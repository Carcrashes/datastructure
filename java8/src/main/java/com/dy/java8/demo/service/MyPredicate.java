package com.dy.java8.demo.service;

@FunctionalInterface
public interface MyPredicate<T> {

    public boolean test(T t);
}
