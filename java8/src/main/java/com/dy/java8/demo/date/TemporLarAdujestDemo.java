package com.dy.java8.demo.date;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * @author dingyu
 * @description 时间校正器案例
 * @date 2019/11/11
 */
public class TemporLarAdujestDemo {

    @Test
    public void test(){
        LocalDateTime localDateTime=LocalDateTime.now();
        localDateTime.withDayOfMonth(10);//设置为10号
        System.out.println(localDateTime);

        LocalDateTime localDate = localDateTime.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));//设置下个周日
        System.out.println(localDate);
    }

    /**
     * 自定义工作日
     */
    @Test
    public  void test2(){

        LocalDateTime dateTime=LocalDateTime.now();

        LocalDateTime with = dateTime.with(l -> {
            LocalDateTime localDateTime = (LocalDateTime) l;
            DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return localDateTime.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SUNDAY)){
                return localDateTime.plusDays(2);
            }else{
                return localDateTime.plusDays(1);
            }
        });

        System.out.println(with);
    }

}
