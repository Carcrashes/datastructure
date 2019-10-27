package com.dy.java8.demo.service.impl;

import com.dy.java8.demo.entity.Employee;
import com.dy.java8.demo.service.MyPredicate;

/**
 * 根据年龄进行比较
 */
public class PredicateByAgeImpl implements MyPredicate<Employee>{

    @Override
    public boolean test(Employee employee) {
        return employee.getAge()>35;
    }
}
