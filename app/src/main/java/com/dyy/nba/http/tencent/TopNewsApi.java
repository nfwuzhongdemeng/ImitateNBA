package com.dyy.nba.http.tencent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 段钰莹 on 2017/9/9.
 */

public interface TopNewsApi {
    @GET("/news/index")
    Call<String> getNewsIndex(@Query("column") String column);
    @GET("/news/item")
    Call<String> getNewsItem(@Query("column") String column, @Query("articleIds") String articleIds);
}
