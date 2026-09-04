import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankingSystem bank = new BankingSystem();
        Scanner scanner = new Scanner(System.in);

        Logic.run(bank, scanner);

        scanner.close();
    }
}
