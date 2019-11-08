package com.dy.java8.demo.entity;

import java.util.Optional;

/**
 * @author dingyu
 * @description Optional方式
 * @date 2019/11/8
 */
public class NewMan {

    private Optional<GodNess> godNess=Optional.empty(); //不赋值，默认值为Optional空实例

    public NewMan(Optional<GodNess> godNess) {
        this.godNess = godNess;
    }

    public NewMan() {
    }

    public Optional<GodNess> getGodNess() {
        return godNess;
    }

    public void setGodNess(Optional<GodNess> godNess) {
        this.godNess = godNess;
    }
}
