package com.dyy.nba.cache;

/**
 * Created by 段钰莹 on 2017/9/9.
 */

public class CacheManager {
    private static CacheManager sIntance;
    public CacheManager() {
    }

    /**
     * 双检索单例
     * @return
     */
    public  static CacheManager getIntance(){
        if (sIntance == null) {
            synchronized (CacheManager.class) {
                if (sIntance == null) {
                    sIntance = new CacheManager();
                }
            }
        }
        return  sIntance;
    }

//    private static RealmConfiguration config;
//    public static void init(RealmConfiguration config){
//        CacheManager.config = config;
//    }
//
//    /**
//     * 获取realm对象
//     * @return
//     */
//    public Realm getRealm(){
//        return  Realm.getInstance(config);
//    }
}
