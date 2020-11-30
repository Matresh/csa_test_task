package com.csa_test_task.csa_test_task.config;

import com.csa_test_task.csa_test_task.iex.models.IEXCloudApiCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@ComponentScan("com.csa_test_task.csa_test_task")
public class IEXCloudTestConfiguration {

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

    @Bean
    public IEXCloudApiCompany[] companyArray(RestTemplate restTemplate){
        return new IEXCloudApiCompany[]{
                IEXCloudApiCompany.builder().isEnabled(true).symbol("A").build()};
    }

}

