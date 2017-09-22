//package com.dyy.nba.model.dbmodel;
//
//import com.dyy.nba.model.IndexBean;
//import com.dyy.nba.model.NewsIndex;
//
//import io.realm.RealmList;
//import io.realm.RealmObject;
//
///**
// * Created by 段钰莹 on 2017/9/9.
// */
//public class DBNewsIndex extends RealmObject {
//    private String key;
//    private RealmList<IndexBean> data;
//    private int code;
//    private String version;
//
//    public String getKey() {
//        return key;
//    }
//
//    public void setKey(String key) {
//        this.key = key;
//    }
//
//    public RealmList<IndexBean> getData() {
//        return data;
//    }
//
//    public void setData(RealmList<IndexBean> data) {
//        this.data = data;
//    }
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
//    public NewsIndex toNormal(){
//        NewsIndex index = new NewsIndex();
//        index.data = this.data;
//        index.code = this.code;
//        index.version = this.version;
//        return index;
//    }
//
//
//}
