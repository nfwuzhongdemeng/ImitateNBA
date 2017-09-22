package com.dyy.nba.http.gson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dyy.nba.model.Base;
import com.dyy.nba.model.MatchStat;
import com.dyy.nba.model.NewsItem;
import com.dyy.nba.model.NewsItemBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by 段钰莹 on 2017/9/13.
 */

public class JsonHelper {
    private static Gson gson = new GsonBuilder().serializeNulls()
            .registerTypeAdapter(MatchStat.MaxPlayers.MatchPlayerInfo.class, new MatchPlayerInfoDefaultAdapter())
            .registerTypeHierarchyAdapter(List.class, new ListDefaultAdapter())
            .create();
    public static <T>T gson2obj(String gsonStr, Class<T> clazz){
        return gson.fromJson(gsonStr,clazz);
    }

    public static String obj2gson(Object obj){
        return gson.toJson(obj);
    }

    public static NewsItem parseNewsItem(String jsonStr) {
        NewsItem newsItem = new NewsItem();
        JSONObject data = JSON.parseObject(JsonHelper.parseBase(newsItem, jsonStr)); // articleIds=    NullPoint
        List<NewsItemBean> list = new ArrayList<NewsItemBean>();
        //Set<String> keySet = data.keySet();
        for (Map.Entry<String, Object> item : data.entrySet()) {
            Gson gson = new Gson();
            NewsItemBean bean = gson.fromJson(item.getValue().toString(), NewsItemBean.class);
            bean.index = item.getKey();
            list.add(bean);
        }
        // 由于fastjson获取出来的entrySet是乱序的  所以这边重新排序
        Collections.sort(list, new Comparator<NewsItemBean>() {
            @Override
            public int compare(NewsItemBean lhs, NewsItemBean rhs) {
                return rhs.index.compareTo(lhs.index);
            }
        });
        newsItem.data = list;
        return newsItem;
    }

    public static String parseBase(Base base, String jsonStr) {
        JSONObject jsonObj = JSON.parseObject(jsonStr);
        String data = "{}";
        for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
            if (entry.getKey().equals("code")) {
                base.code = Integer.parseInt(entry.getValue().toString());
            } else if (entry.getKey().equals("version")) {
                base.version = entry.getValue().toString();
            } else {
                data = entry.getValue().toString();
            }
        }
        return data;
    }
}
