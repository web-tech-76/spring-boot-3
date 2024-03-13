package mongo.dsl.app.customers;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface CustomerRepository extends CrudRepository<Customer, Integer>,
        QuerydslPredicateExecutor<Customer> {

    Customer findCustomersByName(String name);
}
