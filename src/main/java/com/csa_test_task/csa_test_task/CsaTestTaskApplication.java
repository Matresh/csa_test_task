package com.csa_test_task.csa_test_task;

import com.csa_test_task.csa_test_task.iex.IEXCloudApiClient;
import com.csa_test_task.csa_test_task.iex.models.IEXCloudApiCompany;
import com.csa_test_task.csa_test_task.iex.models.IEXCloudApiCompanyStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Stream;

@SpringBootApplication
@EnableScheduling
public class CsaTestTaskApplication implements CommandLineRunner {
	@Value("${app.mobile_data}")
	boolean mobileData;

	@Autowired
	IEXCloudApiCompany[] companyArray;

	@Autowired
	IEXCloudApiClient IexCloudApiClient;

	@Autowired
	StockHistoryController historyController;

	public static void main(String[] args) {
		SpringApplication.run(CsaTestTaskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ExecutorService threadPool = Executors.newFixedThreadPool(2);

		while (true) {
			Stream<IEXCloudApiCompany> company_stream = Arrays.stream(companyArray).
					parallel().
					unordered().
					filter(company -> !company.isEnabled());

			if (mobileData){
				company_stream = company_stream.limit(5);
			}
			Stream<Future<IEXCloudApiCompanyStock>> stock_future_stream = company_stream.
					sequential().
					map(
							(company) ->
									CompletableFuture.supplyAsync(
											() -> this.IexCloudApiClient.requestCompanyStockData(company),
											threadPool));

			stock_future_stream.
					parallel().
					map(
							(stock_future) -> {
								try {
									return stock_future.get();
								} catch (InterruptedException | ExecutionException e) {
									e.printStackTrace();
								}
								return null;
							}
					).
					filter(Objects::nonNull).
					forEach(this.historyController::saveWithChangeHistory);
		}
	}
}
