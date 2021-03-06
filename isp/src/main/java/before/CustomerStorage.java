package before;

import java.util.List;

public interface CustomerStorage {

    void save(Customer customer);

    List<Customer> getAll();

    Customer get(String name);

    void reset();

    String getFileLocation();

    String getUrl();
}
