package after;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerValidator validator;
    private final CustomerWriter writer;
    private final CustomerReader reader;

    @Autowired
    public CustomerService(CustomerValidator validator, CustomerWriter writer, CustomerReader reader) {
        this.validator = validator;
        this.writer = writer;
        this.reader = reader;
    }

    public final Customer createCustomer(String name, String email) {

        validator.validateName(name);
        validator.validateEmail(email);

        Customer customer = Customer.builder()
                .name(name)
                .email(email)
                .build();

        writer.write(customer);

        return customer;
    }


    public List<Customer> getAllCustomers() {
        return reader.readAll();
    }

    public Customer findCustomerByName(String name) {
        return reader.read(name);
    }

    public void resetStorage() {
        writer.reset();
    }
}
