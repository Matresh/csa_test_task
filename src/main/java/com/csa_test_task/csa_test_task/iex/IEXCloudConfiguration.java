package com.csa_test_task.csa_test_task.iex;

import com.csa_test_task.csa_test_task.iex.models.IEXCloudApiCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@ComponentScan
public class IEXCloudConfiguration {

    @Value("${iex.company_list_url}")
    private String company_list_url;

    @Value("${rest.default_timeout}")
    private Long defaultTimeout;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.setConnectTimeout(
                Duration.ofSeconds(this.defaultTimeout)
        ).build();
    }

    @Autowired
    @Bean
    public IEXCloudApiCompany[] companyArray(RestTemplate restTemplate){
        return restTemplate.getForObject(this.company_list_url,
                                            IEXCloudApiCompany[].class);
    }

}

