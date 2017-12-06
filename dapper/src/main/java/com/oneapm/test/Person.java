package com.oneapm.test;


import java.util.Arrays;

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

    public Person() {
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Person(int age, String name, String[] address) {
        this.age = age;
        this.name = name;
        this.address = address;
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

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", address=" + Arrays.toString(address) +
                '}';
    }
}
