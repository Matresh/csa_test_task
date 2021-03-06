package com.csa_test_task.csa_test_task.iex.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class IEXCloudApiCompany {
    String symbol;
    boolean isEnabled;
}
