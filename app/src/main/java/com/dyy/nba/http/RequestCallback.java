package com.dyy.nba.http;

public interface RequestCallback<T> {

    void onSuccess(T t);

    void onFailure(String message);

}