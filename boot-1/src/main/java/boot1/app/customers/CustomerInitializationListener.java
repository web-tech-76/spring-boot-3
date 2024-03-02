package boot1.app.customers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
class CustomerInitializationListener {

    @Bean
    ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener(CustomerService dcs) {
        return event -> {
            log.info("dcs.class {}", dcs.getClass().getName());
            var all = dcs.find();
        };

    }

}
