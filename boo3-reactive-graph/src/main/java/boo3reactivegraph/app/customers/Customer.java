package boo3reactivegraph.app.customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "customer")
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class Customer {

    @Id
    private Integer id;

    private String name;

    @Column("isloggedin")
    private Boolean isLoggedIn;

}
