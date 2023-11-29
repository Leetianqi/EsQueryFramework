package com.es.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author runxiu.li
 * @Date 2023/4/23 15:03
 * @Description 查询订阅入参
 * 注：查询参数和文档是不同的
 * 查询参数为了扩展性,在使用数组存放的字段,都提供了模糊查询
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchSubscriptionParam {

    /**
     * 排序字段 默认 default
     * 默认情况 按照匹配时间倒序
     */
    private String orderColumn = "last_match_time";

    /**
     * 排序顺序
     */
    private String orderAsc = "desc";


    /**
     * 订阅基础信息
     */
    private EvsSubscriptionParam evsSubscriptionParam;

    /**
     * 订阅的相关配置
     */
    private EvsSubscriptionConfigParam evsSubscriptionConfigParam;

    /**
     * 订阅的相关状态
     * 列表查询时：该字段为空
     */
    private EvsSubscriptionStatusParam evsSubscriptionStatusParam;

    /**
     * 订阅的相关选配
     * 列表查询时：该字段为空
     */
    private EvsSubscriptionMatchConfigParam evsSubscriptionMatchConfigParam;

    private Integer limit = 10;
    private Integer offset = 0;


}
