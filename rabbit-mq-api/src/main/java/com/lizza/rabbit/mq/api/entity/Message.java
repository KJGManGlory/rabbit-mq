package com.lizza.rabbit.mq.api.entity;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.lizza.rabbit.mq.api.enums.MessageType;
import com.lizza.rabbit.mq.api.exception.MessageException;
import com.lizza.rabbit.mq.api.exception.MessageRunTimeException;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

/**
 * @Desc: 消息实体类, 建造者模式
 * @author: lizza.liu
 * @date: 2022-02-05
 */
@ToString
@EqualsAndHashCode
public class Message implements Serializable {

    private static final long serialVersionUID = 6754367981961829384L;

    /** 消息 ID **/
    private String messageId;

    /** 消息主题, 对应于 Exchange **/
    private String topic;

    /** 路由 key, 可以设置通配符 **/
    private String routingKey = "";

    /** 自定义的一些属性 **/
    private Map<String, Object> attributes = Maps.newHashMap();

    /** 延迟时间, 单位: 秒 **/
    private int delaySeconds;

    /** 消息类型 **/
    private MessageType messageType = MessageType.RAPID;

    private Message() {
    }

    private Message(String messageId, String topic, String routingKey, Map<String, Object> attributes, int delaySeconds, MessageType messageType) {
        this.messageId = messageId;
        this.topic = topic;
        this.routingKey = routingKey;
        this.attributes = attributes;
        this.delaySeconds = delaySeconds;
        this.messageType = messageType;
    }

    public static Message builder() {
        return new Message();
    }

    public Message messageId(String messageId) {
        this.messageId = messageId;
        return this;
    }

    public Message topic(String topic) {
        this.topic = topic;
        return this;
    }

    public Message routingKey(String routingKey) {
        this.routingKey = routingKey;
        return this;
    }

    public Message delaySeconds(int delaySeconds) {
        this.delaySeconds = delaySeconds;
        return this;
    }

    public Message messageType(MessageType messageType) {
        this.messageType = messageType;
        return this;
    }

    public Message build() {
        if (StrUtil.isBlank(this.messageId)) {
            this.messageId = UUID.randomUUID().toString();
        }

        if (StrUtil.isBlank(this.topic)) {
            throw new MessageRunTimeException("message topic is null");
        }

        return this;
    }
}
