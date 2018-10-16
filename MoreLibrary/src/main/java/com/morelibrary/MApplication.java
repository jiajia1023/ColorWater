package com.morelibrary;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

/**
 * author:jjj
 * time: 2018/10/15 16:20
 * TODO:
 */
public class MApplication extends Application {
    private List<Activity> activityList = new LinkedList<>();
    private static MApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MApplication getInstance() {
        return instance;
    }

    public void addActivity(Activity activity) {

        if (activity != null) {
            this.activityList.add(activity);
        }
    }

    public void removeActivity(Activity activity) {
        if (activity != null) {
            this.activityList.remove(activity);
        }
    }

    public List<Activity> getActivityList() {
        return activityList;
    }


    public void exit() {
        try {
            for (Activity activity : activityList) {
                if (activity != null) {
                    activity.finish();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}
