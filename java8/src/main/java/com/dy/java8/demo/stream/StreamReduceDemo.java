package com.dy.java8.demo.stream;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author dingyu
 * @description
 * 归约:reduce 将流中的元素反复结合起来，得到一个值
 * @date 2019/10/29
 */
public class StreamReduceDemo {

    @Test
    public void test(){

        List<Integer> list= Arrays.asList(1,2,3,4,5,6,7,8);
        //从0开始 将从流中取出一个值1 将0+1 得到1 作为x，然后从流中取出一个值2 作为y 相加，依次累加
        Integer reduce = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println("归约："+reduce);
    }
}
