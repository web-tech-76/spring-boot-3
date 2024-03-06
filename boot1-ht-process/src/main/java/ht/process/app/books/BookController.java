package ht.process.app.books;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Controller
@Slf4j
class BookController implements ApplicationListener<NewBookEvent> {

    private final BookService bookService;
    private final Set<Book> bookSet = new ConcurrentSkipListSet<>(Comparator.comparing(Book::id));

    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void onApplicationEvent(NewBookEvent event) {
        this.bookSet.add(event.getSource());
    }

    @PostMapping("/new")
    @ResponseBody
    Collection<Book> createNew(@RequestBody Book book) {
        var addedNewBook = this.bookService.addNewBook(book);
        return this.bookSet;
    }

    @PutMapping("/update")
    @ResponseBody
    Collection<Book> updateBook(@RequestBody Book book) {
        var addedNewBook = this.bookService.addNewBook(book);
        return this.bookSet;
    }

    @DeleteMapping("/delete")
    @ResponseBody
    Collection<Book> deleteBook(@RequestBody Book book) {
        var addedNewBook = this.bookService.addNewBook(book);
        return this.bookSet;
    }


}

