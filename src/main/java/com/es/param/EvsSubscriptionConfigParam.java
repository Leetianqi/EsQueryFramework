package com.es.param;

import lombok.Data;

import java.util.List;

/**
 * @Date 2023/4/24 10:43
 * @author runxiu.li
 * @Description 订阅配置入参
 *
 */
@Data
public class EvsSubscriptionConfigParam {

    /**
     * 车型，必填，单选
     */
    private String model;

    /**
     * 车款，选填，多选
     */
    private List<String> generations;

    /**
     * 外观颜色（车漆），选填，多选
     */
    private List<String> carPaints;

    /**
     * 电池包，选填，多选
     */
    private List<String> batteries;

    /**
     * 轮毂，选填，多选
     */
    private List<String> wheels;

    /**
     * 内饰材料，选填，多选
     */
    private List<String> interiorMaterial;

    /**
     * 内饰颜色（内饰主题），选填，多选
     */
    private List<String> interiorColor;

    /**
     * 第一把钥匙，选填，多选
     */
    private List<String> firstKey;

    /**
     * 第2把钥匙，选填，多选
     */
    private List<String> secondKey;

}
