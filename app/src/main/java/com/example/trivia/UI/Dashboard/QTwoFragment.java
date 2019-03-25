package com.example.trivia.UI.Dashboard;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.trivia.Base.BaseFragment;
import com.example.trivia.R;


public class QTwoFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener {

    TextView ques2;
    CheckBox white;
    CheckBox orange;
    CheckBox yellow;
    CheckBox green;
    Button next;
    String selected="";
    String whiteStr="";
    String greenStr="";
    String orangeStr="";
    String yellowStr="";

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment QTwoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QTwoFragment newInstance(Bundle args) {
        QTwoFragment fragment = new QTwoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_qtwo, container, false);
        white = v.findViewById(R.id.ck_white);
        green = v.findViewById(R.id.ck_green);
        orange = v.findViewById(R.id.ck_orange);
        yellow = v.findViewById(R.id.ck_yellow);
        next = v.findViewById(R.id.next_entry);
        ques2 = v.findViewById(R.id.ques_2);

        white.setOnCheckedChangeListener(this);
        green.setOnCheckedChangeListener(this);
        orange.setOnCheckedChangeListener(this);
        yellow.setOnCheckedChangeListener(this);

        handleEvents();

        return v;
    }

    private void handleEvents() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!whiteStr.isEmpty())
                    selected+=","+whiteStr;
                if(!yellowStr.isEmpty())
                    selected+=","+yellowStr;
                if(!orangeStr.isEmpty())
                    selected+=","+orangeStr;
                if(!greenStr.isEmpty())
                    selected+=","+greenStr;

                if(!selected.isEmpty()) {
                    selected = selected.substring(1);
                    ViewModelProviders.of(getActivity()).get(DashboardViewModel.class).updateQues2(ques2.getText().toString(), selected);
                    selected="";
                }

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewModelProviders.of(getActivity()).get(DashboardViewModel.class).getQues2Update().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                selected="";
                ((DashboardActivity)getActivity()).openFragment();
            }
        });
    }

    @Override
    protected void onBackpressed(FragmentManager fm) {
        fm.popBackStack();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.ck_white: if(isChecked) whiteStr = white.getText().toString(); else whiteStr=""; break;
            case R.id.ck_orange: if(isChecked) orangeStr = orange.getText().toString(); else orangeStr="";break;
            case R.id.ck_yellow: if(isChecked) yellowStr = yellow.getText().toString(); else yellowStr="";break;
            case R.id.ck_green: if(isChecked) greenStr = green.getText().toString(); else greenStr=""; break;
        }
    }
}
