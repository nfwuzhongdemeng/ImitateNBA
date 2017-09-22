//package com.dyy.nba.cache.dao;
//
//import com.dyy.nba.cache.CacheManager;
//import com.dyy.nba.db.BaseDao;
//import com.dyy.nba.model.NewsIndex;
//import com.dyy.nba.model.dbmodel.DBNewsIndex;
//
//import java.util.List;
//
//import io.realm.Realm;
//import io.realm.Sort;
//
///**
// * Created by 段钰莹 on 2017/9/12.
// */
//
//public class NewsIndexDao  implements BaseDao<DBNewsIndex> {
//    private NewsIndexDao(){
//    }
//
//    private Realm realm;
//    private Realm getRealm(){
//        if(realm == null)
//            realm = CacheManager.getIntance().getRealm();
//        return realm;
//    }
//
//    private static NewsIndexDao dao;
//    public static NewsIndexDao getInstance(){
//        if(dao == null)
//            dao = new NewsIndexDao();
//        return dao;
//    }
//
//    @Override
//    public void insert(DBNewsIndex dbNewsIndex) throws Exception {
//
//    }
//
//    @Override
//    public List<DBNewsIndex> find() throws Exception {
//        List<DBNewsIndex> mlist =  getRealm().where(DBNewsIndex.class).findAllSorted("code", Sort.ASCENDING);
//        return mlist;
//    }
//
//    public NewsIndex find(String key) throws Exception{
//        DBNewsIndex dbNewsIndex=realm.where(DBNewsIndex.class).equalTo("key",key).findFirst();
//        return dbNewsIndex.toNormal();
//    }
//
//    @Override
//    public void update(final DBNewsIndex dbNewsIndex) throws Exception {
//        getRealm().executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                DBNewsIndex dbNewsIndex2=realm.where(DBNewsIndex.class).equalTo("key",dbNewsIndex.getKey()).findFirst();
//                dbNewsIndex2.setData(dbNewsIndex.getData());
//                dbNewsIndex2.setCode(dbNewsIndex.getCode());
//                dbNewsIndex2.setVersion(dbNewsIndex.getVersion());
//            }
//
//        });
//    }
//
//
//    @Override
//    public void delete(String code) throws Exception {
//
//    }
//
//    @Override
//    public void close() {
//        getRealm().close();
//    }
//}
