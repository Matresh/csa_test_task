package com.csa_test_task.csa_test_task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface IEXCompanyStockRepository extends JpaRepository<IEXCloudApiCompanyStock, Long> {

}
