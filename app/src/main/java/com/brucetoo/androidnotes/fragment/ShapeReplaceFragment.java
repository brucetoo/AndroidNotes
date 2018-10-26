package com.brucetoo.androidnotes.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brucetoo.androidnotes.MainActivity;
import com.brucetoo.androidnotes.R;
import com.brucetoo.androidnotes.tools.RefHandler;

/**
 * Created by Bruce Too
 * On 13/09/2018.
 * At 14:13
 */
public class ShapeReplaceFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_shape_replace_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RefHandler<MainActivity> handler = new RefHandler<>(((MainActivity) getActivity()), new RefHandler.MessageCallback<MainActivity>() {
            @Override
            public void handleMessage(MainActivity referSelf, Message msg) {
                Log.i("handleMessage", "in ");
            }
        });

        handler.sendEmptyMessage(1);
    }
}
