package com.dyy.nba.commonlibs.manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 段钰莹 on 2017/9/2.
 */

public class ActivityManager {
    private List<Activity> mActivities = new ArrayList<>();
    private static ActivityManager manager;

    private ActivityManager() {
    }

    public static ActivityManager getInstance() {
        if (manager == null)
            manager = new ActivityManager();
        return manager;
    }

    public void addActivity(Activity activity) {
        mActivities.add(activity);
    }

    public void finishTop() {
        Activity activity = mActivities.get(mActivities.size() - 1);
        removeActivity(activity);
        if (activity != null)
            activity.finish();
    }

    public void finishAll() {
        for (int i = mActivities.size() - 1; i >= 0; i--) {
            Activity activity = mActivities.get(i);
            removeActivity(activity);
            if (activity != null)
                activity.finish();
            i = mActivities.size();
        }
    }

    public void removeActivity(Activity activity) {
        if (mActivities.contains(activity))
            mActivities.remove(activity);
    }

    /**
     * 关闭除了主页以外的页，用于回收
     */
    public void finishNoMain() {
        for (int i = mActivities.size() - 1; i >= 1; i--) {
            Activity activity = mActivities.get(i);
            removeActivity(activity);
            if (activity != null)
                activity.finish();
            i = mActivities.size();
        }
    }
}
