import java.util.ArrayList;
import java.util.List;

public class BankingSystem {
    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Customer added successfully.");
    }

    public Customer findCustomer(int customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    public void displayAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }

        for (Customer customer : customers) {
            System.out.println("\n--------------------");
            customer.displayCustomer();
        }
    }
}
