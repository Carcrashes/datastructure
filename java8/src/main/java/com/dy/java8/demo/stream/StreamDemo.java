package com.dy.java8.demo.stream;

import com.dy.java8.demo.entity.Employee;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author dingyu
 * @description
 *  Stream操作步骤
 *  1。创建Stream
 *
 *
 *  2.中间操作
 *
 *
 *  3.终止操作
 *
 * @date 2019/10/29
 */
public class StreamDemo {


    /**
     * 创建Stream
     */
    @Test
    public void test(){

        //通过list集合的stream()方法
        List<String> list=new ArrayList<>();
        Stream<String> stream = list.stream();

        //Arrays中的静态方法Stream() 获取Stream流对象
        Employee[] employees=new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(employees);
        
        //通过Stream.of() 方式获取Stream
        Stream<String> aaa = Stream.of("aaa", "bbb", "ccc");

        //无限流
        //迭代 产生偶数
        Stream<Integer> iterate = Stream.iterate(0,(x) -> x + 2);
        iterate.limit(10) //产生十个(中间操作)
                .forEach(System.out::println); //终止操作

        //生成
        Stream.generate(()->Math.random()*36)
            .limit(10)    //限制10个数产生中间操作
            .forEach(System.out::println);//产生随机数 并操作
    }

}
