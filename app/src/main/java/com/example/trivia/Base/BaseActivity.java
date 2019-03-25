package com.example.trivia.Base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.trivia.R;
import com.example.trivia.UI.Dashboard.EntryFragment;
import com.example.trivia.UI.Dashboard.HistoryFragment;
import com.example.trivia.UI.Dashboard.QOneFragment;
import com.example.trivia.UI.Dashboard.QTwoFragment;
import com.example.trivia.UI.Dashboard.SummaryFragment;
import com.example.trivia.Util.Utils;

public abstract class BaseActivity extends AppCompatActivity {

    private String CURRENT_TAG;

    private final String ENTRY_PAGE= EntryFragment.class.getSimpleName();
    private final String QONE_PAGE= QOneFragment.class.getSimpleName();
    private final String QTWO_PAGE= QTwoFragment.class.getSimpleName();
    private final String HISTORY_PAGE= HistoryFragment.class.getSimpleName();
    private final String SUMMARY_PAGE= SummaryFragment.class.getSimpleName();



    public enum AnimationType {
        SLIDE_LEFT, SLIDE_RIGHT
    }

    public void switchContent(int id,
                              String TAG,
                              Bundle args) {

        Fragment fragmentToReplace = null;

        FragmentManager fragmentManager = BaseActivity.this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();



        // Try to find the fragment we are switching to
        Fragment fragment = fragmentManager.findFragmentByTag(TAG);

        // If the new fragment can't be found in the manager, create a new
        // one
        if (fragment == null) {

            if(TAG.equals(ENTRY_PAGE))
                fragmentToReplace = EntryFragment.newInstance(args);
            else
            if (TAG.equals(QONE_PAGE)) {
                fragmentToReplace = QOneFragment.newInstance(args);
            } else if (TAG.equals(QTWO_PAGE)) {
                fragmentToReplace = QTwoFragment.newInstance(args);
            } else if (TAG.equals(HISTORY_PAGE))
                fragmentToReplace = HistoryFragment.newInstance(args);
              else if (TAG.equals(SUMMARY_PAGE))
                fragmentToReplace = SummaryFragment.newInstance(args);


        } else

        {
            if(TAG.equals(ENTRY_PAGE))
                fragmentToReplace = (EntryFragment) fragment;
            else
            if (TAG.equals(QONE_PAGE)) {
                fragmentToReplace = (QOneFragment) fragment;
            } else if (TAG.equals(QTWO_PAGE)) {
                fragmentToReplace = (QTwoFragment) fragment;
            } else if (TAG.equals(HISTORY_PAGE)) {
                fragmentToReplace = (HistoryFragment) fragment;
            }else if(TAG.equals(SUMMARY_PAGE)){
                fragmentToReplace = (SummaryFragment)fragment;
            }

        }

        CURRENT_TAG = TAG;

        // Replace our current fragment with the one we are changing to
        transaction.replace(id, fragmentToReplace, TAG);
        transaction.addToBackStack(TAG);

        transaction.commit();

        //}

    }

    @Override
    public void onBackPressed() {

        FragmentManager fragmentManager = BaseActivity.this
                .getSupportFragmentManager();
        Log.d(getClass().getSimpleName(), "backButtonEvent: "+CURRENT_TAG);
        int stackCount = fragmentManager.getBackStackEntryCount();
        Log.d("STACK COUNT", "onBackPressed: "+stackCount);
        if(stackCount>1)
            ((BaseFragment)fragmentManager.findFragmentByTag(CURRENT_TAG)).onBackpressed(fragmentManager);
        else{
            super.onBackPressed();
            finish();
        }
        }

}
