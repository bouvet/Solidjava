package before;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FileCustomerStorage implements CustomerWriter, CustomerReader, FileStorage {

    private static final String FOLDER = "customers";

    @Override
    public void write(Customer customer) {
        log.debug("Saving to file...");
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
    }

    @Override
    public List<Customer> readAll() {
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

    @Override
    public Customer read(String name) {
        return readFromFile(new File(FOLDER + File.separator + name + ".data"));
    }

    @Override
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

    @Override
    public String getFileLocation() {
        return FOLDER;
    }

}