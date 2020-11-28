package com.csa_test_task.csa_test_task.iex;

import com.csa_test_task.csa_test_task.iex.models.IEXCompanyStockHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEXCompanyStockHistoryRepository
        extends JpaRepository<IEXCompanyStockHistory, Long> {
}
