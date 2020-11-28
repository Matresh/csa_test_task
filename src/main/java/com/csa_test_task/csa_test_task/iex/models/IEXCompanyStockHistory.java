package com.csa_test_task.csa_test_task.iex.models;

import com.csa_test_task.csa_test_task.iex.models.IEXCloudApiCompanyStock;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IEXCompanyStockHistory {
    private @Id
    @GeneratedValue Long history_id;

    @Temporal(TemporalType.TIMESTAMP)
    Date changedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    IEXCloudApiCompanyStock companyStock;

    String oldStock;
    String newStock;



}
