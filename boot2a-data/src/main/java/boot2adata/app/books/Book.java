package boot2adata.app.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Collection;
import java.util.List;

@Table(name = "books")
@Data
@AllArgsConstructor
public class Book {
    @Id
    private Integer id;
    @Column("bookname")
    private final String bookName;
    private final String author;
    private final double price;

    @Column("isavailable")
    private boolean isAvailable;

    @DomainEvents
    Collection<NewBookEvent> newBookEvents() {
        return List.of(new NewBookEvent(this));
    }

}
