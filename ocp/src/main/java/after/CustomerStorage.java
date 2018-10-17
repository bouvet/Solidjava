package after;

import java.util.List;

public interface CustomerStorage {

    void save(Customer customer);

    List<Customer> getAll();

    Customer get(String name);

    void reset();
}
