package boot2data.app.books;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class BookInitListener {

    private final BookService bookService;

    @Bean
    ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener() {

        return event -> {
            var all = bookService.getAllBooks();
        };
    }


}
