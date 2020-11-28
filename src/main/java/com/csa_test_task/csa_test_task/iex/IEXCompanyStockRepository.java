package com.csa_test_task.csa_test_task.iex;

import com.csa_test_task.csa_test_task.iex.models.IEXCloudApiCompanyStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEXCompanyStockRepository
        extends JpaRepository<IEXCloudApiCompanyStock, Long> {

}
