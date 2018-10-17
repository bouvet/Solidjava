package before;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class CustomerStorage {

    private static final String FOLDER = "customers";

    void writeToFile(String name, Customer customer) {
        File folder = new File(FOLDER);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File file = new File(folder, name + ".data");
        List<String> content = new ArrayList<>();
        content.add(customer.getName());
        content.add(customer.getEmail());
        try {
            Files.write(file.toPath(), content, StandardOpenOption.CREATE);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> readAllFromFile() {
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

    public Customer get(String name) {
        return readFromFile(new File(FOLDER + File.separator + name + ".data"));
    }

    public void reset() {
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
