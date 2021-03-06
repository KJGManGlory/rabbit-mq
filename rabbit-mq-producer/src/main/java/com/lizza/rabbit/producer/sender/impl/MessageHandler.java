package com.lizza.rabbit.producer.sender.impl;

import com.lizza.rabbit.mq.api.entity.Message;
import com.lizza.rabbit.producer.util.RabbitTemplateHolder;
import com.lizza.rabbit.producer.util.ThreadHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Desc:
 * @author: lizza.liu
 * @date: 2022-02-06
 */
@Slf4j
@Component
public class MessageHandler {

    @Resource
    private RabbitTemplateHolder rabbitTemplateHolder;

    public void handle(Message message) {
        ThreadHolder.ASYNC_MESSAGE_SENDER.submit(() -> {
            // Exchange
            String topic = message.getTopic();
            String routingKey = message.getRoutingKey();

            // 消息头
            MessageHeaders headers = new MessageHeaders(message.getAttributes());
            org.springframework.messaging.Message<?> msg = MessageBuilder.createMessage(message, headers);


            // 业务唯一标示
            CorrelationData data = new CorrelationData(String.format("%s#%s",
                    message.getMessageId(), System.currentTimeMillis()));

            // 消息发送
            RabbitTemplate rabbitTemplate = rabbitTemplateHolder.getRabbitTemplate(message);
            rabbitTemplate.convertAndSend(topic, routingKey, msg, data);
        });
    }
}
