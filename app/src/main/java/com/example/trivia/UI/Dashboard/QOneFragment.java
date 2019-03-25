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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.trivia.Base.BaseFragment;
import com.example.trivia.R;

public class QOneFragment extends BaseFragment {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button next;
    private TextView mQ1;
    private String selectedAns="";


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment QOneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QOneFragment newInstance(Bundle args) {
        QOneFragment fragment = new QOneFragment();
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
        View v =inflater.inflate(R.layout.fragment_qone, container, false);

        radioGroup = v.findViewById(R.id.radio_q1);
        next = v.findViewById(R.id.next_entry);
        mQ1 = v.findViewById(R.id.q1Ques);

        handleEvents();

        return v;
    }


    private void handleEvents() {

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = group.findViewById(checkedId);
                switch (group.indexOfChild(radioButton)) {

                    case 0: selectedAns="Sachin Tendulkar";
                        break;
                    case 1:selectedAns="Virat Kolli";
                        break;
                    case 2:selectedAns="Adam Gilchirst";
                        break;
                    case 3:selectedAns="Jacques Kallis";
                        break;
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!selectedAns.isEmpty()){
                    Log.d("selected",selectedAns);
                    ViewModelProviders.of(getActivity()).get(DashboardViewModel.class).updateQues1(mQ1.getText().toString(),selectedAns);
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewModelProviders.of(getActivity()).get(DashboardViewModel.class).getQues1Update().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                ((DashboardActivity)getActivity()).openFragment();
            }
        });
    }

    @Override
    protected void onBackpressed(FragmentManager fm) {
        fm.popBackStack();
    }
}
