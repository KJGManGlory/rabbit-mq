package com.lizza.rabbit.mq.common.serializer.impl;

import com.lizza.rabbit.mq.api.entity.Message;
import com.lizza.rabbit.mq.common.serializer.Serializer;
import com.lizza.rabbit.mq.common.serializer.SerializerFactory;

/**
 * @Desc:
 * @author: lizza.liu
 * @date: 2022-02-06
 */
public class JacksonSerializerFactory implements SerializerFactory {

    public static SerializerFactory INSTANCE = new JacksonSerializerFactory();

    @Override
    public Serializer create() {
        return JacksonSerializer.createParametricType(Message.class);
    }
}
