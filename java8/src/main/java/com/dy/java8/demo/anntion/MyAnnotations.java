package com.dy.java8.demo.anntion;

import java.lang.annotation.*;

/**
 * @author dingyu
 * @description 重复注解和类型注解
 * @date 2019/11/12
 */

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})  //作用范围
@Retention(RetentionPolicy.RUNTIME)  //生命周期
@Documented //文档说明
public @interface MyAnnotations {

        MyAnnotation[] value();

}
