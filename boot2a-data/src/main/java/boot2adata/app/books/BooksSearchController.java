package boot2adata.app.books;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Slf4j
@Controller
@Profile("prod")
class BooksSearchController implements ApplicationListener<BooksLoadEvent> {

    private Set<Book> bookCache = new ConcurrentSkipListSet<>(Comparator.comparing(Book::getId));

    @Override
    public void onApplicationEvent(BooksLoadEvent event) {
        this.bookCache = (Set<Book>) event.getSource();
    }

    @GetMapping("/")
    @ResponseBody
    Collection<Book> allBooks() {
        log.info("all books {} ", this.bookCache);
        return this.bookCache;
    }

}

