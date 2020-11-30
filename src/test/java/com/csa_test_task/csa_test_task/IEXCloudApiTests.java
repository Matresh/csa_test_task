package com.csa_test_task.csa_test_task;

import com.csa_test_task.csa_test_task.iex.models.IEXCloudApiCompany;
import com.csa_test_task.csa_test_task.iex.models.IEXCloudApiCompanyStock;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class IEXCloudApiTests extends RestWireMockTest {

    public static IEXCloudApiCompany getRandomCompany(){
        String companySymbol = RandomStringUtils
                .randomAlphabetic(RandomUtils.nextInt(1, 4))
                .toUpperCase();

        return IEXCloudApiCompany
                .builder()
                .symbol(companySymbol)
                .isEnabled(true)
                .build();
    }

    public static IEXCloudApiCompanyStock getRandomStock(){
        return IEXCloudApiCompanyStock
                .builder()
                .changePercent(RandomUtils.nextDouble(Double.MIN_VALUE, Double.MAX_VALUE))
                .companyName(RandomStringUtils.randomAlphanumeric(RandomUtils.nextInt(1,50)))
                .high(RandomUtils.nextDouble(Double.MIN_VALUE, Double.MAX_VALUE))
                .symbol(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(1,4)))
                .build();

    }
}
