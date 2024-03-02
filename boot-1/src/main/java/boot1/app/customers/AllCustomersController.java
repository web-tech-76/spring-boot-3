package boot1.app.customers;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class AllCustomersController implements ApplicationListener<CustomersLoadEvent> {

    private List<Customer> customerCache = new LinkedList<>();

    @Override
    public void onApplicationEvent(CustomersLoadEvent event) {
        this.customerCache = event.getSource();
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Customer> all() {
        return this.customerCache;
    }

    @GetMapping("/all.html")
    ModelAndView allCustomers() {
        return new ModelAndView("customer", Map.of("customers", this.customerCache));
    }


}
