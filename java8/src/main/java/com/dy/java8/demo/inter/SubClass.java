package com.dy.java8.demo.inter;

/**
 * @author dingyu
 * @description 子类
 * @date 2019/11/8
 */
public class SubClass /*extends FatherClass */implements Myfun,MyFun2 {


    @Override
    public String getName() {
        //调用MyFun2接口默认方法
        return MyFun2.super.getName();
    }
}
