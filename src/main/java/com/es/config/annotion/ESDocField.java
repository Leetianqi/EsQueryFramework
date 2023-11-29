package com.es.config.annotion;

import java.lang.annotation.*;

/**
 * 标记ES查询字段注解
 * @author runxiu.li
 * @version 1.0
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ESDocField {
    /**
     * ES 字段名称
     */
    String fieldName() default "";

    /**
     * 范围枚举定义
     */
    Range range() default Range.EMPTY;

    /**
     * 是否List查询
     * true只能用来修饰List
     */
    boolean isPlural() default false;

    /**
     * 反选
     * 不能用来修饰List
     */
    boolean isInverse() default false;

    /**
     * 是否可以为空
     * 可以修饰List、String(等)
     */
    boolean isCanBeEmpty() default false;

    /**
     * 是否模糊查询
     */
    boolean isFuzzy() default false;


    enum Range {
        EMPTY,
        START,
        END,

        /**
         * 指定的意思，说明字段为值
         */
        DESIGNATE;

        Range() {
        }

        public boolean isEmpty() {
            return this.equals(EMPTY);
        }

        public boolean isStart() {
            return this.equals(START);
        }

        public boolean isEnd() {
            return this.equals(END);
        }

        public boolean isDesignate() {
            return this.equals(DESIGNATE);
        }
    }
}
