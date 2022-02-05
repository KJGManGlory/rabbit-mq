package com.lizza.rabbit.mq.api;

import com.lizza.rabbit.mq.api.entity.Message;
import com.lizza.rabbit.mq.api.exception.MessageRunTimeException;

import java.util.List;

/**
 * @Desc: 消息发送者
 * @author: lizza.liu
 * @date: 2022-02-05
 */
public interface MessageProducer {

    /**
     * 发送单条消息
     * @param message   消息体
     * @throws MessageRunTimeException
     */
    void send(Message message) throws MessageRunTimeException;

    /**
     * 发送单条消息, 并且可以注册回调服务
     * @param message   消息体
     * @param callback  回调服务
     * @throws MessageRunTimeException
     */
    void send(Message message, MessageCallback callback) throws MessageRunTimeException;

    /**
     * 发送多条消息
     * @param messages  消息体
     * @throws MessageRunTimeException
     */
    void send(List<Message> messages) throws MessageRunTimeException;
}
