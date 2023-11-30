package com.es.param;

import lombok.Data;

import java.util.List;

/**
 * @author runxiu.li
 * @Date 2023/4/24 10:45
 * @Description 选配字段
 */
@Data
public class EvsSubscriptionMatchConfigParam {

    /**
     * nomi类型，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> nomiType;


    /**
     * 卡钳类型，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> caliper;

    /**
     * moon套装(外饰套件)，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> moon;

    /**
     * 增强平视显示系统，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> enhancedHeadUpDisplaySystem;

    /**
     * 舒享套装（舒适套装），选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> comfortSet;

    /**
     * NP包，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> np;

    /**
     * NP- ES8包，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> npes8;

    /**
     * 音响系统，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> soundSystem;

    /**
     * nappa高级内饰套装，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> nappaType;


    /**
     * 创始版签名设计套装，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> foundingEditionSignatureSet;


    /**
     * 个性签名套装，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> personalizedSignatureSet;


    /**
     * 中控屏，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> centerControlPanel;


    /**
     * 女王副驾，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> queueSeat;


    /**
     * 智能香氛系统，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> intelligentFragranceSystem;


    /**
     * 集成式ETC，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> integratedEtc;


    /**
     * 增强显示套装，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> enhancedDisplaySet;

    //新增

    /**
     * 拖钩套装，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> decouplingSet;


    /**
     * 智能调光全景天幕，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> intelligentDimmingPanoramicCanopy;


    /**
     * n-box增强娱乐主机，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> nbox;


    /**
     * 六活塞卡钳制动套装，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> sixPistonCaliper;

    /**
     * 车顶行李架导航，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> roofRackNavigation;


    /**
     * 智能车载冰箱，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> smartCarRefrigerator;


    /**
     * 前风挡下沿加热，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> windshieldHeated;


    /**
     * 电加热座椅，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> electricallyHeatedSeats;


    /**
     * 能量无忧，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> worryFreeEnergy;


    /**
     * 服务无忧，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> worryFreeService;


    /**
     * 座椅
     * 6座、7座，选填，多选（因为多个车款时，会存在多个optionCode），支持反选
     */
    private List<String> seats;


}
