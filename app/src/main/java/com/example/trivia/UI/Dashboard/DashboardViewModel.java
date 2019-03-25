package com.example.trivia.UI.Dashboard;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;
import android.util.Log;


import com.example.trivia.Data.Room.AppDatebase;
import com.example.trivia.Data.Room.PreferenceHelper;
import com.example.trivia.Data.Room.QAEntiry;
import com.example.trivia.Util.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DashboardViewModel extends AndroidViewModel {

    AppDatebase appDatabase=null;
    public  MutableLiveData<String> entryUpdate=new MutableLiveData();
    public  MutableLiveData<String> ques1Update=new MutableLiveData();
    public  MutableLiveData<String> ques2Update=new MutableLiveData();
    public  MutableLiveData<List<QAEntiry>> historyData=new MutableLiveData();
    public MutableLiveData<QAEntiry> lastUser = new MutableLiveData<>();


    //Get live data at observer
    public MutableLiveData<String> getEntryUpdate() {
        return entryUpdate;
    }

    public MutableLiveData<QAEntiry> getLastUser() {
        return lastUser;
    }

    public MutableLiveData<String> getQues1Update() {
        return ques1Update;
    }

    public MutableLiveData<String> getQues2Update() {
        return ques2Update;
    }

    public LiveData<List<QAEntiry>> getHistoryData() {
        return historyData;
    }

    public DashboardViewModel(@NonNull Application application) {
        super(application);
    }

    public String getCurrentDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Log.d("DATE",dateFormat.format(new Date()));
        return dateFormat.format(new Date());
    }

    public AppDatebase getAppDatabase() {
        if(appDatabase==null){
            appDatabase = Room.databaseBuilder(getApplication(), AppDatebase.class, "db-task")
                        .build();
        }
        return appDatabase;
    }

    public void storeUser(final String username){

        new Thread(new Runnable() {
            @Override
            public void run() {
                QAEntiry q = new QAEntiry();
                q.setDate(getCurrentDateTime());
                q.setUserName(username);
                getAppDatabase().getDao().insertData(q);

                QAEntiry lastUser = getAppDatabase().getDao().getLastUser();
                if(lastUser.getUserId()>0){
                    PreferenceHelper.getPreferenceHelper().storeUserId(getApplication(),lastUser.getUserId());
                    PreferenceHelper.getPreferenceHelper().storeLastPage(getApplication(),Constants.Q1_PAGE);
                    entryUpdate.postValue("SUCCESS");
                }
            }
        }).start();


    }

    public void getLastUserData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
               lastUser.postValue(getAppDatabase().getDao().getLastUser());
            }
        }).start();
    }

    public void updateQues1(final String qes1, final String qesAns1){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getAppDatabase().getDao().updateQ1(PreferenceHelper.getPreferenceHelper().getUSER_ID(getApplication()),
                        qes1,qesAns1);
                PreferenceHelper.getPreferenceHelper().storeLastPage(getApplication(),Constants.Q2_PAGE);
                ques1Update.postValue("SUCCESS");
            }
        }).start();

    }

    public void updateQues2(final String ques2, final String qesAns2){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getAppDatabase().getDao().updateQ2(PreferenceHelper.getPreferenceHelper().getUSER_ID(getApplication()),
                        ques2,qesAns2);
                PreferenceHelper.getPreferenceHelper().storeLastPage(getApplication(),Constants.SUMMARY);
                ques2Update.postValue("SUCCESS");
            }
        }).start();

    }

    public void getHistory(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                historyData.postValue(getAppDatabase().getDao().loadHistory());
            }
        }).start();

    }


}
