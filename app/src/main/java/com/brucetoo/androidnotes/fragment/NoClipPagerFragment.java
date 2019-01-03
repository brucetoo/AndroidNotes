package com.brucetoo.androidnotes.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Bruce Too
 * On 2019/1/3.
 * At 16:41
 */
public class NoClipPagerFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FrameLayout frame = new FrameLayout(getContext());
        frame.setBackgroundColor(Color.WHITE);
        frame.setClipChildren(false);
        ViewPager pager = new ViewPager(getContext());
        pager.setPageMargin(20);//page margin must less than margin left/right pixels
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        params.leftMargin = 50;
        params.rightMargin = 50;
        pager.setClipChildren(false);
        pager.setLayoutParams(params);
        pager.setOffscreenPageLimit(2);
        pager.setAdapter(new TestAdapter());
        frame.addView(pager);
        return frame;
    }

    private class TestAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            TextView textView = new TextView(container.getContext());
            textView.setText("Index :" + position);
            textView.setTextSize(33);
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundColor(randomColor());
            container.addView(textView,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            return textView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View view = (View) object;
            if(view.getParent() != null){
                container.removeView(view);
            }
        }
    }

    private int randomColor(){
        Random rnd =  new Random();
        return Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
