package com.lizza.rabbit.producer.converter;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Preconditions;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * @Desc: 定制化的消息转换器, 使用装饰者模式进行定制化处理
 * @author: lizza.liu
 * @date: 2022-02-06
 */
public class RabbitMessageConverter implements MessageConverter {

    private static final String EXPIRATION = String.valueOf(24 * 60 * 60 * 1000);

    private GenericMessageConverter genericConverter;

    public RabbitMessageConverter(GenericMessageConverter genericConverter) {
        Preconditions.checkNotNull(genericConverter);
        this.genericConverter = genericConverter;
    }

    @Override
    public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
        // 定制化处理: 设置一个过期时间
        messageProperties.setExpiration(EXPIRATION);
        return genericConverter.toMessage(object, messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        return null;
    }
}
