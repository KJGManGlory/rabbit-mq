package com.lizza.rabbit.producer.factory;

import com.google.common.collect.Maps;
import com.lizza.rabbit.mq.api.enums.MessageType;
import com.lizza.rabbit.producer.service.MessageSender;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Desc:
 * @author: lizza.liu
 * @date: 2022-02-05
 */
@Component
public class MessageSenderFactory implements BeanPostProcessor {

    private final Map<MessageType, MessageSender> map = Maps.newHashMap();

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MessageSender) {
            MessageSender sender = (MessageSender) bean;
            map.put(sender.type(), sender);
        }
        return null;
    }

    public MessageSender getMessageSender(MessageType type) {
        return map.get(type);
    }
}
