package com.csa_test_task.csa_test_task.iex.models;

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
    public String symbol;
    public String companyName;
    public double changePercent;
    public double high;
}
