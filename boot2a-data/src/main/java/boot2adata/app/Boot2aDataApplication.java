package boot2adata.app;

import boot2adata.app.books.NewBookEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class Boot2aDataApplication {


    public static void main(String[] args) {
        SpringApplication.run(Boot2aDataApplication.class, args);
    }


    @Bean
    ApplicationListener<NewBookEvent> newBookEventApplicationListener() {
        return event -> log.info("new book added {}", event.getSource());
    }

    @Bean
    ApplicationListener<WebServerInitializedEvent> webServerInitializedEventApplicationListener() {
        return event -> log.info("application has started {} ", event.getWebServer().getPort());
    }

/*
    @Bean
    ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener() {
        return event -> log.info("application is ready took {} seconds ", event.getTimeTaken().toSeconds());
    }
*/

    @Bean
    ApplicationListener<AvailabilityChangeEvent<?>> availabilityChangeEventApplicationListener() {
        return event -> log.info("application state has changed to {} ", event.getState().toString());
    }


}
