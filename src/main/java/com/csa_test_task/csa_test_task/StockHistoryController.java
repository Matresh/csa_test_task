package com.csa_test_task.csa_test_task;

import com.csa_test_task.csa_test_task.iex.IEXCompanyStockHistoryRepository;
import com.csa_test_task.csa_test_task.iex.IEXCompanyStockRepository;
import com.csa_test_task.csa_test_task.iex.models.IEXCloudApiCompanyStock;
import com.csa_test_task.csa_test_task.iex.models.IEXCompanyStockHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StockHistoryController {


    private final IEXCompanyStockHistoryRepository historyRepository;

    @Autowired
    IEXCompanyStockRepository stockRepository;

    @Autowired
    public StockHistoryController(IEXCompanyStockHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    private void onPresent(IEXCloudApiCompanyStock stock, IEXCloudApiCompanyStock actual_stock){

        if (!actual_stock.equals(stock)){

            log.info(actual_stock.toString()+" is changed to" + stock.toString());

            historyRepository.save(IEXCompanyStockHistory.builder().
                    newStock(stock.toString()).
                    oldStock(actual_stock.toString()).
                    companyStock(actual_stock).build());
        }
        stock.setId(actual_stock.getId());
        stockRepository.save(stock);
    }
    private void onAbsent(IEXCloudApiCompanyStock stock){
        log.info(stock.toString()+" is appears first time");
        stockRepository.save(stock);
    }

    void saveWithChangeHistory(IEXCloudApiCompanyStock stock){
        stockRepository.findBySymbol(stock.getSymbol()).
                ifPresentOrElse(
                        (actual_stock) -> this.onPresent(stock, actual_stock),
                        ()-> this.onAbsent(stock));

    }
}
