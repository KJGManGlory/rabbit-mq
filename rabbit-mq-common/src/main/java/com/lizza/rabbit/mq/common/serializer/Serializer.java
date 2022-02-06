package com.lizza.rabbit.mq.common.serializer;

/**
 * @Desc: 序列化器
 * @author: lizza.liu
 * @date: 2022-02-06
 */
public interface Serializer {

    /**
     * 将 Java 对象序列化成字节数组
     * @param object    Java 对象
     * @return byte[]   字节数组
     */
    byte[] serializeRaw(Object object);

    /**
     * 将 Java 对象序列化成字符串
     * @param object                Java 对象
     * @return java.lang.String     字符串
     */
    String serialize(Object object);

    /**
     * 将字符串反序列化成 Java 对象
     * @param content   字符串
     * @return T        Java 对象
     */
    <T> T deserialize(String content);

    /**
     * 将字节数组反序列化成 Java 对象
     * @param content   字节数组
     * @return T        Java 对象
     */
    <T> T deserialize(byte[] content);
}
