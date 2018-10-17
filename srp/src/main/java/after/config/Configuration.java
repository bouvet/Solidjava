package after.config;

import after.CustomerService;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean(name = "before")
    public CustomerService customerService() {
        return new CustomerService();
    }
}
