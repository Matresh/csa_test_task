package com.csa_test_task.csa_test_task;

import com.csa_test_task.csa_test_task.config.IEXCloudTestConfiguration;
import com.csa_test_task.csa_test_task.iex.IEXCloudApiClient;
import com.csa_test_task.csa_test_task.iex.models.IEXCloudApiCompany;
import com.csa_test_task.csa_test_task.iex.models.IEXCloudApiCompanyStock;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.nio.file.Files;
import java.nio.file.Paths;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = IEXCloudTestConfiguration.class)
@TestPropertySource(locations = "/application.properties")
public class IEXCloudApiClientTest extends IEXCloudApiTests {
    @Autowired
    IEXCloudApiClient apiClient;


    private final String apiToken;

    public IEXCloudApiClientTest( @Value("${iex.api_token}")String apiToken) {
        this.apiToken = apiToken;
    }


    @Test
    void requestCompanyStockDataTestRequestPerformed() throws Exception {
        IEXCloudApiCompany company = getRandomCompany();
        IEXCloudApiCompanyStock stock = getRandomStock();
        String requestUrl ="/stable/stock/"+
                            company.getSymbol() +
                            "/quote?token=" +
                            apiToken;


        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.valueToTree(stock);

        wireMockServer.stubFor(
        get(WireMock.urlEqualTo(requestUrl))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withJsonBody(node)));

        IEXCloudApiCompanyStock actual_stock = apiClient.requestCompanyStockData(company);

    }

    public static String readFileAsString(String file) throws Exception{
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}
