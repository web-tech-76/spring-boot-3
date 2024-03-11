package boo3reactivegraph.app.customers;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface CustomerDetailsRepository extends ReactiveCrudRepository<CustomerDetails, Integer> {
    Flux<CustomerDetails> findCustomerDetailsByCustomerId(Integer customerId);
}
