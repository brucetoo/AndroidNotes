package com.brucetoo.androidnotes.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

/**
 * Created by Bruce Too
 * On 27/06/2018.
 * At 16:57
 */
public class OkioFragment extends Fragment {

    TextView text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        text = new TextView(getActivity());
        text.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        text.setGravity(Gravity.CENTER);
        text.setBackgroundColor(Color.WHITE);
        text.setText("OkioFragment");
        return text;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/okio.txt");
            //初始化写入sink，
            Sink sink = Okio.sink(file);
            //初始化sink buffer，实际是 RealBufferedSink
            BufferedSink bufferSink = Okio.buffer(sink);
            bufferSink.writeUtf8("i am brucetoo");
            bufferSink.flush();
            bufferSink.close();

            //初始化读取source
            Source source = Okio.source(file);
            //初始化source buffer,实际是 RealBufferedSource
            BufferedSource bufferedSource = Okio.buffer(source);
            String line = bufferedSource.readUtf8();
            bufferSink.close();
            text.setText(line);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
