package com.dyy.nba.http.tencent;

import android.text.TextUtils;

import com.dyy.nba.commonlibs.util.LogUtil;
import com.dyy.nba.http.HttpConfig;
import com.dyy.nba.http.RequestCallback;
import com.dyy.nba.http.enumate.NewsType;
import com.dyy.nba.http.gson.JsonHelper;
import com.dyy.nba.http.okhttp.OkHttpHelper;
import com.dyy.nba.model.NewsIndex;
import com.dyy.nba.model.NewsItem;
import com.dyy.nba.model.VideoInfo;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 段钰莹 on 2017/9/12.
 */

public class TopNewsService {
    static Retrofit retrofit = new Retrofit.Builder().baseUrl(HttpConfig.TENCENT_SERVER)
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(OkHttpHelper.getTecentClient()).build();
    static TopNewsApi api = retrofit.create(TopNewsApi.class);

    public static void getNewsIndex(NewsType newsType, boolean isRefresh, final RequestCallback<NewsIndex> cbk) {
        String key = "getNewsIndex"+newsType.getType();
//        NewsIndex newsIndex = null;
//        try {
//            newsIndex = NewsIndexDao.getInstance().find(key);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if(newsIndex!=null){
//            cbk.onSuccess(newsIndex);
//            return;
//        }

        Call<String> call = api.getNewsIndex(newsType.getType());
        call.enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                if (response != null && !TextUtils.isEmpty(response.body())) {
                    String jsonStr = response.body();
                    NewsIndex index = JsonHelper.gson2obj(jsonStr,NewsIndex.class);
                    cbk.onSuccess(index);
//                    try {
//                        NewsIndexDao.getInstance().update(index.toDB());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                    LogUtil.d("resp:" + jsonStr);
                } else {
                    cbk.onFailure("获取数据失败");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                cbk.onFailure(t.getMessage());
            }
        });
    }

    /**
     * 根据索引获取新闻列表
     *
     * @param articleIds 索引值。多个索引以“,”分隔
     * @param isRefresh  是否重新请求数据
     * @param cbk
     */
    public static void getNewsItem(NewsType newsType, String articleIds, boolean isRefresh, final RequestCallback<NewsItem> cbk) {
        final String key = "getNewsItem" + articleIds;

//        NewsItem newsItem = null;
//        try {
//            newsItem = NewsItemDao.getInstance().find(key);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if(newsItem!=null){
//            cbk.onSuccess(newsItem);
//            return;
//        }


        Call<String> call = api.getNewsItem(newsType.getType(), articleIds);
        call.enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                if (response != null && !TextUtils.isEmpty(response.body())) {
                    String jsonStr = response.body();
                    NewsItem newsItem = JsonHelper.parseNewsItem(jsonStr);
                    cbk.onSuccess(newsItem);

//                    DBNewsItem item = newsItem.toDB();
//                    item.key = key;
//                    try {
//                        NewsItemDao.getInstance().update(item);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                    LogUtil.d("resp:" + jsonStr);

                } else {
                    cbk.onFailure("获取数据失败");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                cbk.onFailure(t.getMessage());
            }
        });
    }

    public static void getVideoRealUrls(String vid, final RequestCallback<VideoInfo> cbk) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.TECENT_URL_SERVER_1)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OkHttpHelper.getTecentClient()).build();
        TopNewsApi api = retrofit.create(TopNewsApi.class);

        Call<String> call = api.getVideoRealUrls(vid);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response != null && !TextUtils.isEmpty(response.body())) {
                    String resp = response.body()
                            .replaceAll("QZOutputJson=", "")
                            .replaceAll(" ", "")
                            .replaceAll("\n", "");
                    if (resp.endsWith(";"))
                        resp = resp.substring(0, resp.length() - 1);

                    LogUtil.d(resp);

                    VideoInfo info = new Gson().fromJson(resp, VideoInfo.class);
                    cbk.onSuccess(info);

                } else {
                    cbk.onFailure("");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                LogUtil.e("getVideoRealUrls:" + t.toString());
                cbk.onFailure(t.getMessage());
            }
        });
    }
}
