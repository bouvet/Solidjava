package before;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomerService {

    private static final String FOLDER = "customers";

    public Customer createCustomer(String name, String email) {

        // Validate
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Name can not be null");
        }
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher emailMatcher = emailPattern.matcher(email);
        if (StringUtils.isEmpty(email) || !emailMatcher.matches()) {
            throw new IllegalArgumentException("Email is empty or is invalid");
        }

        // Create customer
        Customer customer = Customer.builder()
                .name(name)
                .email(email)
                .build();

        // Persist customer
        File folder = new File(FOLDER);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File file = new File(folder, customer.getName() + ".data");
        List<String> content = new ArrayList<>();
        content.add(customer.getName());
        content.add(customer.getEmail());
        try {
            Files.write(file.toPath(), content, StandardOpenOption.CREATE);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return customer;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        File customerFolder =  new File(FOLDER);
        if (!customerFolder.exists()) {
            return customers;
        }
        File[] files = customerFolder.listFiles();
        if (files == null) {
            return customers;
        }
        for (File file : files) {
            Customer customer = readFromFile(file);
            if (customer != null) {
                customers.add(customer);
            }
        }
        return customers;
    }

    public Customer findCustomerByName(String name) {
        return readFromFile(new File(FOLDER + File.separator + name + ".data"));
    }

    public void resetStorage() {
        File folder = new File(FOLDER);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }

    private Customer readFromFile(File file) {
        try {
            List<String> content = Files.readAllLines(file.toPath());
            return Customer.builder()
                    .name(content.get(0))
                    .email(content.get(1))
                    .build();
        }
        catch (IOException e) {
            return null;
        }
    }


}
