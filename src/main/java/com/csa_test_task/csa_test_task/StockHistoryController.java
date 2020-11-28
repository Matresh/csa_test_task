package com.csa_test_task.csa_test_task;

import com.csa_test_task.csa_test_task.iex.IEXCompanyStockHistoryRepository;
import com.csa_test_task.csa_test_task.iex.IEXCompanyStockRepository;
import com.csa_test_task.csa_test_task.iex.models.IEXCloudApiCompanyStock;
import com.csa_test_task.csa_test_task.iex.models.IEXCompanyStockHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockHistoryController {

    @Autowired
    IEXCompanyStockHistoryRepository historyRepository;

    @Autowired
    IEXCompanyStockRepository stockRepository;
    private void onPresent(IEXCloudApiCompanyStock stock, IEXCloudApiCompanyStock actual_stock){
        if (!actual_stock.equals(stock)){
            historyRepository.save(IEXCompanyStockHistory.builder().
                    newStock(stock).
                    oldStock(actual_stock).
                    companyStock(actual_stock).build());
        }

        stockRepository.save(stock);
    }
    private void onAbsent(IEXCloudApiCompanyStock stock){
        stockRepository.save(stock);
    }

    void saveWithChangeHistory(IEXCloudApiCompanyStock stock){
        stockRepository.findById(stock.getId()).
                ifPresentOrElse(
                        (actual_stock) -> this.onPresent(stock, actual_stock),
                        ()-> this.onAbsent(stock));

    }
}
