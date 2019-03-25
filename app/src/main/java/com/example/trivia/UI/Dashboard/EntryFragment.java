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
import android.widget.EditText;

import com.example.trivia.Base.BaseFragment;
import com.example.trivia.R;

public class EntryFragment extends BaseFragment {

    DashboardViewModel mViewModel;
    Button next;
    EditText mUserNmFld;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EntryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EntryFragment newInstance(Bundle args) {
        EntryFragment fragment = new EntryFragment();
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
        View v = inflater.inflate(R.layout.fragment_entry, container, false);
        mUserNmFld =v.findViewById(R.id.username);
        next = v.findViewById(R.id.next_entry);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mUserNmFld.getText().toString().isEmpty())
                ViewModelProviders.of(getActivity()).get(DashboardViewModel.class).storeUser(mUserNmFld.getText().toString());
                mUserNmFld.setText("");
             }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Print",getActivity().getClass().getSimpleName());
        mViewModel = ViewModelProviders.of(getActivity()).get(DashboardViewModel.class);
        Log.d("Print",mViewModel.getClass().getSimpleName());
        mViewModel.getEntryUpdate().observe(getActivity(), new Observer<String>() {
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
