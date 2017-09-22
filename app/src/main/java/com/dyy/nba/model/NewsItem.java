package com.dyy.nba.model;

import java.util.List;

/**
 * @author yuyh.
 * @date 16/6/3.
 */
public class NewsItem extends Base {

    /**
     * 20160603041445 : {"atype":"0","title":"詹皇：今天的比赛属于利文斯顿","abstract":"骑士的板凳输出乏力，仅得10分，勇士则多点开花。","imgurl":"http://inews.gtimg.com/newsapp_ls/0/333422118_640470/0","imgurl2":"http://inews.gtimg.com/newsapp_ls/0/333422118_150120/0","newsId":"20160603041445","url":"http://nbachina.qq.com/a/20160603/041445.htm","commentId":"1422192316","pub_time":"2016-06-03 15:02","column":"news","vid":"","duration":"","img_count":"0","images_3":[],"footer":""}
     */

    public List<NewsItemBean> data;

    /**
     * version : c020d3303db6179626d1f8c7fc77acfd
     */

    /**
     * 图片列表用到：title，pub_time，imgurl
     * 视频列表用到：title，realUrl（播放地址）,url(跳转网页地址),rl，vid,重定向地址
     */


//    public DBNewsItem toDB(){
//        DBNewsItem item = new DBNewsItem();
//        item.setCode(this.code);
//        item.setData(data);
//        item.setVersion(version);
//        return item;
//    }
}
