package com.docker.commonlibrary.rxbus;

/**
 * Created by zhangshaofang on 2017/7/5.
 */

public class RxEvent<T,R> {

    private T t;
    private R r;

    public RxEvent() {
    }
    public RxEvent(T t, R r) {
        this.t = t;
        this.r = r;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }
}
