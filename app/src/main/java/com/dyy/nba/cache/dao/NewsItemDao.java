//package com.dyy.nba.cache.dao;
//
//import com.dyy.nba.cache.CacheManager;
//import com.dyy.nba.db.BaseDao;
//import com.dyy.nba.model.NewsItem;
//import com.dyy.nba.model.dbmodel.DBNewsItem;
//
//import java.util.List;
//
//import io.realm.Realm;
//
///**
// * Created by 段钰莹 on 2017/9/13.
// */
//
//public class NewsItemDao implements BaseDao<DBNewsItem> {
//    private NewsItemDao(){
//    }
//
//    private Realm realm;
//    private Realm getRealm(){
//        if(realm == null)
//            realm = CacheManager.getIntance().getRealm();
//        return realm;
//    }
//
//    private static NewsItemDao dao;
//    public static NewsItemDao getInstance(){
//        if(dao == null)
//            dao = new NewsItemDao();
//        return dao;
//    }
//
//    @Override
//    public void insert(DBNewsItem dbNewsItem) throws Exception {
//
//    }
//
//    @Override
//    public List<DBNewsItem> find() throws Exception {
//        return null;
//    }
//
//    public NewsItem find(String key) throws Exception{
//        DBNewsItem dbNewsIndex=realm.where(DBNewsItem.class).equalTo("key",key).findFirst();
//        return dbNewsIndex.toNormal();
//    }
//
//    @Override
//    public void update(final DBNewsItem dbNewsItem) throws Exception {
//        getRealm().executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                DBNewsItem dbNewsItem2=realm.where(DBNewsItem.class).equalTo("key",dbNewsItem.getKey()).findFirst();
//                dbNewsItem2.setData(dbNewsItem.getData());
//                dbNewsItem2.setCode(dbNewsItem.getCode());
//                dbNewsItem2.setVersion(dbNewsItem.getVersion());
//            }
//
//        });
//    }
//
//    @Override
//    public void delete(String code) throws Exception {
//
//    }
//
//    @Override
//    public void close() {
//
//    }
//}
