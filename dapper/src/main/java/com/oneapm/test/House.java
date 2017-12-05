package com.oneapm.test;

/**
 * filename: House
 * Description:
 * Author: ubuntu
 * Date: 12/5/17 5:20 PM
 */
public class House {
    private String address;
    private int big;
    private double price;

    public House() {
    }

    public House(String address, int big, double price) {
        this.address = address;
        this.big = big;
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public int getBig() {
        return big;
    }

    public double getPrice() {
        return price;
    }
}
