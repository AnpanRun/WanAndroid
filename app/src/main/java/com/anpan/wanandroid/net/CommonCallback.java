package com.anpan.wanandroid.net;

/**
 * Created by AnpanRun on 2021/1/31.
 */
public interface CommonCallback<T> {
    void onSuccess(T data);
    void onError(String resultCode, String resultMsg);
}
