package boot2data.app.books;


import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/books")
class BooksHateoasController {

    private final BookService bookService;

    private final BookModelAssembler bookModelAssembler;

    @GetMapping("available")
    @ResponseBody
        //HttpEntity<BookModel> getAllAvailableBooks() {
    CollectionModel<BookModel> getAllAvailableBooks() {

        //var books = bookService.getAllBooks();
//        var booksModel = new BooksCollectionModel(books);
//        var link1 = linkTo(methodOn(BooksHateoasController.class).getAllAvailableBooks()).withSelfRel();
//        var link2 = linkTo(methodOn(BooksHateoasController.class).getBook(null)).withSelfRel();

        //      booksModel.add(link1, link2);
        //return ResponseEntity.ok(booksModel);

        return bookModelAssembler.toCollectionModel(bookService.getAllBooks());
    }

    @GetMapping("{id}")
    @ResponseBody
        //HttpEntity<BookModel> getBook(@PathVariable @Nullable Integer id) {
    BookModel getBook(@PathVariable @Nullable Integer id) {
    /*    var book = bookService.getBookById(id);
        var booksModel = new BookModel(book);
        return booksModel;
    */
        return bookModelAssembler.toModel(bookService.getBookById(id));

    }

    @GetMapping("{id}/delete")
    @ResponseBody
        //HttpEntity<BookModel> getBook(@PathVariable @Nullable Integer id) {
    HttpEntity<?> deleteBook(@PathVariable @Nullable Integer id) {
    /*    var book = bookService.getBookById(id);
        var booksModel = new BookModel(book);
        return booksModel;
    */
        return ResponseEntity.ok().build();

    }


}

/*
@Data
@AllArgsConstructor
class BooksCollectionModel extends RepresentationModel<BooksCollectionModel> {

    private Collection<Book> bookCollection;
}
*/


@Data
@RequiredArgsConstructor
class BookModel extends RepresentationModel<BookModel> {

    private final Book book;

}


@Component
class BookModelAssembler extends RepresentationModelAssemblerSupport<Book, BookModel> {

    public BookModelAssembler() {
        super(BooksHateoasController.class, BookModel.class);
    }

    @Override
    public CollectionModel<BookModel> toCollectionModel(Iterable<? extends Book> entities) {
        var cm = super.toCollectionModel(entities);
        var link1 = linkTo(methodOn(BooksHateoasController.class)
                .getAllAvailableBooks()).withSelfRel();
        cm.add(link1);
        return cm;
    }

    @Override
    public BookModel toModel(Book book) {
        var resource = new BookModel(book);

        var link1 = linkTo(methodOn(BooksHateoasController.class).getAllAvailableBooks()).withSelfRel();
        var link2 = linkTo(methodOn(BooksHateoasController.class).getBook(book.id())).withSelfRel();
        var link3 = resource.addIf(book.price() > 100,
                () -> linkTo(methodOn(BooksHateoasController.class)
                        .deleteBook(book.id())).withRel("cancelIt"));

        resource.add(link1, link2);
        return resource;
    }


}


