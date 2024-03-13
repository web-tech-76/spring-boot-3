package mongo.dsl.app;

import mongo.dsl.app.customers.Customer;
import mongo.dsl.app.customers.CustomerRepository;
import mongo.dsl.app.customers.QCustomer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class Boot3MongoGraphQdslApplication {

    public static void main(String[] args) {
        SpringApplication.run(Boot3MongoGraphQdslApplication.class, args);
    }


    @Bean
    ApplicationListener<ApplicationReadyEvent> createCustomers(CustomerRepository customerRepository) {
        return event -> {
            customerRepository.deleteAll();

            Set.of("A", "B", "C")
                    .forEach(c ->
                            customerRepository.save(
                            new Customer(null, c, Math.random() > 0.5)));

            customerRepository
                    .findAll(QCustomer.customer.name.startsWith("A").not())
                    .forEach(System.out::println);

        };
    }


}



