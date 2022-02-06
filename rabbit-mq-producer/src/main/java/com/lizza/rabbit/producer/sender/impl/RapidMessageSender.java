package com.lizza.rabbit.producer.sender.impl;

import com.google.common.base.Preconditions;
import com.lizza.rabbit.mq.api.entity.Message;
import com.lizza.rabbit.mq.api.enums.MessageType;
import com.lizza.rabbit.producer.sender.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Desc: 迅速消息
 * @author: lizza.liu
 * @date: 2022-02-05
 */
@Slf4j
@Component
public class RapidMessageSender implements MessageSender {

    @Resource
    private MessageHandler messageHandler;

    @Override
    public void send(Message message) {
        Preconditions.checkNotNull(message.getTopic());
        message.setMessageType(MessageType.RAPID);
        messageHandler.handle(message);
    }

    @Override
    public MessageType type() {
        return MessageType.RAPID;
    }
}
