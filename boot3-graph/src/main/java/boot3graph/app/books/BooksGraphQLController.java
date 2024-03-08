package boot3graph.app.books;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BooksGraphQLController {

    private final BookRepository bookRepository;

    //@SchemaMapping(typeName = "Query", field = "books")
    @QueryMapping
    public Iterable<Book> books() {
        return bookRepository.findAll();
    }

    //@SchemaMapping(typeName = "Query", field = "bookById")
    @QueryMapping
    public Book bookById(@Argument Integer id) {
        return bookRepository.findById(id).orElseGet(null);
    }

    @BatchMapping
    Map<Book, Store> store(List<Book> books) {
        var storeMap = new HashMap<Book, Store>();
        for (var b : books) {
            storeMap.put(b, new Store(b.getId()));
        }
        return storeMap;
    }


/*
    @SchemaMapping(typeName = "Book")
    Store store(Book book) throws Exception {
        log.info("new book id {} ", book.getId());
        Thread.sleep(1000);
        return new Store(book.getId());
    }
*/


    /*@SchemaMapping(typeName = "Book")
    Mono<Store> store(Book book) {
        log.info("new book id {} ", book.getId());
        return Mono.just(new Store(book.getId())).delayElement(Duration.ofSeconds(1l));
    }*/


    @MutationMapping
    public Book createBook(@Argument String bookName, @Argument String author, @Argument Double price, @Argument Boolean isAvailable) {

        return this.bookRepository.save(
                new Book(
                        null,
                        bookName,
                        author,
                        price,
                        isAvailable));
    }

    //@SchemaMapping(typeName = "Query", field = "bookByBookName")
    @QueryMapping
    public Iterable<Book> bookByBookName(@Argument String name) {
        return bookRepository.findBookByBookName(name);
    }


}


record Store(Integer id) {
}
