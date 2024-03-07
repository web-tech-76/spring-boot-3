package boot2data.app.books;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.concurrent.ConcurrentSkipListSet;

@Slf4j
@Controller
@Profile("prod")
class BooksSearchController implements ApplicationListener<BooksLoadEvent> {

    private Collection<Book> bookCache = new ConcurrentSkipListSet<>();

    @Override
    public void onApplicationEvent(BooksLoadEvent event) {
        this.bookCache = event.getSource();
    }

    @GetMapping("/")
    @ResponseBody
    Collection<Book> allBooks() {
        log.info("all books {} ", this.bookCache);
        return this.bookCache;
    }

}

