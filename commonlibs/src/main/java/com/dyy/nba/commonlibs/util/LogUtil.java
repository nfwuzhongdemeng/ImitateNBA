package com.dyy.nba.commonlibs.util;

import android.util.Log;

import com.dyy.nba.commonlibs.AppUtils;

/**
 * Created by 段钰莹 on 2017/9/9.
 */

public class LogUtil {
    /**v=verbose---start**/
    public static void v(String msg){
        if(AppUtils.getDebug()){
            Log.v(AppUtils.getSuperContext().getPackageName(),msg);
        }
    }

    public static void v(String tag,String msg){
        if(AppUtils.getDebug()){
            Log.v(tag,msg);
        }
    }
    /**v=verbose---end**/

    /**d=debug--start**/
    public static void d(String msg){
        if(AppUtils.getDebug()){
            Log.d(AppUtils.getSuperContext().getPackageName(),msg);
        }
    }

    public static void d(String tag,String msg){
        if(AppUtils.getDebug()){
            Log.d(tag,msg);
        }
    }
    /**d=debug--end**/

    /**i=info--start**/
    public static void i(String msg){
        if(AppUtils.getDebug()){
            Log.i(AppUtils.getSuperContext().getPackageName(),msg);
        }
    }

    public static void i(String tag,String msg){
        if(AppUtils.getDebug()){
            Log.i(tag,msg);
        }
    }
    /**i=info--end**/

    /**w=warn--start**/
    public static void w(String msg){
        if(AppUtils.getDebug()){
            Log.w(AppUtils.getSuperContext().getPackageName(),msg);
        }
    }

    public static void w(String tag,String msg){
        if(AppUtils.getDebug()){
            Log.w(tag,msg);
        }
    }

    public static void w(String msg,Throwable throwable){
        if(AppUtils.getDebug()){
            Log.w(AppUtils.getSuperContext().getPackageName(),msg,throwable);
        }
    }

    public static void w(String tag,String msg,Throwable throwable){
        if(AppUtils.getDebug()){
            Log.w(tag,msg,throwable);
        }
    }
    /**w=warn--end**/

    /**e=error--start**/
    public static void e(String msg){
        if(AppUtils.getDebug()){
            Log.e(AppUtils.getSuperContext().getPackageName(),msg);
        }
    }

    public static void e(String tag,String msg){
        if(AppUtils.getDebug()){
            Log.e(tag,msg);
        }
    }

    public static void e(String msg,Throwable throwable){
        if(AppUtils.getDebug()){
            Log.e(AppUtils.getSuperContext().getPackageName(),msg,throwable);
        }
    }

    public static void e(String tag,String msg,Throwable throwable){
        if(AppUtils.getDebug()){
            Log.e(tag,msg,throwable);
        }
    }
    /**e=error--end**/
}
