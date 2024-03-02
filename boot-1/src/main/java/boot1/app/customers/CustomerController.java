package boot1.app.customers;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Controller
class CustomerController implements ApplicationListener<CustomerCreatedEvent> {

    private Set<Customer> customerCache = new ConcurrentSkipListSet<>(
            Comparator.comparing(Customer::id)
    );

    @Override
    public void onApplicationEvent(CustomerCreatedEvent event) {
        this.customerCache.add(event.getSource());
    }

    @GetMapping("/new")
    @ResponseBody
    public Set<Customer> all() {
        System.out.println("customerCache = " + customerCache);
        return this.customerCache;
    }


}


