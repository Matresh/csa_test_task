package com.csa_test_task.csa_test_task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.awt.print.Pageable;

@Component
public class stockStatisticsScheduled {
    @Autowired
    IEXCompanyStockRepository stockRepository;

    @Scheduled(cron = "*/5 * * * * *")
    public void topFiveExpensiveStocks(){
        PageRequest topFive = PageRequest.of(
                0,
                5,
                Sort.by(Sort.Direction.DESC, "high"));

        stockRepository.findAll(topFive).
                forEach(System.out::println);

    }
}
