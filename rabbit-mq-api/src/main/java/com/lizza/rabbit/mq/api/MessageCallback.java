package com.lizza.rabbit.mq.api;

/**
 * @Desc: 回调接口
 * @author: lizza.liu
 * @date: 2022-02-05
 */
public interface MessageCallback {

    /**
     * 成功回调
     */
    void onSuccess();

    /**
     * 失败回调
     */
    void onFailure();
}
