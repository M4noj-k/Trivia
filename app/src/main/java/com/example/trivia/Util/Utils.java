package com.example.trivia.Util;

import android.app.FragmentManager;
public class Utils {
    public interface BackPressCallback{
        void onBackPressEvent(FragmentManager fm);
    }
}
