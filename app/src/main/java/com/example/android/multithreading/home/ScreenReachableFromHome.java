package com.example.android.multithreading.home;

public enum ScreenReachableFromHome {
    EXERCISE_1("Exercise 1"), EXERCISE_2("Exercise_2");

    private String mName;

    ScreenReachableFromHome(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }
}