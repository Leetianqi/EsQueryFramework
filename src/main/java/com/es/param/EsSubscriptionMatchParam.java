package com.es.param;


import cn.hutool.core.util.ObjectUtil;
import com.es.config.annotion.ESDocField;
import com.es.enums.OrderColumnEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 注:
 * 1.该参数为订阅查询的入参,理论上都是单个值，为了扩展性，某些字段提供了模糊查询
 * 2.该参数为订阅查询的入参,存在部分字段不支持查询的情况，部分排序字段参：com.nio.dd.vo.agg.shared.enums.OrderColumnEnum
 * <p>
 * 名词解释:
 * 1.可为空（isCanBeEmpty = true）:意思是对应的fieldName字段不存在时，也符合条件
 * 对应的字段如果是数组[]（isPlural = true）,当可为空时:如果数组[]为空，则不要拼接查询条件（即不需要此条件过滤）;如果数组[]不为空时,则拼接上字段可为空的条件
 * 对应的字段如果是Object(非数组),当可为空时:如果Object(非数组)为空，则不要拼接查询条件（即不需要此条件过滤）;如果Object(非数组)不为空时,则拼接上字段可为空的条件
 * 也就是说可为空（isCanBeEmpty = true），只针对对象不为空时，可以拼接上字段为空的过滤条件；当对象为空时，就不做处理
 * 2.不可为空（isCanBeEmpty = false）:意思是对应的fieldName字段不存在时，不符合条件
 * 同理
 * <p>
 * 3.支持反选（isInverse = true），只用来修饰非数组对象
 * 非数组对象为空时,需要构建条件即该字段为空,或者为0（此处和业务强相关）;当非数组对象不为空时,正常处理即可
 *
 * @author runxiu.li
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EsSubscriptionMatchParam extends EsSubscriptionQueryParam {

    /**
     * 车款,选填,多选,可为空
     */
    @ESDocField(fieldName = "config_info.generations", isPlural = true, isCanBeEmpty = true)
    private List<String> generations;

    /**
     * 外观颜色（车漆），选填，多选,可为空
     */
    @ESDocField(fieldName = "config_info.car_paints", isPlural = true, isCanBeEmpty = true)
    private List<String> carPaints;

    /**
     * 电池包，选填，多选,可为空
     */
    @ESDocField(fieldName = "config_info.batterys", isPlural = true, isCanBeEmpty = true)
    private List<String> batteries;

    /**
     * 轮毂，选填，多选,可为空
     */
    @ESDocField(fieldName = "config_info.wheels", isPlural = true, isCanBeEmpty = true)
    private List<String> wheels;

    /**
     * 内饰材料，选填，多选,可为空
     */
    @ESDocField(fieldName = "config_info.interior_material", isPlural = true, isCanBeEmpty = true)
    private List<String> interiorMaterial;

    /**
     * 内饰颜色（内饰主题），选填，多选,可为空
     */
    @ESDocField(fieldName = "config_info.interior_color", isPlural = true, isCanBeEmpty = true)
    private List<String> interiorColor;

    //订阅状态

    /**
     * 是否降级,非必填,单选,可为空
     */
    @ESDocField(fieldName = "status_info.is_downgrade", isCanBeEmpty = true)
    private Boolean isDowngrade;

    /**
     * 车辆属性,非必填,单选,不可为空
     */
    @ESDocField(fieldName = "config_info.vehicle_attribute", isCanBeEmpty = true)
    private String vehicleAttribute;


    /**
     * 生产库龄，不可为空(默认为0)，单位年
     */
    @ESDocField(fieldName = "status_info.product_storage_age", range = ESDocField.Range.DESIGNATE)
    private Long productStorageAge;

    /**
     * 在库库龄，不可为空(默认为0)，单位年
     */
    @ESDocField(fieldName = "status_info.location_storage_age", range = ESDocField.Range.DESIGNATE)
    private Long locationStorageAge;


    /**
     * 价格,非必填,单选,不可为空 待确定
     */
    @ESDocField(fieldName = "status_info.price", range = ESDocField.Range.DESIGNATE)
    private Double price;

    //订阅选配

    /**
     * 卡钳类型，选填，多选（因为多个车款时，会存在多个optionCode），支持反选，可为空
     */
    @ESDocField(fieldName = "matching_config.caliper", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> caliper;

    /**
     * 女王副驾，选填，多选（因为多个车款时，会存在多个optionCode），支持反选，可为空
     */
    @ESDocField(fieldName = "matching_config.queue_seat", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> queueSeat;

    private String sortOrder;
    private String sortField;
    private Integer offset;
    private Integer limit;

    public static EsSubscriptionMatchParam of(SearchSubscriptionParam param) {
        EsSubscriptionMatchParam esSubscriptionMatchParam = new EsSubscriptionMatchParam();
        var orderColumn = OrderColumnEnum.of(param.getOrderColumn());
        esSubscriptionMatchParam.setSortField(orderColumn.getColumn());
        esSubscriptionMatchParam.setSortOrder(param.getOrderAsc());
        esSubscriptionMatchParam.setLimit(param.getLimit());
        esSubscriptionMatchParam.setOffset(param.getOffset());
        wrapEsQueryParam(esSubscriptionMatchParam, param);

        return esSubscriptionMatchParam;
    }

    private static void wrapEsQueryParam(EsSubscriptionMatchParam esSubscriptionMatchParam, SearchSubscriptionParam param) {
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
            if (ObjectUtil.isNotNull(param.getEvsSubscriptionConfigParam().getModel())) {
                esSubscriptionMatchParam.setModel(param.getEvsSubscriptionConfigParam().getModel());
            }
            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionConfigParam().getGenerations())) {
                esSubscriptionMatchParam.setGenerations(param.getEvsSubscriptionConfigParam().getGenerations());
            }
            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionConfigParam().getCarPaints())) {
                esSubscriptionMatchParam.setCarPaints(param.getEvsSubscriptionConfigParam().getCarPaints());
            }
            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionConfigParam().getBatteries())) {
                esSubscriptionMatchParam.setBatteries(param.getEvsSubscriptionConfigParam().getBatteries());
            }
            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionConfigParam().getWheels())) {
                esSubscriptionMatchParam.setWheels(param.getEvsSubscriptionConfigParam().getWheels());
            }
            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionConfigParam().getInteriorMaterial())) {
                esSubscriptionMatchParam.setInteriorMaterial(param.getEvsSubscriptionConfigParam().getInteriorMaterial());
            }
            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionConfigParam().getInteriorColor())) {
                esSubscriptionMatchParam.setInteriorColor(param.getEvsSubscriptionConfigParam().getInteriorColor());
            }
        }

        //订阅状态
        if (param != null && param.getEvsSubscriptionStatusParam() != null) {
            //构建es查询参数
            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionStatusParam().getIsDowngrade())) {
                esSubscriptionMatchParam.setIsDowngrade(param.getEvsSubscriptionStatusParam().getIsDowngrade());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionStatusParam().getVehicleAttribute())) {
                esSubscriptionMatchParam.setVehicleAttribute(param.getEvsSubscriptionStatusParam().getVehicleAttribute());
            }


            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionStatusParam().getProductStorageAge())) {
                esSubscriptionMatchParam.setProductStorageAge(param.getEvsSubscriptionStatusParam().getProductStorageAge());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionStatusParam().getLocationStorageAge())) {
                esSubscriptionMatchParam.setLocationStorageAge(param.getEvsSubscriptionStatusParam().getLocationStorageAge());
            }


            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionStatusParam().getPrice())) {
                esSubscriptionMatchParam.setPrice(param.getEvsSubscriptionStatusParam().getPrice());
            }
        }
        //订阅选配
        if (param != null && param.getEvsSubscriptionMatchConfigParam() != null) {
            //构建es查询参数

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getCaliper())) {
                esSubscriptionMatchParam.setCaliper(param.getEvsSubscriptionMatchConfigParam().getCaliper());
            }
        }
    }

}