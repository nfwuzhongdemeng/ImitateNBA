package com.dyy.nba.commonlibs;

import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by 段钰莹 on 2017/9/2.
 */

public class AppUtils {
    private static BaseApplication sContext;
    private static Handler sHandler = new Handler(Looper.getMainLooper());
    private static Thread uiThread;
    public static void init(BaseApplication sContext){
        AppUtils.sContext = sContext;
        uiThread = Thread.currentThread();
    }

    public static Resources getResources(){
        return sContext.getResources();
    }

    public static boolean isUIThread(){
        return uiThread == Thread.currentThread();
    }

    public static void runUI(Runnable runnable){
        sHandler.post(runnable);
    }

    public static void runChild(Runnable runnable, long milli){
        sHandler.postDelayed(runnable,milli);
    }

    public static void removeCallbacks(Runnable runnable){
        if(runnable == null){
            sHandler.removeCallbacksAndMessages(runnable);
        }else{
            sHandler.removeCallbacks(runnable);
        }
    }

    public static BaseApplication getSuperContext(){
        return sContext;
    }

    public static String getLocalCrashPath(){
        return sContext.getLocalCrashPath();
    }

    public static String getUploadCrashPath(){
        return sContext.getUploadCrashPath();
    }

    public static boolean needUpload(){
        return sContext.needUpload();
    }

    public static boolean isRelease(){
        return sContext.isRelease();
    }

    public static String getRootPath(){
        return sContext.getRootPath();
    }

}
