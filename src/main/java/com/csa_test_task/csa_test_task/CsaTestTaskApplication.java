package com.csa_test_task.csa_test_task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CsaTestTaskApplication implements CommandLineRunner {
	@Autowired
	IEXCloudApiCompany[] companyArray;

	@Autowired
	IEXCloudApiClient IexCloudApiClient;

	@Autowired
	IEXCompanyStockRepository companyStockRepository;

	public static void main(String[] args) {
		SpringApplication.run(CsaTestTaskApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

//		System.out.println(companyArray);
		Arrays.stream(companyArray).
				filter(company -> !company.isEnabled).
				limit(5).
				map(this.IexCloudApiClient::requestCompanyStockData).
				forEach(this.companyStockRepository::save);
	}
}
