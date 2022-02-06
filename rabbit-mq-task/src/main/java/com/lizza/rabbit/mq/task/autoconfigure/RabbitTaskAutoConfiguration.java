package com.lizza.rabbit.mq.task.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @Desc:
 * @author: lizza.liu
 * @date: 2022-02-06
 */
@Configuration
@ConditionalOnProperty(prefix = "task.zk", name = {"serverLists", "namespace"})
public class RabbitTaskAutoConfiguration {


}
