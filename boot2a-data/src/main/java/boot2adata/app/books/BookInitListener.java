package boot2adata.app.books;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class BookInitListener {

    private final BookRepository bookRepository;

    @Bean
    ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener() {

        return event -> {
            bookRepository.save(
                    new Book(
                            null,
                            "some name",
                            "author1",
                            10.35,
                            false
                    ));
            var all = bookRepository.findAll();
        };
    }


}
