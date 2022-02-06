package com.lizza.rabbit.producer.broker;

import com.lizza.rabbit.mq.api.MessageCallback;
import com.lizza.rabbit.mq.api.MessageProducer;
import com.lizza.rabbit.mq.api.entity.Message;
import com.lizza.rabbit.mq.api.exception.MessageRunTimeException;
import com.lizza.rabbit.producer.factory.MessageSenderFactory;
import com.lizza.rabbit.producer.sender.MessageSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Desc:
 * @author: lizza.liu
 * @date: 2022-02-05
 */
@Component
public class ProducerClient implements MessageProducer {

    @Resource
    private MessageSenderFactory messageSenderFactory;

    @Override
    public void send(Message message) throws MessageRunTimeException {
        MessageSender messageSender = messageSenderFactory.getMessageSender(message.getMessageType());
        messageSender.send(message);
    }

    @Override
    public void send(Message message, MessageCallback callback) throws MessageRunTimeException {

    }

    @Override
    public void send(List<Message> messages) throws MessageRunTimeException {

    }
}
