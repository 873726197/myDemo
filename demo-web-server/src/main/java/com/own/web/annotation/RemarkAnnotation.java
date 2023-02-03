package com.own.web.annotation;

import java.lang.annotation.*;

/**
 * @author liuChang
 * @date 2023/2/3 15:42
 * @describe
 */

@Documented
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})  //可以在字段、枚举的常量、方法
@Retention(RetentionPolicy.RUNTIME)
public @interface RemarkAnnotation {
    String remark() default "";


}
