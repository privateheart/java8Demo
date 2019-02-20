package com.learn.demo.ch5.trade;

/**
 * @author : huyi
 * @Project: java8Demo
 * @Package com.learn.demo.ch5.trade
 * @Description: 交易员
 * @date Date : 2019年02月20日 19:02
 */
public class Trader {
    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
