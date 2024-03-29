package boo3reactivegraph.app.customers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final CustomerDetailsRepository customerDetailsRepository;


    @QueryMapping
    Mono<Customer> customerById(@Argument Integer id) {
        return this.customerRepository.findById(id);
    }

    @QueryMapping
    Flux<Customer> customers() {
        return this.customerRepository.findAll();
    }


    @MutationMapping
    Mono<Customer> createCustomer(@Argument String name, @Argument Boolean isLoggedIn) {
        return this.customerRepository.save(new Customer(null, name, isLoggedIn));
    }


    @SchemaMapping(typeName = "Customer")
    Flux<CustomerDetails> customerDetail(Customer customer) {
        return this.customerDetailsRepository.findCustomerDetailsByCustomerId(customer.getId());
    }

    @SubscriptionMapping
    Flux<Customer> createdCustomers() {
        return this.customers().delayElements(Duration.ofSeconds(1));
    }

  /*  @BatchMapping
    Mono<Map<Customer, List<CustomerDetails>>> customerDetail(List<Customer> customers) {
        var customerMap = new HashMap<Customer, List<CustomerDetails>>();
        for (var customer : customers) {
            List<CustomerDetails> detailsList = null;
            detailsList = new ArrayList<CustomerDetails>();
            var details = this.customerDetailsRepository
                    .findCustomerDetailsByCustomerId(customer.getId());
            details.collectList().subscribe(detailsList::addAll);
            customerMap.put(customer, detailsList);
        }

        return Mono.just(customerMap);
    }
*/

    @QueryMapping
    Flux<Customer> customerByName(@Argument String name) {
        return this.customerRepository.findCustomersByName(name);
    }

    @QueryMapping
    Flux<CustomerDetails> customerDetailsByCustomerId(@Argument Integer customerId) {
        return this.customerDetailsRepository.findCustomerDetailsByCustomerId(customerId);
    }

    @QueryMapping
    Mono<CustomerDetails> customersDetailsById(@Argument Integer id) {
        return this.customerDetailsRepository.findById(id);
    }

    @QueryMapping
    Flux<CustomerDetails> allCustomersDetails() {
        return this.customerDetailsRepository.findAll();
    }


}
