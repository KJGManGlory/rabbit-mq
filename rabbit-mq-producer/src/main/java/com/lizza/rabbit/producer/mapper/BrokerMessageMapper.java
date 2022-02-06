package com.lizza.rabbit.producer.mapper;

import com.lizza.rabbit.producer.entity.BrokerMessage;
import org.apache.ibatis.annotations.Param;

/**
 * @Desc:
 * @author: lizza.liu
 * @date: 2022-02-06
 */
public interface BrokerMessageMapper {

    int insert(BrokerMessage message);

    int changeMessageStatus(@Param("messageId") String messageId,
                            @Param("messageStatus") String messageStatus);
}
