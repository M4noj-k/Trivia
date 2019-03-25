package com.example.trivia.Util;

import android.support.v4.app.FragmentManager;

public class Utils {
    public interface BackPressCallback{
        void onBackPressEvent(FragmentManager fm);
    }
}
