package com.dy.datastructure.recursion;

/**
 * @author dingyu
 * @description 递归阶乘问题
 * @date 2019/10/31
 */
public class FactorialDemo {


    public static  void main(String[] args) {
        int factorial = factorial(1); //结果为1
        System.out.println(factorial);
        int factorial1 = factorial(3); //结果为6
        System.out.println(factorial1);


    }

    //递归问题打印
    /**
     * 每调用一次factorial 则在栈空间开辟一个空间存储局部变量和参数
        例如调用factorial（3） 在它之上开辟了两个栈空间factorial(2),factorial(1)
        最先执行factorial（1） 将执行结果返回给上个调用得栈空间，一级级返回，直到执行到factorial（3） 之后，结束运行
     */
    public static int factorial(int num){
        if (num==1){
            return num;
        }else{
            return factorial(num-1)*num;
        }
    }
}
