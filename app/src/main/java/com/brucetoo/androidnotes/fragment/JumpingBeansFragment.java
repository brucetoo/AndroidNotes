package com.brucetoo.androidnotes.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brucetoo.androidnotes.jumpingbeans.JumpingBeans;

/**
 * Created by Bruce Too
 * On 02/07/2018.
 * At 18:36
 */
public class JumpingBeansFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = new LinearLayout(getContext());
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setGravity(Gravity.CENTER);
        layout.setBackgroundColor(Color.WHITE);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setClickable(true);

        final TextView textView1 = new TextView(getContext());
        textView1.setTextSize(40);
        textView1.setText("Jumping");
        JumpingBeans.with(textView1)
                .appendJumpingDots()
                .build();

        final TextView textView2 = new TextView(getContext());
        textView2.setText("Jumping...");
        textView2.setTextSize(40);
        JumpingBeans.with(textView2)
                .makeTextJump(0, textView2.getText().toString().length())
                .setIsWave(true)
                .setLoopDuration(1000)  // ms
                .build();

        layout.addView(textView1);
        layout.addView(textView2);
        return layout;
    }
}
