package com.lizza.base;

import cn.hutool.socket.protocol.MsgDecoder;
import com.google.common.collect.Maps;
import com.lizza.base.producer.MsgProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Desc:
 * @author: lizza.liu
 * @date: 2022-02-05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqMessageTest {

    @Resource
    private MsgProducer<String> msgProducer;

    @Test
    public void test1() {
        Map<String, Object> properties = Maps.newHashMap();
        properties.put("attr1", "1234");
        properties.put("attr2", "abcd");
        msgProducer.send("Hello Rabbit MQ!", properties);
    }
}
