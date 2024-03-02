package boot1.app.customers;

import org.springframework.context.ApplicationEvent;


public class CustomerCreatedEvent extends ApplicationEvent {

    public CustomerCreatedEvent(Customer source) {
        super(source);
    }


    @Override
    public Customer getSource() {
        return (Customer) super.getSource();

    }


}
