package after.config;

import after.Customer;
import after.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

@Slf4j
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Configuration.class);
        CustomerService customerService = context.getBean(CustomerService.class);

        printMenu();
        int command;
        while ((command = readCommand()) > 0) {
            switch (command) {
                case 1:
                    createNewCustomer(customerService); break;
                case 2:
                    listAllCustomers(customerService); break;
                case 3:
                    readDetailsOnCustomer(customerService); break;
                case 4:
                    resetStoredCustomers(customerService); break;
                default:
                    System.out.println("Invalid command: " + command);
            }
            printMenu();
        }
    }

    private static void printMenu() {
        System.out.println(" ");
        System.out.println("Welcome to the Customer database");
        System.out.println("Select an operation:");
        System.out.println("1 - Create new Customer");
        System.out.println("2 - List all Customers");
        System.out.println("3 - Read details on Customer");
        System.out.println("4 - Reset stored Customers");
        System.out.println("0 - Exit");
    }

    private static void createNewCustomer(CustomerService customerService) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a customer name:");
        String name = scanner.next();
        System.out.println("Enter a customer email:");
        String email = scanner.next();
        try {
            customerService.createCustomer(name, email);
        }
        catch (IllegalArgumentException iae) {
            System.err.println("Validation error on new customer data");
        }
        catch (Exception e) {
            System.err.println("Unexpected error when creating new customer!");
        }
    }

    private static void listAllCustomers(CustomerService customerService) {
        List<Customer> customers;
        try {
            customers = customerService.getAllCustomers();
            if (customers.isEmpty()) {
                System.out.println("No stored Customers");
            }
            else {
                System.out.println("All stored Customers:");
                for (Customer customer : customers) {
                    System.out.println(customer.getName() + " - " + customer.getEmail());
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readDetailsOnCustomer(CustomerService customerService) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a customer name:");
        String name = scanner.next();
        try {
            Customer customer = customerService.findCustomerByName(name);
            if (customer == null) {
                System.err.println("No customer named: " + name + " found");
            }
            else {
                System.out.println("Customer details:");
                System.out.println(customer.getName() + " - " + customer.getEmail());
            }
        }
        catch (Exception e) {
            System.err.println("Invalid input for reading customer data");
        }
    }

    private static void resetStoredCustomers(CustomerService customerService) {
        try {
            customerService.resetStorage();
            System.out.println("Stored Customers reset");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int readCommand() {
        String input = new Scanner(System.in).next();
        try {
            return Integer.parseInt(input);
        }
        catch (Exception e) {
            return 0;
        }
    }
}
