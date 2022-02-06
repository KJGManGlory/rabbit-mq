package com.lizza.rabbit.producer.sender;

import com.lizza.rabbit.mq.api.entity.Message;
import com.lizza.rabbit.mq.api.enums.MessageType;

/**
 * @Desc: 消息发送接口
 * @author: lizza.liu
 * @date: 2022-02-05
 */
public interface MessageSender {

    /**
     * 发送消息
     * @param message   消息体
     * @return void
     */
    void send(Message message);

    MessageType type();
}
