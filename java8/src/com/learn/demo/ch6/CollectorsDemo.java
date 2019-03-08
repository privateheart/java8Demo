package com.learn.demo.ch6;

import com.learn.demo.ch5.Dish;
import com.learn.demo.ch5.StreamDemo;
import com.learn.demo.ch5.trade.TradeDemo;
import com.learn.demo.ch5.trade.Trader;
import com.learn.demo.ch5.trade.Transaction;
import org.junit.Test;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

/**
 * @author : huyi
 * @Project: java8
 * @Package com.learn.demo.ch6
 * @Description: 流收集器 API 简单使用
 *
 * Collectors类的静态工厂方法
 *
 *  toList      把流中所有项目收集到一个List
 *  toSet       把流中所有项目收集到一个Set，删除重复项
 *  toCollection 把流中所有项目收集到给定的供应源创建的集合
 *  counting   计算流中元素的个数
 *  summingInt 对流中项目的一个整数属性求和
 *  averagingInt 计算流中项目Integer 属性的平均值
 *  summarizingInt 收集关于流中项目Integer 属性的统计值，例如最大、最小、总和与平均值
 *  joining 连接对流中每个项目调用toString 方法所生成的字符串
 *  maxBy   一个包裹了流中按照给定比较器选出的最大元素的Optional，或如果流为空则为Optional.empty()
 *  minBy  一个包裹了流中按照给定比较器选出的最小元素的Optional，或如果流为空则为Optional.empty()
 *  reducing   从一个作为累加器的初始值开始，利用BinaryOperator 与流中的元素逐个结合，从而将流归约为单个值
 *  collectingAndThen   转换函数    包裹另一个收集器，对其结果应用转换函数
 *  groupingBy 根据项目的一个属性的值对流中的项目作问组，并将属性值作 为结果Map 的键
 *  partitionBy 根据对流中每个项目应用谓词的结果来对项目进行分区
 *
 * @date Date : 2019年03月02日 9:16
 */
public class CollectorsDemo {
    //.collect(toList()) groupingBy()

    //分组
    public void testGroupingBy(){
        //按交易员 分组，收集
        Map<Trader, List<Transaction>> collect = TradeDemo.transactions.stream().collect(groupingBy(Transaction::getTrader));
    }

    //汇总  summingInt,averageInt, summarizingInt(统计总个数，平均值，最大值，最小值，总和),
    @Test
    public void testSumminInt(){
        //统计所有的交易的总和
        Integer totalAmount = TradeDemo.transactions.stream().collect(summingInt(Transaction::getValue));
        //统计所有交易的平均值
        TradeDemo.transactions.stream().collect(averagingInt(Transaction::getValue));
        //summarizingInt
        IntSummaryStatistics intSummaryStatistics = TradeDemo.transactions.stream().collect(summarizingInt(Transaction::getValue));
        System.out.println(intSummaryStatistics);

        
    }
    //测试分组
    public void testGroup(){
        Map<Dish.Type, List<Dish>> typeList = StreamDemo.menu.stream().collect(groupingBy(Dish::getType));
    }

}
