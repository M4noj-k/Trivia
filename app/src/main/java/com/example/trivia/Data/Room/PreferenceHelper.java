package com.example.trivia.Data.Room;

import android.app.Application;
import android.content.SharedPreferences;

public class PreferenceHelper {

    SharedPreferences sharedPreferences=null;
    private static PreferenceHelper preferenceHelper=null;

    final String _PAGE="PAGE";
    final String USER_ID="userid";

    public static PreferenceHelper getPreferenceHelper() {
        if(preferenceHelper==null)
            preferenceHelper=new PreferenceHelper();
        return preferenceHelper;
    }

    public SharedPreferences getSharedPreferences(Application application) {
        if(sharedPreferences==null)
            sharedPreferences = application.getSharedPreferences("STORAGE", application.MODE_PRIVATE);
        return sharedPreferences;
    }

    public void storeLastPage(Application application,String page){
        SharedPreferences.Editor editor = getSharedPreferences(application).edit();
        editor.putString(_PAGE,page);
        editor.commit();
    }

    public String get_PAGE(Application application){
        return getSharedPreferences(application).getString(_PAGE,"");
    }

    public void storeUserId(Application application,Integer id){
        SharedPreferences.Editor editor = getSharedPreferences(application).edit();
        editor.putInt(USER_ID,id);
        editor.commit();
    }

    public Integer getUSER_ID(Application application){
        return getSharedPreferences(application).getInt(USER_ID,0);
    }

}
