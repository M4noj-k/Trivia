package com.example.trivia.Base;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trivia.R;

public abstract class BaseFragment extends Fragment {
    protected abstract void onBackpressed(FragmentManager fm);
}
