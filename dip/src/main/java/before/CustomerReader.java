package before;

import java.util.List;

public interface CustomerReader {

    Customer read(String name);

    List<Customer> readAll();

}
