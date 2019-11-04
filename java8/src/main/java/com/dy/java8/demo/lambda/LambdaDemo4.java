package com.dy.java8.demo.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java 8 内置的四大核心函数式接口
 *
 * Comsumer<T> :消费型接口
 *  void accept(T t)
 * Supplier<T>:供给型接口
 *  T get();
 *  Function<T,R>:函数式接口
 *  R apply(T t);
 *  PreDicate<T>:断言型接口，
 *  boolean test(T t)；
 *
 */
public class LambdaDemo4 {


    //Comsumer<T>  消费型接口
    public void test(){
        comsumer(100,x->{
            System.out.println("消费了:"+x);
        });
    }

    public void  comsumer(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }

    //Supplier 供给型接口
    public void  test2(){
        //产生10随机数
        getNumList(10,()->(int)(Math.random()*100));
    }

    /**
     * 指定产生个数，并放入集合中
     * @param num
     * @param supplier
     * @return
     */
    public List<Integer> getNumList(int num, Supplier<Integer> supplier){
        List<Integer> ls=new ArrayList<>();
        for (int i=0;i<num;i++){
            ls.add(i);
        }
        return ls;

    }

    /**
     * 处理str
     */
    public void test3(){
        strHandler("xyz",x->x.toUpperCase());//转换为大写
        strHandler("xyz",x->x.substring(0,1));//截取
    }

   // Function<T,R>:函数式接口
    public String strHandler(String str, Function<String,String> function){
        return function.apply(str);
    }


    // PreDicate<T>:断言型接口
    public void test4(){
        List<String> list = filterString(Arrays.asList("xyz", "zzz", "xy"), x -> x.equals("X"));
        list.stream().forEach(c->{
            System.out.println(c);
        });
    }

    //需求：将满足条件的字符串放入集合中，
    public List<String> filterString(List<String> stringList, Predicate<String> predicate) {
        List<String> list=new ArrayList<>();
        for (String str:stringList){
            if (predicate.test(str)){
                list.add(str);
            }
        }
        return list;
    }
}
