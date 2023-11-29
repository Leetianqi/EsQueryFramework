package com.es.config.es;

/**
 * 为了保证一个订阅在 es 中只有一份儿数据需要使用能唯一代表一订阅的 subscriptionId 作为 ES 文档 ID，
 * 对 subscriptionId 号进行了脱敏处理，常见的算法有 MD5，SHA-256 等等
 * @author runxiu.li
 */
public interface DocumentIdProcessor {
    /**
     * 根据 subscriptionId 号生成 Document ID
     * @param subscriptionId
     * @return
     */
    String genDocId(String subscriptionId);
}
