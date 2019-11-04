package com.dy.java8.demo.lambda;

import com.dy.java8.demo.entity.Employee;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author dingyu
 * @description:
 *  方法引用：Lambda表达式中已经有实现，我们可以使用"方法引用方式"
 *  注意：Lambda 体中调用方法的参数列表与返回值类型，要与函数式接口的抽象方法的函数列表返回类型保持一致
 *  方式一：对象::实例方法名
 *  方式二：类:静态方法
 *  方式三:类:实力方法名
 *
 *  构造器引用：
 *  格式
 *  ClassName:new
 *
 *  数组引用：
 *  type[]:new
 * @date 2019/10/28
 */
public class LambdaDemo5 {

    /**
     * 对象::方法名
     */
    public void test(){
        //常见Lambda表达式
        Consumer<String> com=x-> System.out.println(x);
        com.accept("aaaaa");

        //对象::实例方法名
        PrintStream out = System.out;
        Consumer<String> consumer=out::println;
        Consumer<String> consumer1=x->out.println(x);
        consumer1.accept("bbbbbb");
    }

    /**
     * 对象：应用实例
     */
    public void test2(){
       //常用lambda方式
        Employee employee=new Employee("ls",18,9999);
        Supplier<String> supplier=()-> employee.getName();
        String name=supplier.get();

        //对象引用
        Supplier<Integer> supplier1=employee::getAge;
        Integer age=supplier1.get();
    }

    /**
     * 类:静态方法名
     */
    public void test3(){
        //常用Lambda表达式
        Comparator<Integer> comparable=(x, y)->Integer.compare(x,y);

        //类:静态方法名
        Comparator<Integer> comparator=Integer::compare;
        comparator.compare(1,2);
    }

    /**
     * 类:实例方法
     */
    public void test4(){
        //lambda 表达式方式
        BiPredicate<String,String> biPredicate=(x,y)->x.equals(y);

        //类:实例方法
        BiPredicate<String,String> biPredicate1=String::equals;
        //第一个参数是实例方法调用者，第二个参数是实例方法是参数时，则可以使用类::实例方法
        //"x" 是equals方法调用者，"y"equals 方法的参数
    }

    /**
     * 构造器引用
     */
    public void test5(){

        //lambda 表达式方式
        Supplier<Employee> supplier=()->new Employee();
        Employee employee = supplier.get();

        //构造器方式 匹配无参构造器
        Supplier<Employee> supplier1=Employee::new;
        Employee employee1 = supplier1.get();

        //构造器方式:有参构造 //lambda
        Function<Integer,Employee> function=(x)->new Employee(x);

        Function<Integer,Employee> function1=Employee::new;
        function1.apply(1);
    }


    public  void test6(){
        //lambda 表达式方式 生成一个数组
        Function<Integer,Integer[]> tag=(n)->new Integer[n];
        Integer[] apply = tag.apply(10);//初始化长度为10的数组

        //数组引用
        Function<Integer,Integer[]> tag1=Integer[]::new;
        Integer[] apply1 = tag1.apply(10);//初始化为10的数组

    }
}
