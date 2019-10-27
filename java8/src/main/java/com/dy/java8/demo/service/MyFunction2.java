package com.dy.java8.demo.service;

/**
 * 两个Long 处理
 * @param <T>
 * @param <R>
 */
@FunctionalInterface
public interface MyFunction2<T,R> {

    public R getValue(Long x,Long y);
}
