package boo3reactivegraph.app.customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "customerdetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetails {

    @Id
    private Integer id;

    @Column("customerid")
    private Integer customerId;

    private String email;

    private String phone;

    private String address;

}
