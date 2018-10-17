package after;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class CloudCustomerStorage implements CustomerReader, CustomerWriter, CloudStorage {

    private final Map<String, Customer> _cloud = new HashMap<>(); // Such cloud.

    @Override
    public void write(Customer customer) {
        _cloud.put(customer.getName(), customer);
    }

    @Override
    public List<Customer> readAll() {
        return new ArrayList<>(_cloud.values());
    }

    @Override
    public Customer read(String name) {
        return _cloud.get(name);
    }

    @Override
    public void reset() {
        _cloud.clear();
    }

    @Override
    public String getUrl() {
        return "https://mycloud.com";
    }
}
