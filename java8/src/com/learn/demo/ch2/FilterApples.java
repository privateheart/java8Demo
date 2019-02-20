package com.learn.demo.ch2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * 小结：
 *      1.行为参数化，就是一个方法接受多个不同行为作为参数，并在内部使用它们，完成不同行为的能力
 *      2.行为参数化可以让代码更好地适应不断变化的要求，减轻未来的工作量
 *      3.传递代码，就是将新的行为作为参数传递给方法，但在java8之前这实现起来很啰嗦，为接口声明许多只用一次的实体类而造成的啰嗦代码，在java8之前可以用匿名类来减少
 *      4.java8 API中包含很多可以用不同行为进行参数化的方法，包括排序、线程和GUI处理
 */
public class FilterApples {

    private static List<Apple> apples = new ArrayList<>();

    static {
        for (int i=0; i<30; i++) {
            Apple apple = null;
            if (i % 2 == 0) {
                apple = new Apple("app" + i, "red", i + 40);
            } else {
                apple = new Apple("app" + i, "green", i + 35);
            }
            apples.add(apple);
        }
    }
    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for (T e: list){
            if (p.test(e)){
                result.add(e);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        filter(apples,(Apple a) -> a.getWeight()>40).forEach((Apple a) -> System.out.println(a.toString()));
    }
}
