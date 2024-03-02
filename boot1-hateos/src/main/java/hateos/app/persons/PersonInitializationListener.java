package hateos.app.persons;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class PersonInitializationListener {

     @Bean
    ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener(PersonService personService){

         return new ApplicationListener<ApplicationReadyEvent>() {
             @Override
             public void onApplicationEvent(ApplicationReadyEvent event) {
                 personService.all();
             }
         };
     }

}
