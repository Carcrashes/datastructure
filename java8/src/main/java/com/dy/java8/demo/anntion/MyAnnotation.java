package com.dy.java8.demo.anntion;

import java.lang.annotation.*;

/**
 * @author dingyu
 * @description
 * java8对注解提供两点改进：重复注解和类型注解
 * @date 2019/11/12
 */

@Repeatable(MyAnnotations.class)  //重复注解必须添加这个基础注解
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})  //作用范围
@Retention(RetentionPolicy.RUNTIME)  //生命周期
@Documented //文档说明
public @interface MyAnnotation {

    //定义默认注解
    String value() default "dy";
}
