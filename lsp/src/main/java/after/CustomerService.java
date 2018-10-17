package after;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerValidator validator;
    private final CustomerStorage storage;

    @Autowired
    public CustomerService(CustomerValidator validator, CustomerStorage storage) {
        this.validator = validator;
        this.storage = storage;
    }

    public final Customer createCustomer(String name, String email) {

        validator.validateName(name);
        validator.validateEmail(email);

        Customer customer = Customer.builder()
                .name(name)
                .email(email)
                .build();

        storage.save(customer);

        return customer;
    }


    public List<Customer> getAllCustomers() {
        return storage.getAll();
    }

    public Customer findCustomerByName(String name) {
        return storage.get(name);
    }

    public void resetStorage() {
        storage.reset();
    }
}
