package com.dyy.nba.http.okhttp;

import android.text.TextUtils;

import com.dyy.nba.commonlibs.util.LogUtil;
import com.dyy.nba.commonlibs.util.ShareUtils;
import com.dyy.nba.config.Constant;

import java.io.IOException;
import java.net.URLEncoder;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Retrofit2 Cookie拦截器。用于保存和设置Cookies
 */
public class CookieInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        String cookie = ShareUtils.getInstance().resetShare().getString("cookies");
        if (!TextUtils.isEmpty(cookie)
                && !original.url().toString().contains("loginUsernameEmail")) {
            Request request = original.newBuilder()
                    .addHeader("Cookie", "u=" + cookie + ";") // 不能转UTF-8
                    .build();
            LogUtil.d("okhttplog: set header cookie:" + cookie);
            LogUtil.d("okhttplog: set header cookie:" + URLEncoder.encode(cookie));
            return chain.proceed(request);
        } else {
            for (String header : chain.proceed(original).headers("Set-Cookie")) {
                if (header.startsWith("u=")) {
                   cookie = header.split(";")[0].substring(2);
                    LogUtil.d("okhttplog: add cookie:" + cookie);
                    if (!TextUtils.isEmpty(cookie)) {
                        Constant.Cookie = cookie;
                        ShareUtils.getInstance().resetShare().set("cookies",cookie).commit();
                    }
                }
            }
        }
        return chain.proceed(original);
    }
}
