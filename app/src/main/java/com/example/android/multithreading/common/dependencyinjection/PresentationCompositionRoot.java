package com.example.android.multithreading.common.dependencyinjection;

import com.example.android.multithreading.common.ScreensNavigator;
import com.example.android.multithreading.common.ToolbarManipulator;
import com.techyourchance.fragmenthelper.FragmentContainerWrapper;
import com.techyourchance.fragmenthelper.FragmentHelper;

import androidx.fragment.app.FragmentActivity;

public class PresentationCompositionRoot {

    private final FragmentActivity mActivity;

    public PresentationCompositionRoot(FragmentActivity mActivity) {
        this.mActivity = mActivity;
    }

    public ScreensNavigator getScreensNavigator() {
        return new ScreensNavigator(getFragmentHelper());
    }

    private FragmentHelper getFragmentHelper() {
        return new FragmentHelper(mActivity, getFragmentContainerWrapper(), mActivity.getSupportFragmentManager());
    }

    private FragmentContainerWrapper getFragmentContainerWrapper() {
        return (FragmentContainerWrapper) mActivity;
    }

    public ToolbarManipulator getToolbarManipulator() {
        return (ToolbarManipulator) mActivity;
    }
}
