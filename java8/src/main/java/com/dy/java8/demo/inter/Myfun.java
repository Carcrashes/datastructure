package com.dy.java8.demo.inter;

/**
 * @author dingyu
 * @description java8中接口默认方法：
 *  java 可以具体实现的方法，就是默认方法
 * @date 2019/11/8
 */
public interface Myfun {

    /**
     * 默认方法可以进行实现
     * @return
     */
    default String getName() {
        return "哈哈哈";
    }

    /**
     * 静态方法可以有实现
     * @return
     */
    public static String show(){
        return "接口中的静态方法";
    }
}
