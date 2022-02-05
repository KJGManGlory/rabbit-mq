package com.lizza.rabbit.mq.api.enums;

/**
 * @Desc: 消息类型
 * @author: lizza.liu
 * @date: 2022-02-05
 */
public enum MessageType {

    /** 快速消息: 不需要保证可靠性, 也不需要做 confirm 确认 **/
    RAPID("1", "迅速消息"),

    /** 确认消息: 不需要保证可靠性, 但是需要做 confirm 确认 **/
    CONFIRM("2", "确认消息"),

    /** 可靠消息: 需要保证消息 100% 投递成功, 不允许消息丢失; 消息和数据库中的数据是原子性的, 达到最终一致性 **/
    RELIANT("3", "可靠消息");

    public String code;
    public String desc;

    MessageType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
