package com.dy.java8.demo.date;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * @author dingyu
 * @description
 *  时间格式转换
 * @date 2019/11/11
 */
public class DateTimeFormatDemo {

    @Test
    public void test(){
        DateTimeFormatter dtf=DateTimeFormatter.ISO_DATE;//定义日期转换格式
        LocalDateTime ldt=LocalDateTime.now();
        String format = dtf.format(ldt);
        System.out.println(format);

        //自定义格式
        DateTimeFormatter dtfDefine=DateTimeFormatter.ofPattern("yyyyMMdd");
        //转换为String
        String format1 = dtfDefine.format(ldt);
        System.out.println(format1);

        //String 转换为Date
        LocalDateTime localDateTime=LocalDateTime.now();
        LocalDateTime parse = localDateTime.parse(format, dtfDefine);
        System.out.println(parse);
    }

    /**
     * ZoneDate  ZonedTime ZoneDateTime
     */
    @Test
    public void test2(){
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();  //获取所有时区
        availableZoneIds.stream()
                .forEach(System.out::println);

        //指定时区构建时间
        LocalDateTime localDateTime=LocalDateTime.now(ZoneId.of("Europe/Malta"));
        System.out.println(localDateTime);

        //带时区的时间格式

    }
}
