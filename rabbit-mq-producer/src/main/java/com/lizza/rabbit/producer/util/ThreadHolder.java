package com.lizza.rabbit.producer.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Desc: 线程池管理类
 * @author: lizza.liu
 * @date: 2022-02-05
 */
@Slf4j
public class ThreadHolder {

    public static final ExecutorService ASYNC_MESSAGE_SENDER = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors(),
            60L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10000),
            runnable -> new Thread(runnable, "async_message_sender_thread"),
            (runnable, executor) -> {
                log.error("async message sender thread pool reject exception, runnable:{}, executor: {}",
                        runnable, executor);
            });
}
