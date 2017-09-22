package com.dyy.skin.mylibrary.db;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

import static io.realm.Realm.getDefaultInstance;

/**
 * Created by 段钰莹 on 2017/8/30.
 */

public class DBVersion implements RealmMigration {
    private static DBVersion sIntance;
    public DBVersion() {
    }

    /**
     * 双检索单例
     * @return
     */
    public  static DBVersion getIntance(){
        if (sIntance == null) {
            synchronized (DBVersion.class) {
                if (sIntance == null) {
                    sIntance = new DBVersion();
                }
            }
        }



        return  sIntance;
    }

    /**
     * 获取realm对象
     * @return
     */
    public Realm getRealm(){
        Realm realm =getDefaultInstance();
        return  realm;
    }

    /**
     * 版本升级处理
     * @param realm
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        switch ((int)oldVersion){
//            case 0:{
//                RealmObjectSchema personSchema = schema.get("User");//获取表
////                //新增@Required的id
//                personSchema
//                        .addField("outerId", String.class)//添加字段
//                        .transform(new RealmObjectSchema.Function() {
//                            @Override
//                            public void apply(DynamicRealmObject obj) {
//                                obj.set("outerId", "1");//为id设置默认值
//                            }
//                        });
////                        .removeField("age");//移除age属性
//                //注意version不要break,因为前面的版本都要升级
//            }
        }

    }
}
