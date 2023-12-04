import java.util.Scanner;

class BankAccount {
    private int balance;

    public BankAccount(int initialAmount) {
        this.balance = initialAmount;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit of " + amount + "Rs was successful. New balance: " + balance);
        } else {
            System.out.println("Invalid entry! Please choose an amount above 0.");
        }
    }

    public void withdraw(int amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal of " + amount + "Rs was successful. New balance: " + balance);
        } else {
            System.out.println("Invalid entry!! Insufficient balance or negative value entered.");
        }
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void displayOptions() {
        System.out.println("\nSelect Options:");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        int option;

        do {
            displayOptions();
            System.out.println("Pick an option:");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Enter amount to deposit:");
                    int depositAmount = sc.nextInt();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.println("Enter amount to withdraw:");
                    int withdrawAmount = sc.nextInt();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.println("Current balance: " + account.getBalance() + "Rs");
                    break;
                case 4:
                    System.out.println("Thanks for using the ATM. Have a great day!");
                    break;
                default:
                    System.out.println("Invalid entry!! Enter only the given options.");
            }
        } while (option != 4);

        sc.close();
    }
}

public class ATMInterface {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount userAccount = new BankAccount(30000);
        ATM atm = new ATM(userAccount);

        System.out.println("Enter Your PIN:");
        String pin = sc.nextLine();

        if ("123123".equals(pin)) {
            System.out.println("Welcome to Jain Bank ATM!");
            atm.run();
        } else {
            System.out.println("Wrong PIN. Exiting...");
        }
        sc.close();
    }
}
