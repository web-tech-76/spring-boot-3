package boot1.app.config.services;


/*
public class TransactionalCustomerService extends DefaultCustomerService {

    private final TransactionTemplate tt;

    public TransactionalCustomerService(JdbcTemplate jt, TransactionTemplate tt) {
        //super(jt);
        this.tt = tt;
    }

    @Override
    public Customer add(String customerName) {
        return this.tt.execute(status -> super.add(customerName));
    }

    @Override
    public Customer byId(Integer id) {
        return this.tt.execute(status -> super.byId(id));
    }

    @Override
    public Collection<Customer> find() {
        return this.tt.execute(status -> super.find());
    }
}
*/
