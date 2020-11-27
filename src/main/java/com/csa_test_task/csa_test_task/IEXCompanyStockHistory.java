package com.csa_test_task.csa_test_task;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Builder
public class IEXCompanyStockHistory {


    @Temporal(TemporalType.TIMESTAMP)
    Date changedDate;

    @ManyToOne
    @JoinColumn(name = "id")
    IEXCloudApiCompanyStock companyStock;

    IEXCloudApiCompanyStock oldStock;
    IEXCloudApiCompanyStock newStock;



}
