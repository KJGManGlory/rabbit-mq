package com.lizza.base.producer;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @Desc: 消息发送者
 * @author: lizza.liu
 * @date: 2022-02-04
 */
@Slf4j
@Component
public class MsgProducer<T> {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /** 监听确认消息回调接口, 用于确认消息是否被 MQ Broker 收到 **/
    private final RabbitTemplate.ConfirmCallback callback = new RabbitTemplate.ConfirmCallback() {
        /**
         * @param data      业务侧唯一标示
         * @param ack       Broker 是否收到消息标示
         * @param cause     失败原因
         */
        @Override
        public void confirm(CorrelationData data, boolean ack, String cause) {
            log.info(">>> Message callback, data: {}, ack: {}, cause: {}",
                    JSONUtil.toJsonStr(data), ack, cause);
        }
    };

    /** 消息发送完成后的回调 **/
    private final MessagePostProcessor postProcessor = message -> {
        log.info(">>> Message send finished! message: {}", JSONUtil.toJsonStr(message));
        return message;
    };

    public void send(T message, Map<String, Object> properties) {
        // 消息头
        MessageHeaders headers = new MessageHeaders(properties);

        // 业务唯一标示
        CorrelationData data = new CorrelationData(UUID.fastUUID().toString(true));

        // 设置确认消息回调
        rabbitTemplate.setConfirmCallback(callback);

        // 消息发送
        rabbitTemplate.convertAndSend("exchange-1",
                "springboot.rabbit",
                message, postProcessor, data);
    }
}
