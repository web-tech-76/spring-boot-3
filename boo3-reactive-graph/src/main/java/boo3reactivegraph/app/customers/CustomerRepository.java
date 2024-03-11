package boo3reactivegraph.app.customers;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
    Flux<Customer> findCustomersByName(String name);
}
