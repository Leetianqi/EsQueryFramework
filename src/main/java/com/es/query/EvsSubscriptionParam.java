package com.es.query;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Date 2023/4/24 10:42
 * @author runxiu.li
 * @Description 订阅信息
 * 注:有哪些数据，便构造相关的查询条件
 */
@Data
public class EvsSubscriptionParam {

    /**
     * 订阅no
     */
    private String subscriptionNo;

    /**
     * 订阅名称
     */
    private String subscriptionName;

    /**
     * 订阅人accountId
     */
    private String subscriberAccountId;

    /**
     * 是否有效
     */
    private Boolean isValid = true;

    /**
     * 最新匹配时间，默认情况下为订阅创建时间
     */
    private LocalDateTime lastMatchTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
