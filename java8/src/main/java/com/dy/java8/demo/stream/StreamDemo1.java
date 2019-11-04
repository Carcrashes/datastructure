package com.dy.java8.demo.stream;

import com.dy.java8.demo.entity.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author dingyu
 * @description
 *  Stream 中间操作
 *  filter() 接收lambda 。过滤某些元素
 *  limit() 截断流，使其元素不超过给定数量
 *  skip() 跳过元素，返回扔掉前几个元素的流，元素不足n个，则返回空流
 *  distinct 筛选，通过流产生的hashcode 和equls 方法去除重复元素
 * @date 2019/10/29
 */
public class StreamDemo1 {


    //模拟数据值
    List<Employee> list=new ArrayList<>(Arrays.asList(
            new Employee("zhangsan",18,9999),
            new Employee("lisi",19,8888),
            new Employee("wangwu",20,7777),
            new Employee("liulu",21,4444),
            new Employee("dingyu",38,5555),
            new Employee("dingli",50,3333),
            new Employee("wangkeshan",35,1111),
            new Employee("wangkeshan",35,1111),
            new Employee("wangkeshan",35,1111),
            new Employee("wangkeshan",35,1111)
    ));


    //filter操作
    public void test(){
        //filter 操作
        Stream<Employee> employeeStream = list.stream()
                .filter(e -> {
                    System.out.println("中间操作！");
                    return e.getAge() > 25;
                });  //中间操作 产生的是一个新Stream流，中间操作代码不执行，只有触发了终止操作才一次性执行中间操作代码

        //终止操作 内部迭代
        employeeStream.forEach(System.out::println); //终止操纵
    }

    //limit操作
    public void test2(){
        list.stream()
                .filter(employee -> {
                    System.out.println("短路");
                    return employee.getMoney()>3333;
                })
                .limit(2)
                .forEach(System.out::println);
    }

    //disctinct
    public void test3(){
        //distinct 需要新生成的元素重写hashcode和equels方法
        list.stream()
                .distinct()
                .forEach(System.out::println);

    }
}
