package hateos.app.persons;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.Collection;

@Slf4j
@Service
class PersonService {

    private final JdbcTemplate jdbcTemplate;

    private final ApplicationEventPublisher applicationEventPublisher;

    public PersonService(JdbcTemplate jdbcTemplate, ApplicationEventPublisher applicationEventPublisher) {
        this.jdbcTemplate = jdbcTemplate;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    private final RowMapper<Person> rowMapper() {
        return (ResultSet resultset, int rowId) ->
                new Person(resultset.getInt("id"),
                        resultset.getInt("age"),
                        resultset.getFloat("salary"));
    }


    Collection<Person> all() {
        var peronList = this.jdbcTemplate.query("select id,age, salary from person", rowMapper());
        this.applicationEventPublisher.publishEvent(new PersonLoadEvent(peronList));
        return peronList;
    }

    Person byId(Integer id) {
        var person = all()
                .stream()
                .filter(p -> p.id() == id)
                .findFirst().get();

        return person;
    }


}
