package com.dy.java8.demo.lambda;

import com.dy.java8.demo.entity.Employee;
import com.dy.java8.demo.service.MyPredicate;
import com.dy.java8.demo.service.impl.PredicateByAgeImpl;
import com.dy.java8.demo.service.impl.PredicateByMoneyImpl;

import java.security.PublicKey;
import java.util.*;
import java.util.stream.Collectors;

/**
 * java8 lambda表达式新特性
 */

public class LambdaDemo {

    //需求:计算员工年龄大于25的
    //假如需要用工资来进行比较,又需要重新写一种方法,这时候代码实际冗余
    //传统方式 遍历集合比较 返回集合对象
    public List<Employee>getEmpsByAge(List<Employee> list){
        List<Employee> employees=new ArrayList<>();
        for (Employee item:employees){
            if (item.getAge()>25){
                employees.add(item);
            }
        }
        return employees;
    }

    public List<Employee>getEmpsByMoney(List<Employee> list){
        List<Employee> employees=new ArrayList<>();
        for (Employee item:employees){
            if (item.getAge()>5000){
                employees.add(item);
            }
        }
        return employees;
    }


    //优化方式一:使用设计模式来进行优化（策略设计模式）
    public List<Employee>getEmps(List<Employee> list, MyPredicate<Employee> mp){
        List<Employee> employees=new ArrayList<>();
        for (Employee item:employees){
            if (mp.test(item)){
                employees.add(item);
            }
        }
        return employees;
    }

    //优化方式二 匿名内部类
    public List<Employee> getEmps(List<Employee> list){
        return getEmps(list, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge()>35;
            }
        });
    }

    //优化方式三 Lambda 表达式
    public List<Employee> getEmpsByLamda(List<Employee> list){
        //使用lambda简化代码
        List<Employee> emps = getEmps(list, employee -> employee.getAge() > 25);
        return emps;
    }

    //优化方式四 Stream API
    public List<Employee> getEmpsByStream(List<Employee> list){
        return list.stream()
                .filter(employee -> employee.getAge()>25) //根据条件过滤
                .limit(1) // 限制数量
                .collect(Collectors.toList()); //转换为集合
    }


    public static void main(String[] args) {

        //匿名静态内部类
        Comparator<Integer> com=new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        //应用匿名内部类
        TreeSet<Integer> set=new TreeSet<>(com);


        //Lambda 表达式写法(替换内部类方式)
        Comparator<Integer> comLambda=(x,y)->Integer.compare(x,y);
        TreeSet<Integer> lambdaSet=new TreeSet<>(comLambda);


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


        LambdaDemo demo=new LambdaDemo();

        //测试年龄大于25
        List<Employee> emps=demo.getEmps(list,new PredicateByAgeImpl());

        //测试工资大于5000
        List<Employee> employees=demo.getEmps(list,new PredicateByMoneyImpl());

    }




}
