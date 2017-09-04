package com.dyy.nba.commonlibs;

import android.app.Application;

/**
 * Created by 段钰莹 on 2017/9/2.
 * baseApp模板，个别情况可能需要继承application,可复制移植，然后要记得更新AppUtil内为相应Application
 */

public abstract class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    /**
     * 获取本地异常日志地址
     * @return
     */
    public abstract String getLocalCrashPath();

    /**
     * 获取上传异常日志地址
     * @return
     */
    public abstract String getUploadCrashPath();

    public abstract String getRootPath();
    /**
     * 获取是否为上传模式
     * true 上传模式，异常日志在relaseUpload为true时会上传到指定服务器，然后删除本地错误日志
     * false 调试模式，异常日志保存在本地指定路径
     * @return
     */
   public abstract boolean needUpload();

    public abstract boolean isRelease();

    /**
     * 配置异常启动页
     * @return
     */
    public abstract Class getStartClazz();
}
