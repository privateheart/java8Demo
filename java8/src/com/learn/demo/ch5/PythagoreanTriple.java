package com.learn.demo.ch5;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author : huyi
 * @Project: java8
 * @Package com.learn.demo.ch5
 * @Description: 毕达哥拉斯三元数 （勾股数）   用javv8 Stream 构建勾股数流
 * @date Date : 2019年02月23日 10:27
 */
public class PythagoreanTriple {

    public static void main(String[] args) {
        Stream<double[]> doublestream = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100).mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)}).filter(arr -> arr[2] % 1 == 0));

        //stream 就是勾股数流（double数组） 转换成 int 数组
        Stream<int[]> intstream = doublestream.map(arr -> new int[]{(int) arr[0], (int) arr[1], (int) arr[2]});
        intstream.forEach(arr -> System.out.println("arr["+arr[0] +"," + arr[1] + "," + arr[2] + "]"));

    }
}
