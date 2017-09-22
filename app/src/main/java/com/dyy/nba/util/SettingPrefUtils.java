package com.dyy.nba.util;

import android.text.TextUtils;

import com.dyy.nba.commonlibs.util.ShareUtils;

/**
 * @author yuyh.
 * @date 16/6/26.
 */
public class SettingPrefUtils {

    public static String getUid() {
        return ShareUtils.getInstance().resetShare().getString("uid");
    }

    public static void saveUid(String uid) {
        ShareUtils.getInstance().resetShare().set("uid",uid).commit();
    }

    public static String getToken() {

        return ShareUtils.getInstance().resetShare().getString("token");
    }

    public static void saveToken(String token) {
        ShareUtils.getInstance().resetShare().set("token",token).commit();
    }

    public static String getCookies() {
        return ShareUtils.getInstance().resetShare().getString("cookies");
    }

    public static void saveCookies(String cookies) {
        ShareUtils.getInstance().resetShare().set("cookies",cookies).commit();
    }

    public static String getUsername() {
        return ShareUtils.getInstance().resetShare().getString("username");
    }

    public static void saveUsername(String username) {
        ShareUtils.getInstance().resetShare().set("username",username).commit();
    }

    public static String getPassword() {
        return ShareUtils.getInstance().resetShare().getString("password");
    }

    public static void savePassword(String password) {
        ShareUtils.getInstance().resetShare().set("password",password).commit();
    }

    public static String getNickname() {
        return ShareUtils.getInstance().resetShare().getString("nickname");
    }

    public static void saveNickname(String nickname) {
        ShareUtils.getInstance().resetShare().set("nickname",nickname).commit();
    }

    public static boolean isLogin() {
        return !TextUtils.isEmpty(getCookies()) && !TextUtils.isEmpty(getToken());
    }

    public static void logout() {
        saveCookies("");
        saveNickname("");
        saveToken("");
        saveUid("");
        saveUsername("");
    }
}
