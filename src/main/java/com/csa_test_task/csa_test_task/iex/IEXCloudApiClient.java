package com.csa_test_task.csa_test_task.iex;

import com.csa_test_task.csa_test_task.iex.models.IEXCloudApiCompany;
import com.csa_test_task.csa_test_task.iex.models.IEXCloudApiCompanyStock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class IEXCloudApiClient {
    @Value("${iex.api_url}")
    private String apiUrl;
    @Value("${iex.api_token}")
    private String apiToken;
    @Autowired
    private IEXCloudApiCompany[] companies;
    @Autowired
    private RestTemplate restTemplate;

    public IEXCloudApiCompanyStock requestCompanyStockData(IEXCloudApiCompany company){
        // TODO CANT FIND SIMPLE URL BUILDER
        String requestUrl =
                apiUrl +
                "/stable/stock/"+
                company.getSymbol() +
                "/quote?token=" +
                apiToken;
        log.info(requestUrl+"for company"+ company.toString());
        return restTemplate.getForObject(requestUrl,
                IEXCloudApiCompanyStock.class);
    }
}
