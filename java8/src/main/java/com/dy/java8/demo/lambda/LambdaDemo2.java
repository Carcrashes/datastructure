package com.dy.java8.demo.lambda;

import com.dy.java8.demo.service.MyFun;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 一 lambda 表达式:
 *     基础语法: java 8中新引入了 “->” 符号
 *     左侧:Lambda表达式的参数列表
 *     右侧：表达式中所需的执行的j
 *
 *    语法格式一：无参数，无返回值
 *      ()->system.out.println()
 *    语法各式二:有参数，无返回值
 *    （x）->system.out.println(“”)
 *    语法格式三：只有一个参数，小括号可以不写
 *    x -> system.out.println(x)
 *    语法风格四:有两个以上参数，并且Lambda体中有多条语句的话，必须使用大括号
 *    （x,y）->{
 *
 *    }
 *    语法格式五:lambda体 只有一套语句，return he 大括号都可以不写
 *    （x，y）-> Integer.compare(x,y)
 *
 *   语法格式六：lambda表达式的数据类型可以不写，因为jvm可以通过上下文推断数据类型
 *
 *   Lambda 表达式 需要函数式接口支持:(接口中只有一个抽象方法) 可以使用@FunctionalInterface 修饰可以检查是否是函数式接口
 *
 */
public class LambdaDemo2 {


    /**
     * 无参数，无返回值
     */
    public void test() {
        int num = 0;//jdk 1,7前，必须是final

        //匿名内部类方式
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world" + num);

            }
        };

        r.run();
        //lambda 表达式方式
        System.out.println("------------------------");
        Runnable r1 = () -> System.out.println("lambda 表达式方式");
        r1.run();

    }


    /**
     *语法方式:有参数无返回值。
     */
    public void test2(String str) {
        Consumer<String> c=x-> System.out.println("有参数，无返回值:"+x);
        c.accept(str);
    }


    /**
     * 有多个参数，多条语句时候。需要使用大括号
     */
    public  void test3(){
        Comparator<Integer> comparator=(x,y)->{
            System.out.println("x:"+x+","+"y"+y);
            return Integer.compare(x,y);

        };
    }

    /**
     * 多个参数，单条语句时候
     */
    public void  test4(){
        Comparator<Integer> comparator=(x,y) -> Integer.compare(x,y);
    }

    /**
     * 测试计算值
     */
    public void test5(){
        Integer operation = operation(100, x -> x * x);
        System.out.println(operation);
        Integer operation1 = operation(200, x -> 200 + 200);
        System.out.println(operation1);
    }
    
    // 计算值
    public Integer operation(Integer x, MyFun myFun){
        return myFun.calcuator(x);
    }
    
    
}
