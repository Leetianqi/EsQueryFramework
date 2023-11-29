package com.es.enums;

import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author runxiu.li
 */

@Getter
public enum OrderColumnEnum {

    LAST_MATCH_TIME("subscription_info.last_match_time"),
    /**
     * 不需要默认 要把它删除掉
     */
    DEFAULT(""),
    ;

    private final String column;

    OrderColumnEnum(String column) {
        this.column = column;
    }

    public static OrderColumnEnum of(String column) {
        return Stream.of(values()).filter(s -> column.equalsIgnoreCase(s.name())).findAny().orElse(DEFAULT);
    }
}
