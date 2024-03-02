package boot1.app.customers;

import org.springframework.context.ApplicationEvent;

import java.util.List;

public class CustomersLoadEvent extends ApplicationEvent {

    public CustomersLoadEvent(List<Customer> source) {
        super(source);
    }

    @Override
    public List<Customer> getSource() {
        return (List<Customer>) super.getSource();
    }
}
