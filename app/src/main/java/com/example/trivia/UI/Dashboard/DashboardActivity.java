package com.example.trivia.UI.Dashboard;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.trivia.Base.BaseActivity;
import com.example.trivia.Data.Room.PreferenceHelper;
import com.example.trivia.R;
import com.example.trivia.Util.Constants;
import com.example.trivia.Util.Utils;

public class DashboardActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        openFragment();
    }

    public void openFragment() {
        //Check the last page when user exit:
        if (PreferenceHelper.getPreferenceHelper().get_PAGE(getApplication()).equals(Constants.Q1_PAGE))
            switchContent(R.id.dashboard_fragment,QOneFragment.class.getSimpleName(),null);
        else if(PreferenceHelper.getPreferenceHelper().get_PAGE(getApplication()).equals(Constants.Q2_PAGE))
            switchContent(R.id.dashboard_fragment,QTwoFragment.class.getSimpleName(),null);
        else if(PreferenceHelper.getPreferenceHelper().get_PAGE(getApplication()).equals(Constants.SUMMARY))
            switchContent(R.id.dashboard_fragment,SummaryFragment.class.getSimpleName(),null);
        else switchContent(R.id.dashboard_fragment,EntryFragment.class.getSimpleName(),null);
    }

    public void openHistoryPage(){
        switchContent(R.id.dashboard_fragment,HistoryFragment.class.getSimpleName(),null);
    }

}