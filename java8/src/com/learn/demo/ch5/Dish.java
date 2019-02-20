package com.learn.demo.ch5;

/**
 * @author : huyi
 * @Project: UserStreamDemo
 * @Package com.lear.demo
 * @Description: java8 Stream 例子使用
 * @date Date : 2019年02月16日 16:45
 */
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    public enum Type {MEAT, FISH, OTHER}
}
