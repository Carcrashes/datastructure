package com.dy.java8.demo.stream;

import com.dy.java8.demo.entity.Employee;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dingyu
 * @description
 *  Stream 排序
 *      -- 自然排序
 *          sorted()
 *      -- 定制排序 自定义排序规则
 *          sorted(Comparator com)
 * @date 2019/10/29
 */
public class StreamSortDemo {

    //模拟数据值
    List<Employee> list=new ArrayList<>(Arrays.asList(
            new Employee("zhangsan",18,9999),
            new Employee("lisi",19,8888),
            new Employee("wangwu",20,7777),
            new Employee("liulu",21,4444),
            new Employee("dingyu",38,5555),
            new Employee("dingli",50,3333),
            new Employee("wangkeshan",35,1111),
            new Employee("aaaaaaaaa",35,1111),
            new Employee("bbbbbbbbbb",35,1111)
    ));

    @Test
    public void test(){
        List<String> stringList= Arrays.asList("ccc","aaa","bbb","ddd");
        //自然排序
        stringList.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("-------------------------------------------");
        list.stream()
                .sorted((e1,e2) ->{
                    if (e1.getAge()==e2.getAge()){
                        return e1.getName().compareTo(e2.getName());
                    }else {
                        return e1.getAge().compareTo(e2.getAge());
                    }
                })
                .forEach(System.out::println);

    }

}
