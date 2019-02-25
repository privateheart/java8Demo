package com.learn.demo.ch5.trade;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * @author : huyi
 * @Project: java8Demo
 * @Package com.learn.demo.ch5.trade
 * @Description: 练习
 * @date Date : 2019年02月20日 19:05
 */
public class TradeDemo {

    static Trader raoul = new Trader("Raoul", "Cambridge");
    static Trader mario = new Trader("Mario", "Milan");
    static Trader alan = new Trader("Alan", "Cambridge");
    static Trader brian = new Trader("Brian", "Cambridge");
    static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian,2011,300),
            new Transaction(raoul,2012,1000),
            new Transaction(raoul,2011,400),
            new Transaction(mario,2012,710),
            new Transaction(mario, 2012,700),
            new Transaction(alan,2012,950));

    public static void main(String[] args) {
        //(1) 找出2011年发生的所有的交易，并按照交易额排序（从低到高）
        System.out.println("(1) 找出2011年发生的所有的交易，并按照交易额排序（从低到高）");
        transactions.stream().filter( transaction -> {return transaction.getYear() == 2011;}).sorted(comparing(Transaction::getValue)).forEach(System.out::println);
        //(2) 交易员都在哪些不同的城市工作过  toSet 自带去重功能，根据hashCode
        System.out.println("(2) 交易员都在哪些不同的城市工作过");
        transactions.stream().map( t -> {return t.getTrader().getCity();}).distinct().forEach(System.out::println);

//        transactions.stream().map(t -> {return t.getTrader().getCity();}).collect(Collectors.toSet()).forEach(city ->{System.out.println(city);});
        //(3) 查找所有来自于剑桥的交易员，并按照姓名排序
        System.out.println("(3) 查找所有来自于剑桥的交易员，并按照姓名排序");
        transactions.stream().filter(t -> {return "Cambridge".equals(t.getTrader().getCity());}).map( t -> {return t.getTrader();}).sorted(comparing(Trader::getName)).forEach(System.out::println);
        //(4) 返回所有交易员的姓名字符串，按字母顺序排序
        System.out.println("(4) 返回所有交易员的姓名字符串，按字母顺序排序");
        String nameStr = transactions.stream().map(t -> {
            return t.getTrader().getName();
        }).reduce("", (n1, n2) -> {
            return n1 + n2;
        });
        System.out.println(nameStr);
        //(5) 有没有交易员是在米兰工作的
        System.out.println("(5) 有没有交易员是在米兰工作的");
        boolean isExists = transactions.stream().anyMatch(t -> {
            return "Milan".equals(t.getTrader().getCity());
        });
        System.out.println(isExists);
        //(6) 打印生活在剑桥的交易员的所有交易额
        System.out.println("(6) 打印生活在剑桥的交易员的所有交易额");
        transactions.stream().filter(t-> {return "Cambridge".equals(t.getTrader().getCity());}).forEach(System.out::println);
        //(7) 所有交易中，最高的交易额是多少？
        System.out.println("(7) 所有交易中，最高的交易额是多少？");
        Transaction maxTrade = transactions.stream().reduce(transactions.get(0), (t1, t2) -> {
            return t1.getValue() > t2.getValue() ? t1 : t2;
        });
        System.out.println(maxTrade);
        //(8) 找到交易额最小的交易。
        System.out.println("(8) 找到交易额最小的交易。");
        Transaction minTrade = transactions.stream().reduce(transactions.get(0), (t1, t2) -> {
            return t1.getValue() > t2.getValue() ?  t2: t1;
        });
        System.out.println(minTrade);
    }
}
