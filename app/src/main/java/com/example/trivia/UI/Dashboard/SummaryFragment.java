package com.example.trivia.UI.Dashboard;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.trivia.Base.BaseFragment;
import com.example.trivia.Data.Room.PreferenceHelper;
import com.example.trivia.Data.Room.QAEntiry;
import com.example.trivia.R;

public class SummaryFragment extends BaseFragment implements View.OnClickListener {

    Button mHistoryBtn;
    Button mFinishBtn;
    TextView q1;
    TextView a1;
    TextView q2;
    TextView a2;
    TextView name;

    public static SummaryFragment newInstance(Bundle args) {
        SummaryFragment fragment = new SummaryFragment();
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
        View v=inflater.inflate(R.layout.fragment_summary, container, false);
        mHistoryBtn = v.findViewById(R.id.history_btn);
        mFinishBtn = v.findViewById(R.id.finish_btn);
        q1 = v.findViewById(R.id.q1);
        q2 = v.findViewById(R.id.q2);
        a1 = v.findViewById(R.id.a1);
        a2 = v.findViewById(R.id.a2);
        name=v.findViewById(R.id.name);

        mFinishBtn.setOnClickListener(this);
        mHistoryBtn.setOnClickListener(this);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewModelProviders.of(getActivity()).get(DashboardViewModel.class).getLastUserData();
        ViewModelProviders.of(getActivity()).get(DashboardViewModel.class).getLastUser().observe(getActivity(), new Observer<QAEntiry>() {
            @Override
            public void onChanged(@Nullable QAEntiry qaEntiry) {
                name.setText("Hello! "+qaEntiry.getUserName());
                q1.setText(qaEntiry.getQ1ques());
                q2.setText(qaEntiry.getQ2ques());
                a1.setText(qaEntiry.getQ1ans());
                a2.setText(qaEntiry.getQ2ans());
            }
        });
    }

    @Override
    protected void onBackpressed(FragmentManager fm) {
        for(int i = 1; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.finish_btn:
                PreferenceHelper.getPreferenceHelper().storeLastPage(getActivity().getApplication(),"");
                ((DashboardActivity)getActivity()).openFragment();
                break;
            case R.id.history_btn:
                ((DashboardActivity)getActivity()).openHistoryPage();
                break;
        }
    }
}
