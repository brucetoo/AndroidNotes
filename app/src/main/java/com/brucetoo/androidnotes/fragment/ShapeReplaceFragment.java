package com.brucetoo.androidnotes.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brucetoo.androidnotes.R;

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
}
