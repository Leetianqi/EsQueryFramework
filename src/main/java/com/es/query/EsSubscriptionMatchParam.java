package com.es.query;


import cn.hutool.core.util.ObjectUtil;
import com.es.config.annotion.ESDocField;
import com.es.enums.OrderColumnEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 注:
 * 1.该参数为订阅查询的入参,理论上都是单个值，为了扩展性，某些字段提供了模糊查询
 * 2.该参数为订阅查询的入参,存在部分字段不支持查询的情况，部分排序字段参：com.nio.dd.vo.agg.shared.enums.OrderColumnEnum
 * <p>
 * 名词解释:
 * 1.可为空（isCanBeEmpty = true）:意思是对应的fieldName字段不存在时，也符合条件
 * 对应的字段如果是数组[],当可为空时:如果数组[]为空，则不要拼接查询条件（即不需要此条件过滤）;如果数组[]不为空时,则拼接上字段可为空的条件
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
@Data
@Slf4j
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

    /**
     * 第一把钥匙，选填，多选,可为空
     */
    @ESDocField(fieldName = "config_info.first_key", isPlural = true, isCanBeEmpty = true)
    private List<String> firstKey;

    /**
     * 第2把钥匙，选填，多选,可为空
     */
    @ESDocField(fieldName = "config_info.second_key", isPlural = true, isCanBeEmpty = true)
    private List<String> secondKey;

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
     * 展车时长,不可为空(默认为0)，单位年
     */
    @ESDocField(fieldName = "status_info.show_time", range = ESDocField.Range.DESIGNATE)
    private Long showTime;

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
     * 制造状态,非必填,单选,不可为空
     */
    @ESDocField(fieldName = "status_info.manufacturing_state", isPlural = true, isCanBeEmpty = true)
    private List<String> manufacturingState;

    /**
     * 物流状态,非必填,单选,不可为空
     */
    @ESDocField(fieldName = "status_info.logistics_state", isPlural = true, isCanBeEmpty = true)
    private List<String> logisticsState;

    /**
     * 物理库位,非必填,单选,不可为空
     */
    @ESDocField(fieldName = "status_info.location", isPlural = true, isCanBeEmpty = true)
    private List<String> location;

    /**
     * 价格,非必填,单选,不可为空 待确定
     */
    @ESDocField(fieldName = "status_info.price", range = ESDocField.Range.DESIGNATE)
    private Double price;

    //订阅选配

    /**
     * nomi类型，选填，多选（因为多个车款时，会存在多个optionCode），可为空，支持反选
     */
    @ESDocField(fieldName = "matching_config.nomi_type", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> nomiType;

    /**
     * 卡钳类型，选填，多选（因为多个车款时，会存在多个optionCode），支持反选，可为空
     */
    @ESDocField(fieldName = "matching_config.caliper", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> caliper;

    /**
     * moon套装(外饰套件)，选填，多选（因为多个车款时，会存在多个optionCode），支持反选，可为空
     */
    @ESDocField(fieldName = "matching_config.moon", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> moon;

    /**
     * 增强平视显示系统，选填，多选（因为多个车款时，会存在多个optionCode），支持反选，可为空
     */
    @ESDocField(fieldName = "matching_config.enhanced_headup_display_system", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> enhancedHeadUpDisplaySystem;

    /**
     * 舒享套装（舒适套装），选填，多选（因为多个车款时，会存在多个optionCode），支持反选，可为空
     */
    @ESDocField(fieldName = "matching_config.comfort_set", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> comfortSet;

    /**
     * NP包，选填，多选（因为多个车款时，会存在多个optionCode），支持反选，可为空
     */
    @ESDocField(fieldName = "matching_config.np", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> np;

    /**
     * NP- ES8包，选填，多选（因为多个车款时，会存在多个optionCode），支持反选，可为空
     */
    @ESDocField(fieldName = "matching_config.npes8", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> npes8;

    /**
     * 音响系统，选填，多选（因为多个车款时，会存在多个optionCode），支持反选，可为空
     */
    @ESDocField(fieldName = "matching_config.sound_system", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> soundSystem;

    /**
     * nappa高级内饰套装，选填，多选（因为多个车款时，会存在多个optionCode），支持反选，可为空
     */
    @ESDocField(fieldName = "matching_config.nappa_type", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> nappaType;

    /**
     * 创始版签名设计套装，选填，多选（因为多个车款时，会存在多个optionCode），支持反选，可为空
     */
    @ESDocField(fieldName = "matching_config.founding_edition_signature_set", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> foundingEditionSignatureSet;

    /**
     * 个性签名套装，选填，多选（因为多个车款时，会存在多个optionCode），支持反选，可为空
     */
    @ESDocField(fieldName = "matching_config.personalized_signature_set", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> personalizedSignatureSet;

    /**
     * 中控屏，选填，多选（因为多个车款时，会存在多个optionCode），支持反选，可为空
     */
    @ESDocField(fieldName = "matching_config.center_control_panel", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> centerControlPanel;

    /**
     * 女王副驾，选填，多选（因为多个车款时，会存在多个optionCode），支持反选，可为空
     */
    @ESDocField(fieldName = "matching_config.queue_seat", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> queueSeat;

    /**
     * 智能香氛系统，选填，多选（因为多个车款时，会存在多个optionCode），支持反选，可为空
     */
    @ESDocField(fieldName = "matching_config.intelligent_fragrance_system", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> intelligentFragranceSystem;

    /**
     * 集成式ETC，选填，多选（因为多个车款时，会存在多个optionCode），支持反选，可为空
     */
    @ESDocField(fieldName = "matching_config.integrated_Etc", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> integratedEtc;

    /**
     * 增强显示套装，选填，多选（因为多个车款时，会存在多个optionCode），支持反选，可为空
     */
    @ESDocField(fieldName = "matching_config.enhanced_display_set", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> enhancedDisplaySet;


    /**
     * 拖钩套装，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    @ESDocField(fieldName = "matching_config.decoupling_set", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> decouplingSet;


    /**
     * 智能调光全景天幕，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    @ESDocField(fieldName = "matching_config.intelligent_dimming_panoramic_canopy", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> intelligentDimmingPanoramicCanopy;


    /**
     * n-box增强娱乐主机，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    @ESDocField(fieldName = "matching_config.nbox", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> nbox;


    /**
     * 六活塞卡钳制动套装，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    @ESDocField(fieldName = "matching_config.six_piston_caliper_brake_package", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> sixPistonCaliper;

    /**
     * 车顶行李架导航，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    @ESDocField(fieldName = "matching_config.roof_rack_navigation", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> roofRackNavigation;


    /**
     * 智能车载冰箱，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    @ESDocField(fieldName = "matching_config.smart_car_refrigerator", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> smartCarRefrigerator;


    /**
     * 前风挡下沿加热，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    @ESDocField(fieldName = "matching_config.windshield_heated", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> windshieldHeated;


    /**
     * 电加热座椅，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    @ESDocField(fieldName = "matching_config.electrically_heated_seats", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> electricallyHeatedSeats;


    /**
     * 能量无忧，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    @ESDocField(fieldName = "matching_config.worry_free_energy", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> worryFreeEnergy;


    /**
     * 服务无忧，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    @ESDocField(fieldName = "matching_config.worry_free_service", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> worryFreeService;


    /**
     * 座椅
     * 6座、7座，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    @ESDocField(fieldName = "matching_config.seats", isPlural = true, isInverse = true, isCanBeEmpty = true)
    private List<String> seats;

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
            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionConfigParam().getFirstKey())) {
                esSubscriptionMatchParam.setFirstKey(param.getEvsSubscriptionConfigParam().getFirstKey());
            }
            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionConfigParam().getSecondKey())) {
                esSubscriptionMatchParam.setSecondKey(param.getEvsSubscriptionConfigParam().getSecondKey());
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

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionStatusParam().getShowTime())) {
                esSubscriptionMatchParam.setShowTime(param.getEvsSubscriptionStatusParam().getShowTime());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionStatusParam().getProductStorageAge())) {
                esSubscriptionMatchParam.setProductStorageAge(param.getEvsSubscriptionStatusParam().getProductStorageAge());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionStatusParam().getLocationStorageAge())) {
                esSubscriptionMatchParam.setLocationStorageAge(param.getEvsSubscriptionStatusParam().getLocationStorageAge());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionStatusParam().getManufacturingState())) {
                esSubscriptionMatchParam.setManufacturingState(param.getEvsSubscriptionStatusParam().getManufacturingState());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionStatusParam().getLogisticsState())) {
                esSubscriptionMatchParam.setLogisticsState(param.getEvsSubscriptionStatusParam().getLogisticsState());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionStatusParam().getLocation())) {
                esSubscriptionMatchParam.setLocation(param.getEvsSubscriptionStatusParam().getLocation());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionStatusParam().getPrice())) {
                esSubscriptionMatchParam.setPrice(param.getEvsSubscriptionStatusParam().getPrice());
            }
        }

        //订阅选配
        if (param != null && param.getEvsSubscriptionMatchConfigParam() != null) {
            //构建es查询参数
            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getNomiType())) {
                esSubscriptionMatchParam.setNomiType(param.getEvsSubscriptionMatchConfigParam().getNomiType());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getCaliper())) {
                esSubscriptionMatchParam.setCaliper(param.getEvsSubscriptionMatchConfigParam().getCaliper());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getMoon())) {
                esSubscriptionMatchParam.setMoon(param.getEvsSubscriptionMatchConfigParam().getMoon());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getEnhancedHeadUpDisplaySystem())) {
                esSubscriptionMatchParam.setEnhancedHeadUpDisplaySystem(param.getEvsSubscriptionMatchConfigParam().getEnhancedHeadUpDisplaySystem());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getComfortSet())) {
                esSubscriptionMatchParam.setComfortSet(param.getEvsSubscriptionMatchConfigParam().getComfortSet());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getNp())) {
                esSubscriptionMatchParam.setNp(param.getEvsSubscriptionMatchConfigParam().getNp());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getNpes8())) {
                esSubscriptionMatchParam.setNpes8(param.getEvsSubscriptionMatchConfigParam().getNpes8());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getSoundSystem())) {
                esSubscriptionMatchParam.setSoundSystem(param.getEvsSubscriptionMatchConfigParam().getSoundSystem());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getNappaType())) {
                esSubscriptionMatchParam.setNappaType(param.getEvsSubscriptionMatchConfigParam().getNappaType());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getFoundingEditionSignatureSet())) {
                esSubscriptionMatchParam.setFoundingEditionSignatureSet(param.getEvsSubscriptionMatchConfigParam().getFoundingEditionSignatureSet());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getPersonalizedSignatureSet())) {
                esSubscriptionMatchParam.setPersonalizedSignatureSet(param.getEvsSubscriptionMatchConfigParam().getPersonalizedSignatureSet());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getCenterControlPanel())) {
                esSubscriptionMatchParam.setCenterControlPanel(param.getEvsSubscriptionMatchConfigParam().getCenterControlPanel());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getQueueSeat())) {
                esSubscriptionMatchParam.setQueueSeat(param.getEvsSubscriptionMatchConfigParam().getQueueSeat());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getIntelligentFragranceSystem())) {
                esSubscriptionMatchParam.setIntelligentFragranceSystem(param.getEvsSubscriptionMatchConfigParam().getIntelligentFragranceSystem());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getIntegratedEtc())) {
                esSubscriptionMatchParam.setIntegratedEtc(param.getEvsSubscriptionMatchConfigParam().getIntegratedEtc());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getEnhancedDisplaySet())) {
                esSubscriptionMatchParam.setEnhancedDisplaySet(param.getEvsSubscriptionMatchConfigParam().getEnhancedDisplaySet());
            }
            //新增
            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getDecouplingSet())) {
                esSubscriptionMatchParam.setDecouplingSet(param.getEvsSubscriptionMatchConfigParam().getDecouplingSet());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getIntelligentDimmingPanoramicCanopy())) {
                esSubscriptionMatchParam.setIntelligentDimmingPanoramicCanopy(param.getEvsSubscriptionMatchConfigParam().getIntelligentDimmingPanoramicCanopy());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getNbox())) {
                esSubscriptionMatchParam.setNbox(param.getEvsSubscriptionMatchConfigParam().getNbox());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getSixPistonCaliper())) {
                esSubscriptionMatchParam.setSixPistonCaliper(param.getEvsSubscriptionMatchConfigParam().getSixPistonCaliper());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getRoofRackNavigation())) {
                esSubscriptionMatchParam.setRoofRackNavigation(param.getEvsSubscriptionMatchConfigParam().getRoofRackNavigation());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getSmartCarRefrigerator())) {
                esSubscriptionMatchParam.setSmartCarRefrigerator(param.getEvsSubscriptionMatchConfigParam().getSmartCarRefrigerator());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getWindshieldHeated())) {
                esSubscriptionMatchParam.setWindshieldHeated(param.getEvsSubscriptionMatchConfigParam().getWindshieldHeated());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getElectricallyHeatedSeats())) {
                esSubscriptionMatchParam.setElectricallyHeatedSeats(param.getEvsSubscriptionMatchConfigParam().getElectricallyHeatedSeats());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getWorryFreeEnergy())) {
                esSubscriptionMatchParam.setWorryFreeEnergy(param.getEvsSubscriptionMatchConfigParam().getWorryFreeEnergy());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getWorryFreeService())) {
                esSubscriptionMatchParam.setWorryFreeService(param.getEvsSubscriptionMatchConfigParam().getWorryFreeService());
            }

            if (ObjectUtil.isNotEmpty(param.getEvsSubscriptionMatchConfigParam().getSeats())) {
                esSubscriptionMatchParam.setSeats(param.getEvsSubscriptionMatchConfigParam().getSeats());
            }
        }
    }

}