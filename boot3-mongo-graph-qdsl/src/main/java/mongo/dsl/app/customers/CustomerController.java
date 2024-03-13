package mongo.dsl.app.customers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerRepository customerRepository;

    @QueryMapping
    Customer customerById(@Argument Integer id) {
        return this.customerRepository.findById(id).orElseGet(null);
    }

    @QueryMapping
    Iterable<Customer> customers() {
        return this.customerRepository.findAll();
    }


    @MutationMapping
    Customer createCustomer(@Argument String name, @Argument Boolean isLoggedIn) {
        return this.customerRepository.save(new Customer(null, name, isLoggedIn));
    }


    @SubscriptionMapping
    Flux<Customer> createdCustomers() throws Exception {
        return Flux.fromIterable(this.customers()).delayElements(Duration.ofSeconds(1));
    }


    @QueryMapping
    Customer customerByName(@Argument String name) {
        return this.customerRepository.findCustomersByName(name);
    }


}
