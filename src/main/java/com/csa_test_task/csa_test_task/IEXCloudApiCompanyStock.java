package com.csa_test_task.csa_test_task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class IEXCloudApiCompanyStock {
    String symbol;
    String companyName;
    double changePercent;
    double high;
}
