package com.dy.java8.demo.entity;

/**
 * @author dingyu
 * @description
 * @date 2019/11/8
 */
public class Man {

    private GodNess godNess;

    public Man() {
    }

    public Man(GodNess godNess) {
        this.godNess = godNess;
    }

    public GodNess getGodNess() {
        return godNess;
    }

    public void setGodNess(GodNess godNess) {
        this.godNess = godNess;
    }
}
