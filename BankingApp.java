import java.util.Scanner;

class Account {
    int accountNumber;
    String accountHolderName;
    double balance;
    String email;
    double phoneNumber;

    // Constructor
    Account(int accountNumber, String accountHolderName, double balance, String email, double phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Deposit method
    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // Withdraw method
    void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Successfully withdrawn: " + amount);
        } else if (amount <= 0) {
            System.out.println("Invalid withdrawal amount!");
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    // Display account details
    void displayAccountDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
    }

    // Update contact details
    void updateContactDetails(String email, double phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println("Contact details updated successfully!");
    }
}

class UserInterface {
    Account[] accounts;
    int accountCount;
    int nextAccountNumber = 1001;
    Scanner sc;

    UserInterface(int size) {
        accounts = new Account[size];
        accountCount = 0;
        sc = new Scanner(System.in);
    }

    Account findAccount(int accNo) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].accountNumber == accNo) {
                return accounts[i];
            }
        }
        return null; 
    }

    void createAccount() {
        int accNo = nextAccountNumber++; 

        sc.nextLine();
        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Phone Number: ");
        double phone = sc.nextDouble();

        accounts[accountCount++] = new Account(accNo, name, balance, email, phone);
        System.out.println("Account created successfully!");
        System.out.println("Your Account Number is: " + accNo);
    }

    void performDeposit() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        System.out.print("Enter Amount to Deposit: ");
        double amount = sc.nextDouble();
        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.deposit(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    void performWithdrawal() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        System.out.print("Enter Amount to Withdraw: ");
        double amount = sc.nextDouble();
        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.withdraw(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    void showAccountDetails() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.displayAccountDetails();
        } else {
            System.out.println("Account not found!");
        }
    }

    void updateContact() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        sc.nextLine(); // consume newline
        Account acc = findAccount(accNo);
        if (acc != null) {
            System.out.print("Enter New Email: ");
            String newEmail = sc.nextLine();
            System.out.print("Enter New Phone Number: ");
            double newPhone = sc.nextDouble();
            acc.updateContactDetails(newEmail, newPhone);
        } else {
            System.out.println("Account not found!");
        }
    }

    void mainMenu() {
        while (true) {
            System.out.println("\n--- Banking Application ---");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> performDeposit();
                case 3 -> performWithdrawal();
                case 4 -> showAccountDetails();
                case 5 -> updateContact();
                case 6 -> { 
                    System.out.println("Thank you for using Banking Application!");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

public class BankingApp {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface(100);
        ui.mainMenu();
    }
}
