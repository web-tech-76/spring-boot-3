package ht.process.app.books;


import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@Slf4j
@RequiredArgsConstructor
class BooksHateoasController {

    private final BookService bookService;

    @GetMapping("/available")
    HttpEntity<BooksCollectionModel> getAllAvailableBooks() {

        var books = bookService.getAllBooks();
        var booksModel = new BooksCollectionModel(books);
        var link1 = linkTo(methodOn(BooksHateoasController.class)
                .getAllAvailableBooks()).withSelfRel();
        var link2 = linkTo(methodOn(BooksHateoasController.class)
                .getBook(null)).withSelfRel();

        booksModel
                .add(link1, link2);
        return ResponseEntity.ok(booksModel);
    }

    @GetMapping("/book/{id}")
    HttpEntity<BookModel> getBook(@PathVariable @Nullable Integer id) {
        var book = bookService.getBookById(id);
        var booksModel = new BookModel(book);
        return ResponseEntity.ok(booksModel);
    }


    @Data
    @AllArgsConstructor
    static
    class BooksCollectionModel extends RepresentationModel<BooksCollectionModel> {

        private Collection<Book> bookCollection;
    }


    @Data
    @RequiredArgsConstructor
    static
    class BookModel extends RepresentationModel<BookModel> {

        private final Book book;

    }
}
