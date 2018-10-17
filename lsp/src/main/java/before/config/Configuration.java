package before.config;

import before.*;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public CustomerService customerService(CustomerValidator validator, CustomerStorage customerStorage) {
        return new CustomerService(validator, customerStorage);
    }

    @Bean
    public CustomerStorage customerStorage() {
        return new CloudCustomerStorage();
    }

    @Bean
    public CustomerValidator customerValidator() {
        return new ThirdPartyCustomerValidator();
    }
}
