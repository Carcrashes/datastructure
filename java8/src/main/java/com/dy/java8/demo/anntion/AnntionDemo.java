package com.dy.java8.demo.anntion;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

public class AnntionDemo {

    @Test
    public void test() throws Exception {
        //反射获取实例
        Class<AnntionDemo> clazz = AnntionDemo.class;
        //获取方法
        Method method = clazz.getMethod("show");
        //获取方法上面的注解
        MyAnnotation[] annotation = method.getAnnotationsByType(MyAnnotation.class);

        Stream<MyAnnotation> stream = Arrays.stream(annotation); //创建一个流
        stream.map(e->e.value()).forEach(System.out::println);


    }

    @MyAnnotation("hello")
    @MyAnnotation("world")
    public void show(){

    }
}
