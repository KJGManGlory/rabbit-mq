package com.lizza.rabbit.producer.entity;

import com.lizza.rabbit.mq.api.entity.Message;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * @Desc:
 * @author: lizza.liu
 * @date: 2022-02-06
 */
@Data
@Builder
@NoArgsConstructor
public class BrokerMessage {

    /** 消息 id **/
    private String messageId;

    /** 消息内容 **/
    private Message message;

    /** 重试次数 **/
    private Integer tryCount;

    /** 消息状态(0: 发送中; 1: 发送成功; 2: 发送失败) **/
    private String status;

    /** 下次尝试时间 **/
    private Date nextRetry;

    /** 创建时间 **/
    private Date createTime;

    /** 更新时间 **/
    private Date updateTime;

}
