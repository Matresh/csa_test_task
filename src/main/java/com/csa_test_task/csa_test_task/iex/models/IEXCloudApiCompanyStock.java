package com.csa_test_task.csa_test_task.iex.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class IEXCloudApiCompanyStock {
    private @Id @GeneratedValue Long id;

    @Column(unique = true)
    String symbol;
    String companyName;
    double changePercent;
    double high;

    @ToString.Exclude
    @OneToMany(mappedBy = "companyStock",
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    Collection<IEXCompanyStockHistory> historyRecords = new ArrayList<IEXCompanyStockHistory>();

}
