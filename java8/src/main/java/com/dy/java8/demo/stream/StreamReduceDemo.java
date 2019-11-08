package com.dy.java8.demo.stream;


import com.dy.java8.demo.entity.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author dingyu
 * @description
 * 归约:reduce 将流中的元素反复结合起来，得到一个值
 * @date 2019/10/29
 */
public class StreamReduceDemo {

    //模拟数据值
    List<Employee> employees=new ArrayList<>(Arrays.asList(
            new Employee("zhangsan",18,9999, Employee.Status.VOCATION),
            new Employee("lisi",19,8888,Employee.Status.BUSY),
            new Employee("wangwu",20,7777,Employee.Status.FREE),
            new Employee("liulu",21,4444, Employee.Status.VOCATION),
            new Employee("dingyu",38,5555, Employee.Status.FREE),
            new Employee("dingli",50,3333, Employee.Status.FREE),
            new Employee("wangkeshan",35,1111, Employee.Status.BUSY),
            new Employee("wangkeshan",35,1111, Employee.Status.BUSY),
            new Employee("wangkeshan",35,1111, Employee.Status.VOCATION),
            new Employee("wangkeshan",35,1111, Employee.Status.BUSY)
    ));

    @Test
    public void test(){

        List<Integer> list= Arrays.asList(1,2,3,4,5,6,7,8);
        //从0开始 将从流中取出一个值1 将0+1 得到1 作为x，然后从流中取出一个值2 作为y 相加，依次累加
        Integer reduce = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println("归约："+reduce);


        System.out.println("---------------------------------------------------------");

        //计算工资总和
        Optional<Double> reduce1 = employees.stream()
                .map(Employee::getMoney)
                .reduce(Double::sum);
        System.out.println("工资总和"+reduce1.get().doubleValue());

        //map -reduce 的连接通常称为map-reduce模式,因Google用它进行网络搜索而出名

    }

    @Test
    public void test2(){
        //收集
        // collect 一一将流转换为其他形式,接收一个Collector接口实现.用于Stream中元素做汇总
        List<String> collect = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);

        //去重
        Set<String> collect1 = employees.stream().map(Employee::getName).collect(Collectors.toSet());
        collect1.forEach(System.out::println);
        
        //存放到不同格式中
        HashSet<String> collect2 = employees.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
        collect2.forEach(System.out::println);
        
    }
    
    @Test
    public void test3(){
        
        //总数
        Long collect = employees.stream().collect(Collectors.counting());

        //可以直接计算获取总数 平均成绩 最大成绩等
        DoubleSummaryStatistics  dec = employees.stream().collect(Collectors.summarizingDouble(Employee::getMoney));
        dec.getSum();
        dec.getAverage();
        dec.getMax();


        //工资的平均值
        Double collect1 = employees.stream().collect(Collectors.averagingDouble(Employee::getMoney));

        //最大值
        Optional<Employee> collect2 = employees.stream().collect(Collectors.maxBy((e1, e2) ->
                Double.compare(e1.getMoney(), e2.getMoney())));
        System.out.println("最大值:"+collect2.get().getName());
        
        //最小值
        Optional<Employee> collect3 = employees.stream().collect(Collectors.minBy((e1, e2) -> Double.compare(e1.getMoney(), e2.getMoney())));
        System.out.println(collect3.get().getName());
        
    }
    
    @Test
    public void test4(){
        //分组 按照Status 进行分组
        Map<Employee.Status, List<Employee>> collect = employees.stream().collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println("分组结果:"+collect);

        //多级分组

        //先按照状态分组,然后按照年龄分组
        Map<Employee.Status, Map<String, List<Employee>>> collect1 = employees.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
            if (e.getAge() <= 35) {
                return "青年";
            } else if (e.getAge() <= 50) {
                return "中年";
            } else {
                return "老年";
            }
        })));
        System.out.println("多级分组结果:"+collect1);
        
    }
    
    @Test
    public  void test5(){
        //分区
        //按照工资是否大于8000  大于为一个区  小于为一个区
        Map<Boolean, List<Employee>> collect = employees.stream().collect(Collectors.partitioningBy((e) -> e.getMoney() > 8000));
        System.out.println(collect);

        

    }
}
