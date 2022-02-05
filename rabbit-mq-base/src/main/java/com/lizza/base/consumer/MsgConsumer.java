package com.lizza.base.consumer;

import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Desc: 消息消费者
 * @author: lizza.liu
 * @date: 2022-02-05
 */
@Slf4j
@Component
public class MsgConsumer<T> {

    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue-1", durable = "true"),
            exchange = @Exchange(name = "exchange-1", durable = "ture", type = "topic", ignoreDeclarationExceptions = "true"),
            key = "springboot.*"
    ))
    public void onMessage(Message<T> message, Channel channel) throws IOException {
        // 收到消息
        log.info(">>> Consumer accept message: {}", JSONUtil.toJsonStr(message));
        // 手动 ack; spring.rabbitmq.listener.simple.acknowledge-mode=manual 配置生效
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }
}
