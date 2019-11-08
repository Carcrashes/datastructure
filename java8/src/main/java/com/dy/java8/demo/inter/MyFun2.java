package com.dy.java8.demo.inter;
/**
 * 测试接口方法相同
 */
public interface MyFun2 {

    default String getName(){
        return "呵呵和";
    }
}
