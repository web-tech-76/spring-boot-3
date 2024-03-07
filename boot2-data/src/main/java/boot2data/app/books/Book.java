package boot2data.app.books;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "books")
record Book(@Id int id, @Column("bookname") String bookName, String author, double price) {
}
