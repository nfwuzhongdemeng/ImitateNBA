package com.dyy.nba.db;

import java.util.List;

/**
 * Created by 段钰莹 on 2017/8/30.
 */

public interface BaseDao<T> {
    void insert(T t)throws  Exception;

    List<T> find()throws Exception;

    void  update(T t)throws Exception;

    void   delete(String code)throws Exception;

    void close();

}
