package com.example.customers;

import io.helidon.logging.common.LogConfig;
import io.helidon.webserver.WebServer;

/**
 * Explicit SE bootstrap: construct the dependency graph and register routes.
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        LogConfig.configureRuntime();

        CustomerService customerService = new CustomerService();
        CustomerHttpService customerHttpService = new CustomerHttpService(customerService);

        WebServer server = WebServer.builder()
                .port(8080)
                .routing(routing -> routing
                        .register("/customers", customerHttpService))
                .build()
                .start();

        System.out.println("Customer API at http://localhost:" + server.port() + "/customers/{id}");
    }
}
