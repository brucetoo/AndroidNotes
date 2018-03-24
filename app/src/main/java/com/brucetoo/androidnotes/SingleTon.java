package com.brucetoo.androidnotes;

/**
 * Created by Bruce Too
 * On 20/03/2018.
 * At 17:49
 */

public class SingleTon {
    private static final SingleTon instance = new SingleTon();
    private SingleTon(){}

    public static SingleTon getInstance() {
        return instance;
    }

    public void print(){
        System.out.printf("SingleTon");
    }
}
