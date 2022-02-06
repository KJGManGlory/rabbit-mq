package com.lizza.rabbit.producer.service;

import com.lizza.rabbit.mq.api.enums.MessageStatus;
import com.lizza.rabbit.producer.entity.BrokerMessage;
import com.lizza.rabbit.producer.mapper.BrokerMessageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Desc:
 * @author: lizza.liu
 * @date: 2022-02-06
 */
@Service
public class BrokerMessageService {

    @Resource
    private BrokerMessageMapper brokerMessageMapper;

    public int insert(BrokerMessage message) {
        return brokerMessageMapper.insert(message);
    }

    public int changeMessageStatus(String messageId, MessageStatus messageStatus) {
        return brokerMessageMapper.changeMessageStatus(messageId, messageStatus.name());
    }
}
