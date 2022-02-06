package com.lizza.rabbit.producer.sender.impl;

import com.google.common.base.Preconditions;
import com.lizza.rabbit.mq.api.entity.Message;
import com.lizza.rabbit.mq.api.enums.MessageStatus;
import com.lizza.rabbit.mq.api.enums.MessageType;
import com.lizza.rabbit.producer.entity.BrokerMessage;
import com.lizza.rabbit.producer.sender.MessageSender;
import com.lizza.rabbit.producer.service.BrokerMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Desc: 可靠消息, 发送之前先落库
 * @author: lizza.liu
 * @date: 2022-02-05
 */
@Slf4j
@Component
public class ReliantMessageSender implements MessageSender {

    /** 下一次重试的时间间隔 **/
    private static final int NEXT_RETRY_TIME_OUT = 30;

    @Resource
    private MessageHandler messageHandler;

    @Resource
    private BrokerMessageService brokerMessageService;

    @Override
    public void send(Message message) {
        Preconditions.checkNotNull(message.getTopic());
        message.setMessageType(MessageType.RELIANT);

        // 1. 先进行落库
        BrokerMessage brokerMessage = BrokerMessage.builder()
                .messageId(message.getMessageId())
                .message(message)
                .status(MessageStatus.SENDING.name())
                .nextRetry(DateUtils.addSeconds(new Date(), NEXT_RETRY_TIME_OUT))
                .build();
        brokerMessageService.insert(brokerMessage);

        // 2. 然后发送消息
        messageHandler.handle(message);
    }

    @Override
    public MessageType type() {
        return MessageType.RAPID;
    }
}
