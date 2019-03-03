package com.learn.demo.ch5;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author : huyi
 * @Project: java8
 * @Package com.learn.demo.ch5
 * @Description: 构建流
 * @date Date : 2019年02月23日 11:07
 */
public class BuildStreamDemo {

    public static void main(String[] args) {
        //由值创建流 Stream.of(T....)  构建 Stream<T>
        Stream<String> stream = Stream.of("Java 8", "Lambda ", "In ", "Actions");
        stream.map(String::toUpperCase).forEach(System.out::println);

        //由数组创建流
        int[] numbers = {2,3,5,7,11,13};
        int sum = Arrays.stream(numbers).sum();//求和

        //由文件生成流
        Path path = Paths.get("resource/data.txt");
        System.out.println(path);
        try (Stream<String> lines = Files.lines(path, Charset.defaultCharset())){
            //一共有多少个不相同的单词
            Stream<String> distinct = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct();
            distinct.forEach(System.out::println);
//            System.out.println("一共有" + distinct.count() + "不相同的单词");  //forEach 消费了，这一行 再消费distinct流就报错了
        }catch (IOException e){
            System.out.println("路径错误！");
        }

        //由函数生成流：创建无限流Stream.iterate Stream.generate  （流的元素是无限的）
        Stream.iterate(0, n -> n +2).limit(100).forEach(System.out::println);
        //生成 斐波拉契 元组 (0,1) (1,1) (1,2) (2,3) (3,5) ......  (n1,n2) (n2,n3) (n3,n4)
        Stream.iterate(new int[]{0,1}, arr -> new int[]{arr[1],arr[0]+arr[1]}).limit(20).forEach(t -> System.out.println("[" + t[0] + "," + t[1] + "]"));
        System.out.println();
        //打印 正常的斐波拉契数列
        Stream.iterate(new int[]{0,1}, arr -> new int[]{arr[1],arr[0]+arr[1]}).limit(20).map(t -> t[0]).forEach(n -> System.out.print(n+","));

        //生成  10个随机数流
        Stream<Double> limit = Stream.generate(Math::random).limit(10);



        //generate 生成斐波那契数列 （不能并行，因为记录了状态） 这种使用方式并不常用，不建议使用
        Supplier<Integer> fib = new Supplier<Integer>() {
            private Integer previous = 0;
            private Integer current = 1;
            @Override
            public Integer get() {
                Integer oldPrevious = this.previous;
                Integer nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        Stream.generate(fib).limit(10).forEach(System.out::println);

    }
}
