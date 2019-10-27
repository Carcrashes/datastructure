package com.dy.java8.demo.lambda;

import com.dy.java8.demo.entity.Employee;
import com.dy.java8.demo.service.MyFunction;
import com.dy.java8.demo.service.MyFunction2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LambdaDemo3 {

    //模拟数据值
    List<Employee> list=new ArrayList<>(Arrays.asList(
            new Employee("zhangsan",18,9999),
            new Employee("lisi",19,8888),
            new Employee("wangwu",20,7777),
            new Employee("liulu",21,4444),
            new Employee("dingyu",38,5555),
            new Employee("dingli",50,3333),
            new Employee("wangkeshan",35,1111)
    ));

    /**
     * 排序:先按照年龄排序，然后按照姓名排序
     */

    public void test(){
        Collections.sort(list,(x,y )->{
            if (x.getAge()==y.getAge()){
                return x.getName().compareTo(y.getName());
            }else {
                // -Integer.compare(x.getAge(),y.getAge()); 按照年龄倒序排
                return Integer.compare(x.getAge(),y.getAge());
            }
        });

        for (Employee e:list){
            System.out.println(e);
        }

    }

    /**
     * 对字符串处理测试
     */
    public void test2(){
        //对字符串进行不同处理
        strHandler("x y",x->x.trim());//去除空格
        strHandler("x",x->x+"123"); //拼接
        strHandler("xyz",x->x.substring(1,2)); //截取
        strHandler("x",x->x.toUpperCase());//转为大写
    }


    public String strHandler(String str, MyFunction mf){
        return mf.getValue(str);
    }

    /**
     * 计算long值
     */
    public void test3(){
        op(1l,2l,(x,y)->x*y);
    }

    //声明带两个泛型的函数式接口，泛型类型为<T,R> T为参数，R为返回值
    public Long op(Long l1, Long l2, MyFunction2<Long,Long> myFunction2){
        return myFunction2.getValue(l1,l2);
    }
}
