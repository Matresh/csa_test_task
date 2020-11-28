package com.csa_test_task.csa_test_task;

import com.csa_test_task.csa_test_task.iex.IEXCompanyStockRepository;
import com.csa_test_task.csa_test_task.iex.models.IEXCloudApiCompanyStock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@Slf4j
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

        Page<IEXCloudApiCompanyStock> stocks = stockRepository.findAll(topFive);

        log.info("-".repeat(40)+"topFiveExpensiveStocks"+"-".repeat(40));

        if (stocks.isEmpty()){
            log.info("Empty");
        }
        else{
            stocks.forEach(
                    (companyStock) ->
                            log.info(companyStock.toString()));
        }

    }

    @Scheduled(cron = "*/5 * * * * *")
    public void lastFiveExpensiveStocks(){
        PageRequest lastFive = PageRequest.of(
                0,
                5,
                Sort.by(Sort.Direction.ASC,
                        "changePercent"));

        Page<IEXCloudApiCompanyStock> stocks = stockRepository.findAll(lastFive);

        log.info("-".repeat(40)+"lastFiveExpensiveStocks"+"-".repeat(40));

        if (stocks.isEmpty()){
            log.info("Empty");
        }
        else{
            stocks.forEach(
                    (companyStock) ->
                            log.info(companyStock.toString()));
        }
    }
}
