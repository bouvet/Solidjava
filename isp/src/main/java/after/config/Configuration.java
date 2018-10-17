package after.config;

import after.*;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public CustomerService customerService(CustomerValidator validator, CustomerWriter customerWriter, CustomerReader customerReader) {
        return new CustomerService(validator, customerWriter, customerReader);
    }

    @Bean
    public CustomerWriter customerWriter(CloudCustomerStorage storage) {
        return storage;
    }

    @Bean
    public CustomerReader customerReader(CloudCustomerStorage storage) {
        return storage;
    }

    @Bean
    public CloudCustomerStorage cloudCustomerStorage() {
        return new CloudCustomerStorage();
    }

    @Bean
    public CustomerValidator customerValidator() {
        return new SimpleCustomerValidator();
    }
}
