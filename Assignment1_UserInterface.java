// UserInterface.java
import java.util.Scanner;

public class UserInterface {
    private Account[] accounts;
    private int accountCount;
    private Scanner scanner;
    private static int nextAccountNumber = 1001;

    public UserInterface(int maxAccounts) {
        accounts = new Account[maxAccounts];
        accountCount = 0;
        scanner = new Scanner(System.in);
    }

    // Create a new account
    public void createAccount() {
        if (accountCount >= accounts.length) {
            System.out.println("Maximum account limit reached!");
            return;
        }

        try {
            System.out.print("Enter account holder name: ");
            scanner.nextLine(); // Clear buffer
            String name = scanner.nextLine();

            System.out.print("Enter initial deposit amount: ");
            double initialDeposit = scanner.nextDouble();

            if (initialDeposit < 0) {
                System.out.println("Initial deposit cannot be negative!");
                return;
            }

            scanner.nextLine(); // Clear buffer
            System.out.print("Enter email address: ");
            String email = scanner.nextLine();

            System.out.print("Enter phone number: ");
            String phone = scanner.nextLine();

            Account newAccount = new Account(nextAccountNumber, name, initialDeposit, email, phone);
            accounts[accountCount++] = newAccount;
            System.out.println("Account created successfully with Account Number: " + nextAccountNumber);
            nextAccountNumber++;
        } catch (Exception e) {
            System.out.println("Error creating account. Please enter valid data.");
            scanner.nextLine(); // Clear buffer
        }
    }

    // Find account by account number
    private Account findAccount(int accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return accounts[i];
            }
        }
        return null;
    }

    // Perform deposit
    public void performDeposit() {
        try {
            System.out.print("Enter account number: ");
            int accountNumber = scanner.nextInt();

            Account account = findAccount(accountNumber);
            if (account == null) {
                System.out.println("Account not found!");
                return;
            }

            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();

            account.deposit(amount);
        } catch (Exception e) {
            System.out.println("Error processing deposit. Please enter valid data.");
            scanner.nextLine(); // Clear buffer
        }
    }

    // Perform withdrawal
    public void performWithdrawal() {
        try {
            System.out.print("Enter account number: ");
            int accountNumber = scanner.nextInt();

            Account account = findAccount(accountNumber);
            if (account == null) {
                System.out.println("Account not found!");
                return;
            }

            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();

            account.withdraw(amount);
        } catch (Exception e) {
            System.out.println("Error processing withdrawal. Please enter valid data.");
            scanner.nextLine(); // Clear buffer
        }
    }

    // Show account details
    public void showAccountDetails() {
        try {
            System.out.print("Enter account number: ");
            int accountNumber = scanner.nextInt();

            Account account = findAccount(accountNumber);
            if (account == null) {
                System.out.println("Account not found!");
                return;
            }

            account.displayAccountDetails();
        } catch (Exception e) {
            System.out.println("Error displaying account. Please enter valid data.");
            scanner.nextLine(); // Clear buffer
        }
    }

    // Update contact details
    public void updateContact() {
        try {
            System.out.print("Enter account number: ");
            int accountNumber = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            Account account = findAccount(accountNumber);
            if (account == null) {
                System.out.println("Account not found!");
                return;
            }

            System.out.print("Enter new email address: ");
            String email = scanner.nextLine();

            System.out.print("Enter new phone number: ");
            String phone = scanner.nextLine();

            account.updateContactDetails(email, phone);
        } catch (Exception e) {
            System.out.println("Error updating contact. Please enter valid data.");
            scanner.nextLine(); // Clear buffer
        }
    }

    // Main menu
    public void mainMenu() {
        int choice = 0;

        do {
            System.out.println("\n===== Welcome to the Banking Application! =====");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        performDeposit();
                        break;
                    case 3:
                        performWithdrawal();
                        break;
                    case 4:
                        showAccountDetails();
                        break;
                    case 5:
                        updateContact();
                        break;
                    case 6:
                        System.out.println("Thank you for using our banking application!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1-6.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear buffer
                choice = 0;
            }
        } while (choice != 6);

        scanner.close();
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface(100); // Max 100 accounts
        ui.mainMenu();
    }
}