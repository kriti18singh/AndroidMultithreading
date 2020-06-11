package com.example.android.multithreading.exercises.exercise1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.multithreading.R;
import com.example.android.multithreading.common.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Exercise1Fragment extends BaseFragment {

    private static final int ITERATIONS_COUNTER_DURATION_SEC = 10;

    public static Fragment newInstance() {
        return new Exercise1Fragment();
    }

    private Button mBtnCountIterations;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise1, container, false);

        mBtnCountIterations = view.findViewById(R.id.btn_count_iterations);
        mBtnCountIterations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countIterations();
            }
        });

        return view;
    }

    @Override
    protected String getScreenTitle() {
        return "Exercise 1";
    }

    private void countIterations() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long startTimestamp = System.currentTimeMillis();
                long endTimestamp = startTimestamp + ITERATIONS_COUNTER_DURATION_SEC * 1000;

                int iterationsCount = 0;
                while (System.currentTimeMillis() <= endTimestamp) {
                    iterationsCount++;
                }
                Log.d(
                        "Exercise1",
                        "iterations in " + ITERATIONS_COUNTER_DURATION_SEC + "seconds: " + iterationsCount
                );
            }
        });
        thread.start();

    }
}
