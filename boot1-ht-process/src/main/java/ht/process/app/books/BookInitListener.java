package ht.process.app.books;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BookInitListener {

    @Bean
    ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener(BookService bookService) {

        return event -> {
            var all = bookService.getAllBooks();
        };
    }


}
