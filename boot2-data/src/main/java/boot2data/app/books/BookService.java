package boot2data.app.books;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
class BookService {

    private final BookRepository bookRepository;

    private final ApplicationEventPublisher applicationEventPublisher;


    @Transactional(readOnly = true)
    Collection<Book> getAllBooks() {
        var books = bookRepository.findAll();
        Collection bookList = new ArrayList<>();
        books.forEach(bookList::add);
        this.applicationEventPublisher.publishEvent(new BooksLoadEvent(bookList));
        return bookList;
    }


    @Transactional(readOnly = true)
    Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    Book addNewBook(Book book) {
        var newBook = bookRepository.save(book);
        var newBookEvent = new NewBookEvent(newBook);
        this.applicationEventPublisher.publishEvent(newBookEvent);
        return newBook;

    }
}

