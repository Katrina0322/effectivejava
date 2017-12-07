package com.oneapm.test;

/**
 * filename: Man
 * Description:
 * Author: ubuntu
 * Date: 12/7/17 2:32 PM
 */
public class Man implements Human {

    private String name;
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
