package com.learn.demo.ch6;

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

}
