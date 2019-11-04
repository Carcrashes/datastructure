package com.dy.java8.demo.entity;

import java.util.Objects;

/**
 * 员工基础信息
 */
public class Employee {

    private String name;

    private Integer age;

    private double money;

    private Status status;


    public Employee() {
    }

    public Employee(Integer age) {
        this.age = age;
    }

    public Employee(String name, Integer age, double money) {
        this.name = name;
        this.age = age;
        this.money = money;
    }

    public Employee(String name, Integer age, double money, Status status) {
        this.name = name;
        this.age = age;
        this.money = money;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.money, money) == 0 &&
                Objects.equals(name, employee.name) &&
                Objects.equals(age, employee.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, money);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", money=" + money +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public enum Status{
        BUSY,FREE,VOCATION,
    }
}


