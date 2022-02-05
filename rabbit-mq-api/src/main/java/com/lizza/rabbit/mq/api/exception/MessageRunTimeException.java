package com.lizza.rabbit.mq.api.exception;

/**
 * @Desc:
 * @author: lizza.liu
 * @date: 2022-02-05
 */
public class MessageRunTimeException extends RuntimeException {

    private static final long serialVersionUID = -2702353165123858968L;

    public MessageRunTimeException() {
        super();
    }

    public MessageRunTimeException(String message) {
        super(message);
    }

    public MessageRunTimeException(Throwable throwable) {
        super(throwable);
    }

    public MessageRunTimeException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
