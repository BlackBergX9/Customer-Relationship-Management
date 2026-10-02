package org.blackbergx9.customerhandler;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
@DirtiesContext
class CustomerHandlerApplicationTests {

    @Autowired
    private RestTestClient restTestClient;

    @Test
    void shouldRetrieveAndCreateCustomers()
    {

        // Verify initial data (3 customers from CustomerConfiguration)
        restTestClient.get().uri("api/v1/customers")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Customer[].class)
                .value(customers -> Assertions.assertThat(customers).hasSize(3));



        // Create a new customer
        Customer newCustomer = new Customer("New User", "new@example.com", "555-0199");

        restTestClient.post().uri("api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .body(newCustomer)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Customer.class)
                .value(savedCustomer -> {

                    Assertions.assertThat(savedCustomer.id()).isNotNull();
                    Assertions.assertThat(savedCustomer.name()).isEqualTo(newCustomer.name());
                });



        // Verify list now has 4 customers
        restTestClient.get().uri("api/v1/customers")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Customer[].class)
                .value(customers -> Assertions.assertThat(customers).hasSize(4));

    }

    @Test
    void contextLoads() {
    }

}
