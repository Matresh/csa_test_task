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

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.stream.Stream;

@SpringBootApplication
@EnableScheduling
public class CsaTestTaskApplication implements CommandLineRunner {

	private final boolean mobileData;

	private final IEXCloudApiCompany[] companyArray;

	private final IEXCloudApiClient iexCloudApiClient;

	private final StockHistoryController historyController;

	ExecutorService threadPool;

	@Autowired
	public CsaTestTaskApplication(@Value("${app.mobile_data}")boolean mobileData,
								  IEXCloudApiCompany[] companyArray,
								  IEXCloudApiClient iexCloudApiClient,
								  StockHistoryController historyController) {
		this.mobileData = mobileData;
		this.companyArray = companyArray;
		this.iexCloudApiClient = iexCloudApiClient;
		this.historyController = historyController;
		this.threadPool = Executors.newFixedThreadPool(2);
	}

	public static void main(String[] args) {
		SpringApplication.run(CsaTestTaskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		while (true) {
			Stream<IEXCloudApiCompany> companyStream = Arrays.stream(companyArray)
					.parallel()
					.filter(company -> !company.isEnabled());

			if (mobileData){
				companyStream = companyStream.limit(5);
			}

			Stream<Future<IEXCloudApiCompanyStock>> stockFutureStream = companyStream
					.sequential()
					.map(this::AsyncRequestIEXCloudApiCompanyStock);

			stockFutureStream.
					parallel()
					.map(this::getIEXCloudApiCompanyStock)
					.filter(Optional::isPresent)
					.map(Optional::get)
					.forEach(this.historyController::saveWithChangeHistory);
		}
	}

	private Future<IEXCloudApiCompanyStock> AsyncRequestIEXCloudApiCompanyStock(IEXCloudApiCompany company){
		return CompletableFuture.supplyAsync(
				() -> this.iexCloudApiClient.requestCompanyStockData(company),
				threadPool);
	}
	private Optional<IEXCloudApiCompanyStock> getIEXCloudApiCompanyStock(Future<IEXCloudApiCompanyStock> future){
		try {
			return Optional.of(future.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return Optional.empty();

	}
}
