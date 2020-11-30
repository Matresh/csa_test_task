package com.csa_test_task.csa_test_task;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


public class RestWireMockTest {
    protected static WireMockServer wireMockServer;

    @BeforeAll
    static void setupWireMockServer(){
        wireMockServer = new WireMockServer(options().port(8888));

        wireMockServer.start();

    }

    @AfterAll
    static void shutdownWireMockServer(){
        wireMockServer = new WireMockServer();
        wireMockServer.start();

    }
    
}
