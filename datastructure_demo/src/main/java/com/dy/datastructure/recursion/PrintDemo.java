package com.dy.datastructure.recursion;

/**
 * @author dingyu
 * @description 递归打印
 * @date 2019/10/31
 */
public class PrintDemo {

    public static void main(String[] args) {
        test(4); //调用结果2 3 4
    }

    /**
     * 递归调用
     *  每调用一次test() 在栈区重新生成一个空间，先执行最上层栈空间
     *      最先调用test(2) 然后输出2 ，返回到上个一个调用得栈空间，依次执行
     *      再执行test（3） 然后输出3，
     *      最后执行到test（4） 退出程序
     *
     *  将代码修改为
     *  if(num>2){
     *      test(num-1)
     *  }else{
     *      System.out.println();
     *  }
     *  此时，输出结果为2 只有2 满足else条件
     *
     * @param num
     */
    public static void test(Integer num){
        if (num>2){
            test(num-1);
        }
        System.out.println(num);
    }
}
