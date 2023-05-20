import java.util.Scanner;

public class ATM {
    private static final int MAX_PIN_ATTEMPTS = 3;
    private static final int MIN_BALANCE = 0;
    private static final int MAX_WITHDRAWAL = 10000;
    private static final int MIN_WITHDRAWAL = 500;
    private static final int PIN_LENGTH = 4;
    private static final int ACCOUNT_NUMBER = 123456;
    private static final int PIN = 1234;
    private static final double BALANCE = 1000;

    private static Scanner scanner = new Scanner(System.in);

    private static int pinAttempts = 0;
    private static double balance = BALANCE;

    public static void main(String[] args) {
        int choice;
        boolean authenticated = false;

        System.out.println("Welcome to the ATM!");
        System.out.println("Please insert your card.");
        System.out.println("Enter your account number:");

        int accountNumber = scanner.nextInt();

        if (accountNumber == ACCOUNT_NUMBER) {
            while (pinAttempts < MAX_PIN_ATTEMPTS && !authenticated) {
                System.out.println("Enter your PIN:");
                int pin = scanner.nextInt();
                authenticated = (pin == PIN);
                if (!authenticated) {
                    System.out.println("Invalid PIN. Please try again.");
                    pinAttempts++;
                }
            }

            if (authenticated) {
                System.out.println("Authentication successful.");
                while (true) {
                    System.out.println("ATM MENU:");
                    System.out.println("1. Check balance");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Deposit");
                    System.out.println("4. Exit");
                    System.out.println("Enter your choice:");
                    choice = scanner.nextInt();

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
                            System.out.println("Thank you for using the ATM!");
                            System.exit(0);
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                }
            } else {
                System.out.println("Authentication failed. Your card has been retained.");
                System.exit(0);
            }
        } else {
            System.out.println("Invalid account number. Your card has been retained.");
            System.exit(0);
        }
    }

    private static void checkBalance() {
        System.out.println("Your balance is Rs" + balance);
    }

    private static void withdraw() {
        System.out.println("Enter the amount you wish to withdraw ");
        double withdrawalAmount = scanner.nextDouble();

        if (withdrawalAmount < MIN_WITHDRAWAL || withdrawalAmount > MAX_WITHDRAWAL) {
            System.out.println("Invalid withdrawal amount.");
            return;
        }

        if (withdrawalAmount > balance) {
            System.out.println("Insufficient funds.");
            return;
        }

        balance -= withdrawalAmount;
        System.out.println("Dispensing cash: Rs" + withdrawalAmount);
        System.out.println("Your new balance is Rs" + balance);
    }
    private static void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        balance += amount;
        System.out.println("Deposit successful. New balance is " + balance);
    }
}
