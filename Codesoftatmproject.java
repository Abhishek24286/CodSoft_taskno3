import java.util.Scanner;

// Represents a bank account with basic operations
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance > 0 ? initialBalance : 0;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }
}

// Represents an ATM machine interacting with the bank account
class ATM {
    private final BankAccount account;
    private final Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void launch() {
        System.out.println("=====================================");
        System.out.println("      Welcome to Smart ATM 💳");
        System.out.println("=====================================");

        int selection;
        do {
            displayMenu();
            selection = getUserSelection();
            handleUserAction(selection);
        } while (selection != 4);

        System.out.println("Session ended. Thank you for banking with us!");
    }

    private void displayMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1️⃣  Withdraw Funds");
        System.out.println("2️⃣  Deposit Funds");
        System.out.println("3️⃣  Check Account Balance");
        System.out.println("4️⃣  Exit");
        System.out.print("Enter your choice: ");
    }

    private int getUserSelection() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void handleUserAction(int option) {
        switch (option) {
            case 1 -> processWithdrawal();
            case 2 -> processDeposit();
            case 3 -> displayBalance();
            case 4 -> {}
            default -> System.out.println("⚠️ Invalid choice. Please try again.");
        }
    }

    private void processWithdrawal() {
        System.out.print("Enter amount to withdraw: ₹");
        double amount = scanner.nextDouble();

        if (account.withdraw(amount)) {
            System.out.println("✅ Withdrawal of ₹" + amount + " successful.");
        } else {
            System.out.println("❌ Insufficient funds or invalid amount.");
        }
    }

    private void processDeposit() {
        System.out.print("Enter amount to deposit: ₹");
        double amount = scanner.nextDouble();

        if (account.deposit(amount)) {
            System.out.println("✅ ₹" + amount + " deposited successfully.");
        } else {
            System.out.println("❌ Deposit failed. Amount must be positive.");
        }
    }

    private void displayBalance() {
        System.out.printf("📊 Current Balance: ₹%.2f%n", account.getBalance());
    }
}

// Main class to run the ATM simulation
public class ATMApplication {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount(5000.0); // Customize initial balance here
        ATM atmInterface = new ATM(myAccount);
        atmInterface.launch();
    }
}
