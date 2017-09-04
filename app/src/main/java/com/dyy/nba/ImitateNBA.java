package com.dyy.nba;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.dyy.nba.commonlibs.AppUtils;
import com.dyy.nba.commonlibs.BaseApplication;
import com.dyy.nba.commonlibs.CrashHandler;

import java.io.File;

/**
 * Created by 段钰莹 on 2017/9/2.
 */

public class ImitateNBA extends BaseApplication {
    private static ImitateNBA sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        AppUtils.init(this);
        CrashHandler.getInstance().init(sContext);
    }

    public static ImitateNBA getSuperContext(){
        return sContext;
    }

    private String rootPath;

    public String getRootPath() {
        if (rootPath != null && rootPath.length() > 0)
            return rootPath;
        String externalStorageState;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (NullPointerException e) {
            externalStorageState = "";
        }
        boolean c1 = MEDIA_MOUNTED.equals(externalStorageState);
        boolean c2 = hasExternalStoragePermission(sContext);
        if (c1 && c2) {
            rootPath = getExternalDataPath(sContext);
        }

        //现在许多手机内存够大，可能没有sdcard,所以当出现这种情况就放在应用目录下：
        if (rootPath == null) {
            rootPath = getApplicationContext().getPackageResourcePath() + "/files/";
        }

        return rootPath;
    }

    /**
     * 未初始化
     */
    private static final int STATE_INIT = 0;
    /**
     * 没有SD卡
     */
    private static final int STATE_NO_SDCARD = 1;
    /**
     * 没有SD卡权限
     */
    private static final int STATE_NO_PERMISSON = 2;
    /**
     * 有SD卡且成功取得SD卡权限
     */
    private static final int STATE_ALL = 3;


    private String getExternalDataPath(Context context) {
        File appDir = new File(Environment.getExternalStorageDirectory(), context.getPackageName());
        if (!appDir.exists()) {
            if (!appDir.mkdirs()) {
                return null;
            }
        }
        return appDir.getAbsolutePath() + "/";
    }

    private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final String MEDIA_MOUNTED = "mounted";

    private static boolean hasExternalStoragePermission(Context context) {
        int perm = context.checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION);
        return perm == PackageManager.PERMISSION_GRANTED;
    }


    @Override
    public String getLocalCrashPath() {
        return getRootPath() + "/crash/";
    }

    @Override
    public String getUploadCrashPath() {
        return null;
    }

    @Override
    public boolean needUpload() {
        return false;
    }

    @Override
    public boolean isRelease() {
        return false;
    }

    @Override
    public Class getStartClazz() {
        return SplashActivity.class;
    }
}
