package com.lizza.rabbit.mq.api;

import com.lizza.rabbit.mq.api.entity.Message;

/**
 * @Desc: 消息监听器
 * @author: lizza.liu
 * @date: 2022-02-05
 */
public interface MessageListener {

    /**
     * 监听消息
     * @param message   消息体
     * @return void
     */
    void onMessage(Message message);
}
