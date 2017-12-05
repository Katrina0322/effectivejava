package com.oneapm.test;

import java.util.List;

/**
 * filename: Person
 * Description:
 * Author: ubuntu
 * Date: 12/5/17 5:19 PM
 */
public class Person {
    private int age;
    private String name;
    private String[] address;
    private List<House> houses;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Person(int age, String name, String[] address, List<House> houses) {
        this.age = age;
        this.name = name;
        this.address = address;
        this.houses = houses;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String[] getAddress() {
        return address;
    }

    public List<House> getHouses() {
        return houses;
    }
}
