package com.example.trivia.UI.Dashboard;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trivia.Base.BaseFragment;
import com.example.trivia.Data.Room.QAEntiry;
import com.example.trivia.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends BaseFragment {

    RecyclerView mHistoryRecycler;
    HistoryAdapter mHistoryAdapter;
    List<QAEntiry> mList=new ArrayList<>();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(Bundle args) {
        HistoryFragment fragment = new HistoryFragment();
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
        View v= inflater.inflate(R.layout.fragment_history, container, false);
        mHistoryRecycler = v.findViewById(R.id.history_recycler);
        mHistoryRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mHistoryAdapter = new HistoryAdapter(mList);
        mHistoryRecycler.setAdapter(mHistoryAdapter);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewModelProviders.of(getActivity()).get(DashboardViewModel.class).getHistory();
        ViewModelProviders.of(getActivity()).get(DashboardViewModel.class).getHistoryData().observe(getActivity(), new Observer<List<QAEntiry>>() {
            @Override
            public void onChanged(@Nullable List<QAEntiry> qaEntiries) {
                mHistoryAdapter.setData(qaEntiries);
            }
        });

    }

    @Override
    protected void onBackpressed(FragmentManager fm) {
        for(int i = 1; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }
}
