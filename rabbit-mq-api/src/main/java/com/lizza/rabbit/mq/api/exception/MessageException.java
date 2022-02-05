package com.lizza.rabbit.mq.api.exception;

/**
 * @Desc:
 * @author: lizza.liu
 * @date: 2022-02-05
 */
public class MessageException extends Exception {

    private static final long serialVersionUID = -2702353165123858968L;

    public MessageException() {
        super();
    }

    public MessageException(String message) {
        super(message);
    }

    public MessageException(Throwable throwable) {
        super(throwable);
    }

    public MessageException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
