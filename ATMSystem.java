import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATMSystem {
    private Map<String, User> userDatabase;
    private User currentUser;
    private Scanner scanner; // Declare a single Scanner for input

    public ATMSystem() {
        userDatabase = new HashMap<>();
        // Add sample users to the database (you can replace this with a database or file-based storage)
        userDatabase.put("user1", new User("Baivab", 1234, 1500.0));
        userDatabase.put("user2", new User("Octanet", 5678, 2000.0));
        scanner = new Scanner(System.in); // Initialize the Scanner
    }

    public void start() {
        System.out.println("Welcome to the ATM System!");

        while (true) {
            if (currentUser == null) {
                System.out.print("Enter User ID: ");
                String userID = scanner.nextLine();
                System.out.print("Enter PIN: ");
                int pin = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (authenticateUser(userID, pin)) {
                    System.out.println("Authentication successful. Welcome, " + userID + "!");
                } else {
                    System.out.println("Authentication failed. Please try again.");
                }
            } else {
                // User is authenticated, show menu
                System.out.println("ATM Menu:");
                System.out.println("1. Check Balance");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Logout");

                System.out.print("Select an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        withdraw();
                        break;
                    case 3:
                        deposit();
                        break;
                    case 4:
                        transfer();
                        break;
                    case 5:
                        logout();
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }

    private boolean authenticateUser(String userID, int pin) {
        if (userDatabase.containsKey(userID)) {
            User user = userDatabase.get(userID);
            if (user.getPin() == pin) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    private void checkBalance() {
        System.out.println("Your balance is: $" + currentUser.getAccountBalance());
    }

    private void withdraw() {
        System.out.print("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();

        if (amount > 0 && amount <= currentUser.getAccountBalance()) {
            currentUser.setAccountBalance(currentUser.getAccountBalance() - amount);
            System.out.println("Withdrawal successful. Your new balance is: $" + currentUser.getAccountBalance());
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    private void deposit() {
        System.out.print("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            currentUser.setAccountBalance(currentUser.getAccountBalance() + amount);
            System.out.println("Deposit successful. Your new balance is: $" + currentUser.getAccountBalance());
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    private void transfer() {
        System.out.print("Enter the user ID to transfer to: ");
        String recipientID = scanner.nextLine();
        System.out.print("Enter the amount to transfer: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        if (userDatabase.containsKey(recipientID) && amount > 0 && amount <= currentUser.getAccountBalance()) {
            User recipient = userDatabase.get(recipientID);
            currentUser.setAccountBalance(currentUser.getAccountBalance() - amount);
            recipient.setAccountBalance(recipient.getAccountBalance() + amount);
            System.out.println("Transfer successful.");
            System.out.println("Your new balance is: $" + currentUser.getAccountBalance());
        } else {
            System.out.println("Invalid recipient or amount or insufficient funds.");
        }
    }

    private void logout() {
        currentUser = null;
        System.out.println("Logged out successfully.");
    }

    public static void main(String[] args) {
        ATMSystem atm = new ATMSystem();
        atm.start();
        atm.scanner.close(); // Close the scanner when the program is done
    }
}

