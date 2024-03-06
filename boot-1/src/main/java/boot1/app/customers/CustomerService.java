package boot1.app.customers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Slf4j
@Service
@Transactional
class CustomerService {

    private final JdbcTemplate jt;

    private final ApplicationEventPublisher applicationEventPublisher;

    CustomerService(JdbcTemplate jt, ApplicationEventPublisher applicationEventPublisher) {
        this.jt = jt;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    private final RowMapper<Customer> customerRowMapper = (ResultSet rs, int rowNum) ->
            new Customer(rs.getInt("id"), rs.getString("name"));


    Customer add(String customerName) {
        var keys = new ArrayList<Map<String, Object>>();
        keys.add(Map.of("id", Integer.class));
        var keyGenerator = new GeneratedKeyHolder(keys);

        var returnValue = this.jt.update(psc -> {
            var ps = psc.prepareStatement("""
                            insert into customer ( name ) values ( ? )
                            on conflict on constraint customer_name_key
                            do update
                            set name = excluded.name
                            """,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customerName);
            return ps;
        }, keyGenerator);

        var genId = (Integer) keyGenerator.getKeys().get("id");

        var customer = byId(genId);
        this.applicationEventPublisher.publishEvent(new CustomerCreatedEvent(customer));
        return customer;

    }

    Customer byId(Integer id) {
        return
                this.jt.queryForObject("select id , name from customer where id = ? "
                        , customerRowMapper
                        , id);

    }

    Collection<Customer> find() {
        var custList = this.jt.query("select id , name from customer ",
                customerRowMapper);
        var customerEvents = new CustomersLoadEvent(custList);
        this.applicationEventPublisher.publishEvent(customerEvents);
        return custList;
    }


}
