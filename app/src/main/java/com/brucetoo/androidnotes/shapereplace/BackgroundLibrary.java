package com.brucetoo.androidnotes.shapereplace;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.reflect.Field;

public class BackgroundLibrary {

    /**
     * Inject this before activity's onCreate callback
     * @param context activity
     */
    public static void inject(Context context) {

        LayoutInflater inflater;
        if (context instanceof Activity) {
            inflater = ((Activity) context).getLayoutInflater();
        } else {
            inflater = LayoutInflater.from(context);
        }
        Field field = null;
        try {
            field = LayoutInflater.class.getDeclaredField("mFactorySet");
            field.setAccessible(true);
            if(((Boolean) field.get(inflater))){
                inject2(inflater);
            }else {
                BackgroundFactory factory = new BackgroundFactory();
                if (context instanceof AppCompatActivity) {
                    final AppCompatDelegate delegate = ((AppCompatActivity) context).getDelegate();
                    factory.setInterceptFactory(new LayoutInflater.Factory() {
                        @Override
                        public View onCreateView(String name, Context context, AttributeSet attrs) {
                            return delegate.createView(null, name, context, attrs);
                        }
                    });
                }
                inflater.setFactory(factory);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void inject2(LayoutInflater inflater) {
        try {

            Field field = LayoutInflater.class.getDeclaredField("mFactorySet");
            field.setAccessible(true);
            field.setBoolean(inflater, false);

            BackgroundFactory factory = new BackgroundFactory();
            if (inflater.getFactory2() != null) {
                factory.setInterceptFactory2(inflater.getFactory2());
            } else if (inflater.getFactory() != null) {
                factory.setInterceptFactory(inflater.getFactory());
            }
            inflater.setFactory(factory);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
