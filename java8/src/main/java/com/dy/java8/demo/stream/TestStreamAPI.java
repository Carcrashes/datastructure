package com.dy.java8.demo.stream;

import com.dy.java8.demo.entity.Employee;
import org.junit.Test;

import javax.xml.ws.RequestWrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 练习
 */
public class TestStreamAPI {

    //模拟数据值
    List<Employee> employees=new ArrayList<>(Arrays.asList(
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

    /**
     * 给定一个数字列表,如何返回一个由每个数的平方构成的列表
        1,2 ->1 4
     */
    
    @Test
    public  void test(){

        //初始化列表
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);

        //map方式
        integers.stream().map((x) -> x * x).forEach(System.out::println);

        //map -reduce
    }


    /**
     * 统计员工的个数
     */
    @Test
    public void test2(){
        Optional<Integer> reduce = employees.stream().map((e) -> 1).reduce(Integer::sum);


    }
}

