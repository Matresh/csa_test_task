package com.csa_test_task.csa_test_task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class IEXCloudApiCompanyStock {
    private @Id @GeneratedValue Long id;
    String symbol;
    String companyName;
    double changePercent;
    double high;
}
