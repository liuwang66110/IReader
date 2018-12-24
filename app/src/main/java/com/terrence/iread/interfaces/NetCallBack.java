package com.terrence.iread.interfaces;


public interface NetCallBack<T> {
    void onSuccess(T t);

    void onFail(String reason);
}
