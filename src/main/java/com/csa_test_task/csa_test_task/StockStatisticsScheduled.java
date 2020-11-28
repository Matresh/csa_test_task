package com.csa_test_task.csa_test_task;

import com.csa_test_task.csa_test_task.iex.IEXCompanyStockRepository;
import com.csa_test_task.csa_test_task.iex.models.IEXCloudApiCompanyStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockStatisticsScheduled {
    @Autowired
    IEXCompanyStockRepository stockRepository;

    @Scheduled(cron = "*/5 * * * * *")
    public void topFiveExpensiveStocks(){
        PageRequest topFive = PageRequest.of(
                0,
                5,
                Sort.by(Sort.Direction.DESC,
                        "high"));

        stockRepository.findAll(topFive).
                forEach(System.out::println);

    }

    @Scheduled(cron = "*/5 * * * * *")
    public void lastFiveExpensiveStocks(){
        PageRequest lastFive = PageRequest.of(
                0,
                5,
                Sort.by(Sort.Direction.ASC,
                        "changePercent"));

        Page<IEXCloudApiCompanyStock> stocks = stockRepository.findAll(lastFive);

        if (stocks.isEmpty()){
            System.out.println("Empty");
        }
        else{
            stocks.forEach(System.out::println);
        }
    }
}
