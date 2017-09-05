package com.dyy.nba.commonlibs.util;

import android.widget.Toast;

import com.dyy.nba.commonlibs.AppUtils;

/**
 * Created by 段钰莹 on 2017/9/5.
 */

public class ToastUtil {
    public static void shorts(String msg){
        Toast.makeText(AppUtils.getSuperContext(),msg,Toast.LENGTH_SHORT).show();
    }

    public static void longs(String msg){
        Toast.makeText(AppUtils.getSuperContext(),msg,Toast.LENGTH_LONG).show();
    }
}
