package com.learn.demo.ch5;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : huyi
 * @Project: UserStreamDemo
 * @Package com.lear.demo
 * @Description:  Stream Api使用例子
 * @date Date : 2019年02月16日 16:53
 */
public class StreamDemo {
    static List<Dish> menu;
    static {
        menu = Arrays.asList(
                new Dish("pork",false,800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
    }

    /**
     * Stream 用谓词筛选
     */
    public void testFilter1(){
        //检查是否适合素食者
        menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
    }

    /**
     * 筛选各异的元素
     */
    public void testFilterAndDistinct(){
        List<Integer> numbers = Arrays.asList(1,2,1,3,3,2,4);
        numbers.stream().filter(i -> i%2 ==0).distinct().forEach(System.out::println);
    }

    /**
     * 截断流
     */
    public void testLimit()
    {
        List<Dish> higCaloryMenu = menu.stream().filter(d -> d.getCalories() > 300).limit(3).collect(Collectors.toList());
    }

    /**
     * 跳过元素
     */
    public void testSkip(){
        List<Dish> lowCaloryMenu = menu.stream().filter(d -> d.getCalories() > 300).skip(2).collect(Collectors.toList());
    }

    /**
     * 映射  返回菜单的名称集合
     */
    public void testMap(){
        List<String> names = menu.stream().map(Dish::getName).collect(Collectors.toList());
    }

    /**
     * 流的扁平化(将多个数据流，合并成一个流)  Stream<Stream<String>> 变成 Stream<String>
     * Arrays.stream() 的方法，接受一个数组并产生一个流
     * String[] arrayOfWords = {"Goodbye","World"};
     * Stream<String> streamOfWords = Arrays.stream(arrayOfWords);
     */
    public void testFlatMap(){
        //获取所有 菜的名字所用到的 字母。
        List<String> names = menu.stream()
                .map(Dish::getName)
                .map(name -> name.split(""))//将单词转换成由其字母构成的数组
                .flatMap(Arrays::stream)//将各个生成流扁平化为单个流
                .distinct().collect(Collectors.toList());

        //给定列表 [1,2,3] 和列表 [3,4],应该返回[(1,3),(1,4),(2,3),(2,4),(3,3),(3,4)]
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> collect = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());

        //扩展，只返回总和能被3整除的数对

        List<int[]> list = numbers1.stream().flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j})).collect(Collectors.toList());
        //检查是否所有的元素 都是这样的
        boolean allMatch = numbers1.stream().allMatch(i -> i % 2 == 0);
        //检查是否存在一个这样的元素
        boolean anyMatch = numbers1.stream().anyMatch(i -> i % 2 == 0);
        //检查是否不存在 这样的元素
        boolean noneMatch = numbers1.stream().noneMatch(i -> i % 3 == 0);

        //获取第一个元素
        Optional<Integer> first = numbers1.stream().findFirst();
        //获取任意一个元素
        Optional<Integer> any = numbers1.stream().findAny();
        System.out.println("first=" + first);
        System.out.println("any=" + any);

        //查找第一个能被3整除的元素    不用比较所有的元素
        numbers1.stream().filter(i -> i%3==0).findFirst();
    }

    /**
     * 测试 规约（将流中所有元素反复结合起来，得到一个值） Reduce
     */
    @Test
    public void testReduce(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Integer sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        //找到卡路里最高的一道菜
        Dish powerfulDish = menu.stream()
                .reduce(new Dish("default",false, 100, Dish.Type.OTHER), (d1, d2) -> {
                    return d1.getCalories() > d2.getCalories() ? d1 : d2;
                });
        System.out.println("最高卡路里菜是：" + powerfulDish.getName() + "  "+ powerfulDish.getCalories() + "卡路里");
    }
}
