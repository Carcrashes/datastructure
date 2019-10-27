package com.dy.java8.demo.service.impl;

import com.dy.java8.demo.entity.Employee;
import com.dy.java8.demo.service.MyPredicate;

/**
 *根据工资进行比较
 */
public class PredicateByMoneyImpl implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getMoney()>5000;
    }
}
