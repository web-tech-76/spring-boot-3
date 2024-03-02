package boot1.app.orders;

import bulk.app.customers.CustomerCreatedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements ApplicationListener<CustomerCreatedEvent> {

    @Override
    public void onApplicationEvent(CustomerCreatedEvent event) {

    }
}
