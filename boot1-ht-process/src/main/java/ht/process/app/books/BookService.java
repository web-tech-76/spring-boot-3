package ht.process.app.books;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Service
@Slf4j
class BookService {


    private final JdbcTemplate jdbcTemplate;

    private final ApplicationEventPublisher applicationEventPublisher;
    private final RowMapper<Book> rowMapper = (ResultSet rs, int rowNum) -> new Book(
            rs.getInt("id"),
            rs.getString("book_name"),
            rs.getString("author"),
            rs.getDouble("price")
    );

    BookService(JdbcTemplate jdbcTemplate, ApplicationEventPublisher applicationEventPublisher) {
        this.jdbcTemplate = jdbcTemplate;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional(readOnly = true)
    Collection<Book> getAllBooks() {
        String sql = """
                       select * from books
                """;
        var bookList = jdbcTemplate.query(sql, rowMapper);
        var bookLoadEvent = new BooksLoadEvent(bookList);
        this.applicationEventPublisher.publishEvent(bookLoadEvent);
        return bookList;
    }


    @Transactional(readOnly = true)
    Book getBookById(int id) {
        String sql = """
                       select * from books where id = ?
                """;
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    Book addNewBook(Book book) {

        var keys = new ArrayList<Map<String, Object>>();
        keys.add(Map.of("id", Integer.class));
        var keyGenHolder = new GeneratedKeyHolder(keys);

        var sql = """
                    insert into books (book_name, author, price) values(?, ? ,?)
                    on conflict on constraint books_book_name_author_key do update
                    set
                    book_name = excluded.book_name,
                    author = excluded.author
                """;
        var resultValue = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                var ps = con.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(Parameters.NAME.ordinal(), book.name());
                ps.setString(Parameters.AUTHOR.ordinal(), book.author());
                ps.setDouble(Parameters.PRICE.ordinal(), book.price());
                return ps;
            }
        }, keyGenHolder);

        var genId = (Integer) keyGenHolder.getKeys().get("id");
        var newBook = getBookById(genId);
        log.info("newly added book {}", newBook);
        var newBookEvent = new NewBookEvent(newBook);
        this.applicationEventPublisher.publishEvent(newBookEvent);

        return newBook;

    }
}

enum Parameters {
    ID, NAME, AUTHOR, PRICE
}