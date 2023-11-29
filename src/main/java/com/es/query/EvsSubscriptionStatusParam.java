package com.es.query;

import lombok.Data;

import java.util.List;

/**
 * @Date 2023/4/24 10:44
 * @author runxiu.li
 * @Description 订阅状态字段
 */
@Data
public class EvsSubscriptionStatusParam {

    /**
     * 降级，必填，单选
     *
     */
    private Boolean isDowngrade;


    /**
     * 车辆属性，选填，单选
     */
    private String vehicleAttribute;

    /**
     * 展车时长，选填，单选
     */
    private Long showTime;

    /**
     * 生产库龄，选填，单选
     */
    private Long productStorageAge;

    /**
     * 在库库龄，选填，单选
     */
    private Long locationStorageAge;

    /**
     * 制造状态，选填，多选
     */
    private List<String> manufacturingState;

    /**
     * 物流状态，选填，多选
     */
    private List<String> logisticsState;

    /**
     * 物理库位，选填，多选
     */
    private List<String> location;

    /**
     * 价格，选填，单选
     */
    private Double price;

}
