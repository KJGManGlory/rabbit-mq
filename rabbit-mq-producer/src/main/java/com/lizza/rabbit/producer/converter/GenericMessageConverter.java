package com.lizza.rabbit.producer.converter;

import com.google.common.base.Preconditions;
import com.lizza.rabbit.mq.common.serializer.Serializer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * @Desc: 通用消息转换器, 负责自定义的 {@link com.lizza.rabbit.mq.api.entity.Message}
 * 与 amqp 的 {@link org.springframework.amqp.core.Message}  之间的相互转换
 * 1. 需要实现 springframework amqp MessageConverter
 * @author: lizza.liu
 * @date: 2022-02-06
 */
public class GenericMessageConverter implements MessageConverter {

    private Serializer serializer;

    public GenericMessageConverter(Serializer serializer) {
        Preconditions.checkNotNull(serializer);
        this.serializer = serializer;
    }

    /**
     * 将自定义的 message 转换为 amqp 的 message
     * @param o 自定义的 message
     * @param messageProperties
     * @return org.springframework.amqp.core.Message
     */
    @Override
    public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
        return new Message(serializer.serializeRaw(o), messageProperties);
    }

    /**
     * 将 amqp 的 message 转换为自定义的 message
     * @param message
     * @return java.lang.Object
     */
    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        return serializer.deserialize(message.getBody());
    }
}
