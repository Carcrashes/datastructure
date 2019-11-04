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
 *  映射：
 *  Map -- 接收lambda ,将元素转换成其他形式或提取信息，接受一个函数为参数，该函数会应用到每个元素上面，并将其映射到一个新的元素
 *  flatMap  -- 接受一个函数作为参数，将流中的每个值都转换为另一个流，然后把所有的流连到一起
 * @date 2019/10/29
 */
public class StreamMapDemo {


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

    @Test
    public void test(){
        //测试map提取类中属性
        list.stream()
                .map(employee -> employee.getName())
                .forEach(System.out::println);

        //将函数方法作用到集合中的每一个元素
        List<String> strs=Arrays.asList("aa","bb","cc");
        strs.stream()
                .map(str -> str.toUpperCase())
                .forEach(System.out::println);

        System.out.println("-----------------------------------------------------");
        //map 和 flatMap
        //通过map 返回的是一个Stream<Character>  将每个值转成流，加入到新流中，形成一个流的集合
        Stream<Stream<Character>> stream = strs.stream().map(StreamMapDemo::filterCharacter);
        stream.forEach((sm)->{
            sm.forEach(System.out::println);
        });

        //flatMap调用 将每个元素放入新流中，转换为一个流，连接一个流
        Stream<Character> characterStream = strs.stream().flatMap(StreamMapDemo::filterCharacter);
        characterStream.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> characters=new ArrayList<>();
        for (Character ch:str.toCharArray()){
            characters.add(ch);
        }
        return characters.stream();
    }

}
