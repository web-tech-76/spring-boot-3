package boot1.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class Boot1Application {

    public static void main(String[] args) {
        SpringApplication.run(Boot1Application.class, args);
    }

    @Bean
    ApplicationListener<WebServerInitializedEvent> webServerInitializedEventApplicationListener() {
        return event -> {
            log.info("application server started on port  -> {}", event.getWebServer().getPort());
        };
    }

    @Bean
    ApplicationListener<AvailabilityChangeEvent<?>> availabilityChangeEventApplicationListener() {
        return event -> {
            log.info("application is healthy {}", event.getState().toString());
        };
    }

    /*@Bean
    CommandLineRunner commandLineRunner() {
        return (String...args) -> {

        };

    }*/


}



/*new AnnotationConfigApplicationContext();
        appContext.register(AppConfig.class);
        appContext.refresh();
        appContext.start();
*/



    /*@Bean
    ApplicationRunner applicationRunner(DefaultCustomerService dcs) {
        return (event) -> {
            log.info("dcs.class {}", dcs.getClass().getName());
            *//* var james = dcs.add("james");
                var watt = dcs.add("watt");
            *//*
            var values = dcs.find();
            var names = values.stream().map(value -> value.name()).toList();
            System.out.printf("names found  %s \n", names);
            Assert.state(names.contains("james") && names.contains("watt"), "added successfully");
            values.stream().forEach(System.out::println);
        };

    }
*/

/*
@Slf4j
@Component
class MyApplicationClassRunner implements ApplicationRunner {

    private final DefaultCustomerService dcs;

    public MyApplicationClassRunner(DefaultCustomerService dcs) {
        this.dcs = dcs;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {


        log.info("dcs.class {}", dcs.getClass().getName());

       */
/* var james = dcs.add("james");
        var watt = dcs.add("watt");
        *//*


        var values = dcs.find();

        var names = values.stream().map(value -> value.name()).toList();

        System.out.printf("names found  %s \n", names);

        Assert.state(names.contains("james") && names.contains("watt"), "added successfully");

        values.stream().forEach(System.out::println);

    }
}
*/
