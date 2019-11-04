package com.dy.java8.demo.stream;

import com.dy.java8.demo.entity.Employee;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author dingyu
 * @description 查找与匹配
 *  allMatch: 检查是否匹配所有元素
 *  anyMatch: 检查是否至少匹配一个元素
 *  noneMatch:检查是否没有匹配元素
 *  findFirst:返回第一个元素
 *  findAny:返回流中的任意元素
 *  count:返回流中的总个数
 *  max：返回流中最大值
 *  min: 返回流中最小值
 *
 * @date 2019/10/29
 */
public class StreamMatchAndFind {

    //模拟数据值
    List<Employee> list=new ArrayList<>(Arrays.asList(
            new Employee("zhangsan",18,9999, Employee.Status.BUSY),
            new Employee("lisi",19,8888,Employee.Status.FREE),
            new Employee("wangwu",20,7777,Employee.Status.VOCATION),
            new Employee("liulu",21,4444,Employee.Status.BUSY),
            new Employee("dingyu",38,5555,Employee.Status.FREE),
            new Employee("dingli",50,3333,Employee.Status.VOCATION),
            new Employee("wangkeshan",35,1111, Employee.Status.VOCATION)
    ));

    @Test
    public void test(){
        //测试allMatch  检测匹配所有元素
        boolean b = list.stream()
                .allMatch(employee -> {
                    return employee.getStatus().equals(Employee.Status.BUSY);
                });
        System.out.println("测试结果:"+b);
        System.out.println("----------------------------------------------");

        //anyMatch 至少匹配一个元素
        boolean b1 = list.stream()
                .anyMatch(employee -> {
                    return employee.getStatus().equals(Employee.Status.BUSY);
                });
        System.out.println("测试结果:"+b1);

        //noneMatch 没有元素匹配
        list.stream()
                .noneMatch(employee -> {
                   return   employee.getStatus().equals(Employee.Status.BUSY);
                });

        //findFirst 查找第一个元素
        Optional<Employee> first = list.stream()
                .filter(employee -> {
                    return employee.getName().equals("lisi");
                })
                .findFirst();
        first.orElse(null);//如果first为空 则设置first为null
        Employee employee = first.get();//得到一个结果对象 

        //findAny 查找随意一个空闲状态员工
        Optional<Employee> any = list.stream()
                .filter(employee1 -> {
                    return employee.getStatus().equals(Employee.Status.FREE);
                })
                .findAny();
        Employee employee1 = any.get();//查找任意一个空闲的数据

        //并行
        Optional<Employee>  em = list.parallelStream()
                .filter(em1 -> {
                    return em1.getStatus().equals(Employee.Status.FREE);
                })
                .findAny();

    }

    @Test
    public void test2(){
        //统计流中个数
        long count = list.stream().count();
        System.out.println("个数:"+count);

        //查找最大值
        Optional<Employee> max = list.stream().max((e1, e2) -> Double.compare(e1.getMoney(), e2.getMoney()));
        Employee employee = max.get();
        System.out.println("最多工资员工："+employee);

        //查找最少工资
        Optional<Double> min = list.stream()
                .map(employee1 -> employee.getMoney())
                .min(Double::compareTo);
        System.out.println("最少工资:"+min.get());

    }

}
