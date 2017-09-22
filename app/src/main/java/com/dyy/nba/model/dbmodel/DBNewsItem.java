//package com.dyy.nba.model.dbmodel;
//
//import com.dyy.nba.model.NewsItem;
//import com.dyy.nba.model.NewsItemBean;
//
//import io.realm.RealmList;
//import io.realm.RealmObject;
//
///**
// * @author yuyh.
// * @date 16/6/3.
// */
//public class DBNewsItem extends RealmObject {
//
//    /**
//     * 20160603041445 : {"atype":"0","title":"詹皇：今天的比赛属于利文斯顿","abstract":"骑士的板凳输出乏力，仅得10分，勇士则多点开花。","imgurl":"http://inews.gtimg.com/newsapp_ls/0/333422118_640470/0","imgurl2":"http://inews.gtimg.com/newsapp_ls/0/333422118_150120/0","newsId":"20160603041445","url":"http://nbachina.qq.com/a/20160603/041445.htm","commentId":"1422192316","pub_time":"2016-06-03 15:02","column":"news","vid":"","duration":"","img_count":"0","images_3":[],"footer":""}
//     */
//    private int code;
//    private String version;
//    private RealmList<NewsItemBean> data;
//    private String key;
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getVersion() {
//        return version;
//    }
//
//    public void setVersion(String version) {
//        this.version = version;
//    }
//
//    /**
//     * version : c020d3303db6179626d1f8c7fc77acfd
//     */
//
//
//
//    public RealmList<NewsItemBean> getData() {
//        return data;
//    }
//
//    public void setData(RealmList<NewsItemBean> data) {
//        this.data = data;
//    }
//
//    public String getKey() {
//        return key;
//    }
//
//    public void setKey(String key) {
//        this.key = key;
//    }
//
//    /**
//     * 图片列表用到：title，pub_time，imgurl
//     * 视频列表用到：title，realUrl（播放地址）,url(跳转网页地址),imgurl，vid,重定向地址
//     */
//
//
//
//    public NewsItem toNormal(){
//        NewsItem item = new NewsItem();
//        item.data = data;
//        item.code = code;
//        item.version = version;
//        return item;
//    }
//}
