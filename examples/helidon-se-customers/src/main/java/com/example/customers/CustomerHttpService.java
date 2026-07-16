package com.example.customers;

import io.helidon.http.Status;
import io.helidon.webserver.http.HttpRules;
import io.helidon.webserver.http.HttpService;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;

/**
 * Helidon SE {@link HttpService} for customer routes.
 * Register under a path prefix such as {@code /customers}.
 */
public final class CustomerHttpService implements HttpService {

    private final CustomerService customerService;

    public CustomerHttpService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void routing(HttpRules rules) {
        rules.get("/{id}", this::findById);
    }

    private void findById(ServerRequest request, ServerResponse response) {
        String id = request.path().pathParameters().get("id");

        customerService.findById(id)
                .ifPresentOrElse(
                        response::send,
                        () -> response.status(Status.NOT_FOUND_404).send()
                );
    }
}
