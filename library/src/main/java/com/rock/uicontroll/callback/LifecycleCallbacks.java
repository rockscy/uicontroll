package com.rock.uicontroll.callback;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by timeDoMain.
 * User: scy
 * Date: 2/26/21  8:34 PM
 */
public class LifecycleCallbacks implements Application.ActivityLifecycleCallbacks {


    private View decorView;
    private WindowInsetsControllerCompat controller;

    public View getDecorView() {
        return decorView;
    }

    public WindowInsetsControllerCompat getController() {
        return controller;
    }

    private final List<Activity> activities = new ArrayList<>();

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        activities.add(activity);
        decorView = activity.getWindow().getDecorView();
        controller = WindowCompat.getInsetsController(activity.getWindow(), decorView);

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    public boolean isVisible(int type){
       return  ViewCompat.getRootWindowInsets(decorView).isVisible(type);
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        activities.remove(activity);
        if (activities.size() == 0) {
            decorView = null;
            controller = null;
        }
    }
}
