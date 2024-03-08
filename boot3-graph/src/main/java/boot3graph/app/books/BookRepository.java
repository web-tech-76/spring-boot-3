package boot3graph.app.books;

import org.springframework.data.repository.CrudRepository;


interface BookRepository extends CrudRepository<Book, Integer> {

    Iterable<Book> findBookByBookName(String name);

}
