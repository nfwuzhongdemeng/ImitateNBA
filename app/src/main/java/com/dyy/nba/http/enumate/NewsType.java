package com.dyy.nba.http.enumate;

/**
 * Created by 段钰莹 on 2017/9/12.
 */

public enum NewsType {
    BANNER("banner"),      //头条
    NEWS("news"),          //新闻
    VIDEO("videos"),     //视频集锦
    DEPTH("depth"),        //十佳球/五佳球
    HIGHLIGHT("highlight");//赛场花絮

    private String type;

    NewsType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
