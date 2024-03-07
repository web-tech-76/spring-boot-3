package boot2adata.app.books;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Controller
@Slf4j
@RequiredArgsConstructor
class BookController implements ApplicationListener<NewBookEvent> {

    private final BookRepository bookRepository;
    private Set<Book> bookSet = new ConcurrentSkipListSet<>(Comparator.comparing(Book::getId));


    @Override
    public void onApplicationEvent(NewBookEvent event) {
        this.bookSet.add(event.getSource());
    }

    @PostMapping("/new")
    @ResponseBody
    Collection<Book> createNew(@RequestBody Book book) {
        var addedNewBook = this.bookRepository.save(book);
        return this.bookSet;
    }


}

