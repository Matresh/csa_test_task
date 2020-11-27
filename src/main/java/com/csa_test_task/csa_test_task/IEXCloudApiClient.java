package com.csa_test_task.csa_test_task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;


@Service
public class IEXCloudApiClient {
    @Value("${iex.api_url}")
    private String apiUrl;
    @Autowired
    private IEXCloudApiCompany[] companies;
    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    private void init(){
        System.out.println(this.apiUrl);
//
//        System.out.println(this.apiUrl);
//        this.restTemplate.getForObject(this.apiUrl,IEXCloudApiCompany.class);
    }
}
