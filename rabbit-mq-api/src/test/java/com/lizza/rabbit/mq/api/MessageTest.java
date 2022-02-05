package com.lizza.rabbit.mq.api;

import cn.hutool.core.lang.UUID;
import com.lizza.rabbit.mq.api.entity.Message;
import org.junit.Test;

/**
 * @Desc:
 * @author: lizza.liu
 * @date: 2022-02-05
 */
public class MessageTest {

    @Test
    public void test1() {
        Message message = Message.builder()
                .messageId(UUID.fastUUID().toString())
                .delaySeconds(10)
                .build();
        System.out.println(message);
    }
}
