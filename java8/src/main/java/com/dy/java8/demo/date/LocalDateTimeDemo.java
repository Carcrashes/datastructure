package com.dy.java8.demo.date;

import java.time.*;

/**
 * @author dingyu
 * @description java8的新特性 时间日期
 *      LocalDate  日期
 *      LocalDateTime 日期和时间
 *      LocalTime 时间
 * @date 2019/11/11
 */
public class LocalDateTimeDemo {

    public static void main(String[] args) {
        LocalDateTime dateTime=LocalDateTime.now();

        System.out.println(dateTime);

        LocalDateTime dateTime1=LocalDateTime.of(2019,11,11,13,22,23);
        System.out.println(dateTime1);

        LocalDateTime dateTime2 = dateTime.plusYears(1);  //增加一年 还有plusDays() 等方法，都会产生一个新的实例。这样是线程安全
        System.out.println(dateTime2);
        System.out.println(dateTime2.getMonth());
        System.out.println(dateTime2.getDayOfMonth()); //月中某日
        System.out.println(dateTime2.getDayOfWeek()); //周中某日
        System.out.println(dateTime2.getDayOfYear()); //年中的第多少天


        //Instant时间戳 以1970年1月1日到某个时间的毫秒值，
        Instant instant=Instant.now();//获取当前时间的时间戳 默认获取以UTC时区
        System.out.println(instant);

        //偏移值
        OffsetDateTime offset=instant.atOffset(ZoneOffset.ofHours(8));//以第八时区时间为标准
        System.out.println(offset);
        System.out.println(instant.toEpochMilli());//毫秒

        Instant instant1 = Instant.ofEpochSecond(60);
        System.out.println(instant1);


        //计算时间间隔
        //Duration :计算时间间隔
        //Period:计算日期之间间隔

        Instant now = Instant.now();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant now2=Instant.now();
        Duration between = Duration.between(now, now2);
        System.out.println(between.getNano());//获取相差纳秒
        System.out.println(between.getSeconds());//获取相差秒
        System.out.println(between.toMillis());//毫秒
        System.out.println(between.toNanos());//纳秒


        //日期 LocalDate
        LocalDate localDate=LocalDate.now();
        LocalDate ofDate = LocalDate.of(2015, 1, 12);

        //计算日期之间间隔
        Period period=Period.between(ofDate,localDate);
        System.out.println(period.getDays());
        System.out.println(period.getMonths());
        System.out.println(period.toTotalMonths());

    }
}
