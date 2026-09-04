import java.util.Scanner;

public class Logic {
    public static void run(BankingSystem bank, Scanner scanner) {
        while (true) {
            System.out.println("\n===== BANKING SYSTEM =====");
            System.out.println("1. Add Customer");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Customer");
            System.out.println("5. Display All Customers");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter customer ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter customer name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter account number: ");
                    int accountNumber = scanner.nextInt();

                    System.out.print("Enter opening balance: ");
                    double balance = scanner.nextDouble();

                    bank.addCustomer(
                        new Customer(id, name, new Account(accountNumber, balance))
                    );
                    break;

                case 2:
                    System.out.print("Enter customer ID: ");
                    Customer depositCustomer = bank.findCustomer(scanner.nextInt());

                    if (depositCustomer != null) {
                        System.out.print("Enter amount: ");
                        depositCustomer.getAccount().deposit(scanner.nextDouble());
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter customer ID: ");
                    Customer withdrawCustomer = bank.findCustomer(scanner.nextInt());

                    if (withdrawCustomer != null) {
                        System.out.print("Enter amount: ");
                        withdrawCustomer.getAccount().withdraw(scanner.nextDouble());
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter customer ID: ");
                    Customer customer = bank.findCustomer(scanner.nextInt());

                    if (customer != null) {
                        customer.displayCustomer();
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;

                case 5:
                    bank.displayAllCustomers();
                    break;

                case 6:
                    System.out.println("Thank you for using the Banking System!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
