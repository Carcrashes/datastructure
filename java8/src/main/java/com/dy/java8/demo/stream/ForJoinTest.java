package com.dy.java8.demo.stream;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;

import java.sql.SQLOutput;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

/**
 * @author dingyu
 * @description 测试ForkJoin进行累加
 * @date 2019/11/8
 */
public class ForJoinTest {

    /**
     * 利用ForkJoin进行计算
     */
    @Test
    public void test(){
        Instant start = Instant.now();//计算时间戳
        ForkJoinPool pool=new ForkJoinPool();
        ForkJoin forkJoin=new ForkJoin(0,10000000000l);
        Long sum = pool.invoke(forkJoin);
        System.out.println("输出结果:"+sum);
        Instant end=Instant.now();
        System.out.println("计算时间为:"+ Duration.between(start,end).toMillis()); //转换为毫秒

    }

    /**
     * java8的并行流
     */
    @Test
    public void test2(){
        Instant start = Instant.now();//计算时间戳
        //串行流
        LongStream.rangeClosed(0,1000000000l)
                .reduce(0,Long::sum);//从0开始累加
        Instant end=Instant.now();
        System.out.println("串行计算时间为:"+ Duration.between(start,end).toMillis()); //转换为毫秒

        start = Instant.now();
        //切换为并行流  更高的cpu利用率
        LongStream.rangeClosed(0,1000000000l)
                .parallel()
                .reduce(0,Long::sum);
        end=Instant.now();
        System.out.println("并行计算时间为:"+ Duration.between(start,end).toMillis()); //转换为毫秒

    }


}
