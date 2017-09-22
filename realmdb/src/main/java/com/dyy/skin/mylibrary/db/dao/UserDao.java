package com.dyy.skin.mylibrary.db.dao;

import com.dyy.skin.mylibrary.BaseDao;
import com.dyy.skin.mylibrary.db.DBVersion;
import com.dyy.skin.mylibrary.db.model.User;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by 段钰莹 on 2017/8/30.
 */

public class UserDao implements BaseDao<User> {
    private UserDao(){
    }

    private Realm realm;
    private Realm getRealm(){
        if(realm == null)
            realm = DBVersion.getIntance().getRealm();
        return realm;
    }

    private static UserDao dao;
    public static UserDao getInstance(){
        if(dao == null)
            dao = new UserDao();
        return dao;
    }

    @Override
    public void insert(final User user) throws Exception {
        getRealm().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User u = realm.createObject(User.class);
                u.setId(user.getId());
                u.setName(user.getName());
            }
        });
    }

    @Override
    public List<User> find() throws Exception {

        List<User> mlist = null;
        mlist =  getRealm().where(User.class).findAllSorted("id", Sort.ASCENDING);
        return mlist;
    }

    @Override
    public void update(final User user) throws Exception {
        getRealm().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User u=realm.where(User.class).equalTo("id",user.getId()).findFirst();
                u.setName(user.getName());
            }

        });
    }


    @Override
    public void delete(final String id) throws Exception {
        getRealm().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<User> result = realm.where(User.class).equalTo("id", id).findAll();
                if(result!=null)
                    result.deleteAllFromRealm();
            }
        });

    }

    @Override
    public void close() {
        getRealm().close();
    }

    //分页查询
    public static <E extends RealmModel> List<E> getLimitList(RealmResults<E> data, int offset, int limit) {
        List<E> obtainList = new ArrayList();
        Realm realm = Realm.getDefaultInstance();
        if (data.size() == 0 ){
            return obtainList;
        }
        for (int i = offset; i < offset + limit; i++) {
            if (i >= data.size()) {
                break;
            }
            E temp = realm.copyFromRealm(data.get(i));
            obtainList.add(temp);
        }
        realm.close();
        return obtainList;
    }
}
