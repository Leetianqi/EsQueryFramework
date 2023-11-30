package com.es.param;


import cn.hutool.core.util.ObjectUtil;
import com.es.config.annotion.ESDocField;
import com.es.enums.OrderColumnEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


/**
 * 注:
 * 1.该参数为订阅查询的入参,理论上都是单个值，为了扩展性，某些字段提供了模糊查询
 * 2.该参数为订阅查询的入参,存在部分字段不支持查询的情况，部分排序字段参：OrderColumnEnum
 * <p>
 * 只是用来查询的接口
 * 因为
 *
 * @author runxiu.li
 */
@Data
@Slf4j
public class EsSubscriptionQueryParam {
    //订阅基本信息
    /**
     * 订阅no,非必填,单选,不可为空
     */
    @ESDocField(fieldName = "subscription_info.subscription_no")
    private String subscriptionNo;

    /**
     * 订阅人id,非必填,单选,不可为空
     */
    @ESDocField(fieldName = "subscription_info.subscriber_account_id")
    private String subscriberAccountId;

    /**
     * 订阅名称,非必填,单选,不可为空
     */
    @ESDocField(fieldName = "subscription_info.subscription_name")
    private String subscriptionName;

    /**
     * 订阅是否有效,非必填(默认为true),单选,不可为空
     */
    @ESDocField(fieldName = "subscription_info.is_valid")
    private Boolean isValid = true;

    //订阅配置
    /**
     * 车型,非必填,单选,不可为空
     */
    @ESDocField(fieldName = "config_info.model")
    private String model;

    private String sortOrder;
    private String sortField;
    private Integer offset;
    private Integer limit;

    public static EsSubscriptionQueryParam of(SearchSubscriptionParam param) {
        EsSubscriptionQueryParam esSubscriptionMatchParam = new EsSubscriptionQueryParam();
        var orderColumn = OrderColumnEnum.of(param.getOrderColumn());
        esSubscriptionMatchParam.setSortField(orderColumn.getColumn());
        esSubscriptionMatchParam.setSortOrder(param.getOrderAsc());
        esSubscriptionMatchParam.setLimit(param.getLimit());
        esSubscriptionMatchParam.setOffset(param.getOffset());
        wrapEsQueryParam(esSubscriptionMatchParam, param);

        return esSubscriptionMatchParam;
    }

    private static void wrapEsQueryParam(EsSubscriptionQueryParam esSubscriptionMatchParam, SearchSubscriptionParam param) {
        //订阅信息
        if (param != null && param.getEvsSubscriptionParam() != null) {
            //构建es查询参数
            if (param.getEvsSubscriptionParam().getSubscriptionNo() != null) {
                esSubscriptionMatchParam.setSubscriptionNo(param.getEvsSubscriptionParam().getSubscriptionNo());
            }
            if (param.getEvsSubscriptionParam().getSubscriptionName() != null) {
                esSubscriptionMatchParam.setSubscriptionName(param.getEvsSubscriptionParam().getSubscriptionName());
            }
            if (param.getEvsSubscriptionParam().getSubscriberAccountId() != null) {
                esSubscriptionMatchParam.setSubscriberAccountId(param.getEvsSubscriptionParam().getSubscriberAccountId());
            }
            if (param.getEvsSubscriptionParam().getIsValid() != null) {
                esSubscriptionMatchParam.setIsValid(param.getEvsSubscriptionParam().getIsValid());
            }
        }

        //订阅配置
        if (param != null && param.getEvsSubscriptionConfigParam() != null) {
            //构建es查询参数
            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionConfigParam().getModel())) {
                esSubscriptionMatchParam.setModel(param.getEvsSubscriptionConfigParam().getModel());
            }
        }
    }

}