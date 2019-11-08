package com.dy.java8.demo.inter;

import com.dy.java8.demo.service.MyFun;
import org.junit.Test;

/**
 * @author dingyu
 * @description 测试默认方法
 * @date 2019/11/8
 */
public class TestInterFace {

    @Test
    public void test(){
        SubClass subClass=new SubClass();
        //类优先原则
        //当子类的父类与子类实现接口方法名一致时候，先调用的是父类的方法。
        String name = subClass.getName();
        System.out.println(name);

        //接口冲突问题：
        //实现多个接口，接口中有方法名一致时候，必须重写方法名相同的方法
        String name1 = subClass.getName();
        System.out.println(name1);

        String show = Myfun.show();
        System.out.println(show);

    }
}
