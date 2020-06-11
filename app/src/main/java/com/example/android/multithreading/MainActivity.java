package com.example.android.multithreading;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.multithreading.common.ScreensNavigator;
import com.example.android.multithreading.common.ToolbarManipulator;
import com.example.android.multithreading.common.dependencyinjection.PresentationCompositionRoot;
import com.techyourchance.fragmenthelper.FragmentContainerWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class MainActivity extends AppCompatActivity implements FragmentContainerWrapper,
        ToolbarManipulator {

    private PresentationCompositionRoot mPresentationCompositionRoot;

    private ScreensNavigator mScreensNavigator;

    private ImageButton mBtnBack;
    private TextView mTxtScreenTitle;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresentationCompositionRoot = new PresentationCompositionRoot(this);

        mScreensNavigator = mPresentationCompositionRoot.getScreensNavigator();

        mBtnBack = findViewById(R.id.btn_back);
        mTxtScreenTitle = findViewById(R.id.txt_screen_title);

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScreensNavigator.navigateUp();
            }
        });

        if (savedInstanceState == null) {
            mScreensNavigator.toHomeScreen();
        }

        reduceChoreographerSkippedFramesWarningThreshold();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void reduceChoreographerSkippedFramesWarningThreshold() {
        Field field = null;
        try {
            field = Choreographer.class.getDeclaredField("SKIPPED_FRAME_WARNING_LIMIT" );
            field.setAccessible(true);
            field.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(null, 1);
        } catch (NoSuchFieldException|IllegalAccessException e) {
            // probably failed to change Choreographer's field, but it's not critical
        }
    }

    @Override
    public void onBackPressed() {
        mScreensNavigator.navigateBack();
    }

    @NonNull
    @Override
    public ViewGroup getFragmentContainer() {
        return findViewById(R.id.frame_content);
    }

    @Override
    public void setScreenTitle(String screenTitle) {
        mTxtScreenTitle.setText(screenTitle);
    }

    @Override
    public void showUpButton() {
        mBtnBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUpButton() {
        mBtnBack.setVisibility(View.INVISIBLE);
    }
}
