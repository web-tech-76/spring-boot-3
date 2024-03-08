package boot3graph.app.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "books")
@Data
@AllArgsConstructor
class Book {
    @Id
    private Integer id;
    @Column("bookname")
    private final String bookName;
    private final String author;
    private final double price;

    @Column("isavailable")
    private boolean isAvailable;

/*
    @DomainEvents
    Collection<NewBookEvent> newBookEvents() {
        return List.of(new NewBookEvent(this));
    }
*/

}
