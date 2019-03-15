package com.learn.demo.ch6;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * @author : huyi
 * @Project: java8Demo
 * @Package com.learn.demo.ch6
 * @Description: 质数收集器
 * @date Date : 2019年03月12日 8:27
 */
public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>() {{
            put(true, new ArrayList<Integer>());
            put(false, new ArrayList<Integer>());
        }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
            acc.get(isPrime(acc.get(true), candidate)).add(candidate);
        };
    }

    /**
     * 让收集器并行工作（如果可能）
     * 下一个方法要在并行收集时把两个部分累加器合并起来，这里，它只需要合并两个Map，即
     * 将第二个Map中质数和非质数列表中的所有数字合并到第一个Map的对应列表中就行了：
     * @return
     */
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
                map1.get(true).addAll(map2.get(true));
                map1.get(false).addAll(map2.get(false));
                return map1;
        };
    }

    /**
     * 前面说过，accumulator正好就是收集器的结果，用不着
     * 进一步转换，那么finisher方法就返回identity函数：
     * @return
     */
    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    /**
     * 就characteristics方法而言，我们已经说过，它既不是CONCURRENT也不是UNORDERED，
     * 但却是IDENTITY_FINISH的
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
    }

    private static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A item : list) {
            if (!p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

    private static boolean isPrime(List<Integer> primes, int candidate){
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return takeWhile(primes, i -> i <= candidateRoot)
                .stream()
                .noneMatch(p -> candidate % p == 0);
    }
}
