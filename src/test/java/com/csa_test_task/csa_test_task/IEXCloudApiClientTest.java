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
import static org.junit.jupiter.api.Assertions.assertEquals;
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

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.valueToTree(stock);
        String requestUrl ="/stable/stock/"+
                            company.getSymbol() +
                            "/quote?token=" +
                            apiToken;

        wireMockServer.stubFor(
        get(WireMock.urlEqualTo(requestUrl))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withJsonBody(node)));

        IEXCloudApiCompanyStock actual_stock = apiClient.requestCompanyStockData(company);

        assertEquals(stock.getId(), actual_stock.getId());
        assertEquals(stock.getChangePercent(), actual_stock.getChangePercent());
        assertEquals(stock.getCompanyName(), actual_stock.getCompanyName());
        assertEquals(stock.getSymbol(), actual_stock.getSymbol());
        assertEquals(stock.getHigh(), actual_stock.getHigh());

        wireMockServer.verify(getRequestedFor(urlEqualTo(requestUrl)));

    }

}
