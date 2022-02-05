package com.lizza.rabbit.producer.util;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.lizza.rabbit.mq.api.entity.Message;
import com.lizza.rabbit.mq.api.enums.MessageType;
import com.lizza.rabbit.mq.api.exception.MessageException;
import com.lizza.rabbit.mq.api.exception.MessageRunTimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Desc: RabbitTemplate 池化处理: 根据 topic 进行池化, 提高性能
 * @author: lizza.liu
 * @date: 2022-02-05
 */
@Slf4j
@Component
public class RabbitTemplateHolder {

    /** RabbitTemplate 缓存池, 根据 topic 进行缓存, 相同的 topic 复用同一个 RabbitTemplate **/
    private final Map<String, RabbitTemplate> map = Maps.newConcurrentMap();

    @Resource
    private ConnectionFactory connectionFactory;

    public RabbitTemplate getRabbitTemplate(Message message) throws MessageRunTimeException {
        Preconditions.checkNotNull(message);
        RabbitTemplate rabbitTemplate = map.get(message.getTopic());
        if (Objects.nonNull(rabbitTemplate)) {
            return rabbitTemplate;
        }
        log.info("get rabbit template from cache is empty, topic:{}", message.getTopic());
        rabbitTemplate = new RabbitTemplate(connectionFactory);

        rabbitTemplate.setExchange(message.getTopic());
        rabbitTemplate.setRetryTemplate(new RetryTemplate());
        rabbitTemplate.setRoutingKey(message.getRoutingKey());
        // todo 消息序列化方式
        rabbitTemplate.setMessageConverter(null);

        // 除了快速消息, 其他消息都需要确认
        if (!MessageType.RAPID.equals(message.getMessageType())) {
            rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
                // 确认回调
                List<String> array = Splitters.POUND_SPLITTER.splitToList(correlationData.getId());
                String messageId = array.get(0);
                long sendTime = Long.parseLong(array.get(1));
                if (!ack) {
                    log.error("send message failure, message id: {}, send time: {}",
                            messageId, sendTime);
                    return;
                }
                log.info("send message success, message id: {}, send time: {}",
                        messageId, sendTime);
            });
        }

        map.putIfAbsent(message.getTopic(), rabbitTemplate);

        return rabbitTemplate;
    }
}
