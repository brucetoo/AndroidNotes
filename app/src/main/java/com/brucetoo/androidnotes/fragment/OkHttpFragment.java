package com.brucetoo.androidnotes.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Bruce Too
 * On 05/07/2018.
 * At 15:59
 */
public class OkHttpFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView text = new TextView(getActivity());
        text.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        text.setGravity(Gravity.CENTER);
        text.setBackgroundColor(Color.WHITE);
        text.setText("OkHttpFragment");
        return text;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Log.i("OkHttpFragment", String.format("Sending request %s on %s%n%s",
                                request.url(), chain.connection(), request.headers()));

                        Response response = chain.proceed(request);

                        Log.i("OkHttpFragment", String.format("Received response for %s  %s",
                                response.request().url(), response.headers()));
                        return response;
                    }
                })
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Log.i("OkHttpFragment", String.format("Net Sending request %s on %s%n%s",
                                request.url(), chain.connection(), request.headers()));

                        Response response = chain.proceed(request);

                        Log.i("OkHttpFragment", String.format("Net Received response for %s  %s",
                                response.request().url(), response.headers()));
                        return response;
                    }
                })
                .build();
        Request request = new Request.Builder().url("http://www.publicobject.com/helloworld.txt").build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("OkHttpFragment", String.format("onFailure %s",
                        e.getMessage()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("OkHttpFragment", String.format("onResponse for %s  %s",
                        response.request().url(), response.body()));
            }
        });
    }

    /**
     * 截取浮点数到几位
     * 0.1246 -> 截取三位 -> 0.125
     */
    protected static float truncate(float f, int decimalPlaces) {
        float decimalShift = (float) Math.pow(10, decimalPlaces);
        return Math.round(f * decimalShift) / decimalShift;
    }
}
