package main;
import Dao.BankDAO;
import Dao.CustomerDAO;
import Dao.AccountDAO;
import Dao.TransactionDAO;
import model.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class BankSystemApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AccountDAO accountDAO = new AccountDAO();
    private static final TransactionDAO transactionDAO = new TransactionDAO();

    public static void main(String[] args) {
        BankDAO bankDAO = new BankDAO();
        CustomerDAO customerDAO = new CustomerDAO();

        // Main Menu
        while (true) {
            System.out.println("\nBank System Menu:");
            System.out.println("1. Add Bank");
            System.out.println("2. Add Customer");
            System.out.println("3. Open Account");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Show Transaction History");
            System.out.println("7. Show Bank Statement");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addBank(bankDAO);
                case 2 -> addCustomer(customerDAO);
                case 3 -> openAccount();
                case 4 -> deposit();
                case 5 -> withdraw();
                case 6 -> showTransactionHistory();
                case 7 -> showBankStatement();
                case 8 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Add Bank
    private static void addBank(BankDAO bankDAO) {
        System.out.print("Enter bank name: ");
        String bankName = scanner.next();
        System.out.print("Enter bank branch: ");
        String bankBranch = scanner.next();

        Bank bank = new Bank();
        bank.setBankName(bankName);
        bank.setBankBranch(bankBranch);

        try {
            bankDAO.insertBank(bank);
            System.out.println("Inserted Bank: " + bank.getBankName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add Customer
    private static void addCustomer(CustomerDAO customerDAO) {
        System.out.print("Enter customer name: ");
        String customerName = scanner.next();
        System.out.print("Enter customer address: ");
        String customerAddress = scanner.next();
        System.out.print("Enter customer phone: ");
        String customerPhone = scanner.next();

        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setCustomerAddress(customerAddress);
        customer.setCustomerPhone(customerPhone);

        try {
            customerDAO.insertCustomer(customer);
            System.out.println("Inserted Customer: " + customer.getCustomerName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Open Account (Savings or Current)
    private static void openAccount() {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        System.out.print("Enter account type (1 for Savings, 2 for Current): ");
        int accountTypeChoice = scanner.nextInt();

        try {
            Account account;
            if (accountTypeChoice == 1) {
                System.out.print("Enter interest rate for Savings Account: ");
                double interestRate = scanner.nextDouble();
                account = new SavingsAccount(00, customerId, balance, "Savings", interestRate);
                ((SavingsAccount) account).setInterestRate(interestRate);
            } else if (accountTypeChoice == 2) {
                System.out.print("Enter overdraft limit for Current Account: ");
                double overdraftLimit = scanner.nextDouble();
                account = new CurrentAccount(0, customerId, balance, "Current", overdraftLimit);
                ((CurrentAccount) account).setOverdraftLimit(overdraftLimit);
            } else {
                System.out.println("Invalid account type. Please try again.");
                return;
            }

            accountDAO.insertAccount(account);
            System.out.println("Account created successfully: " + account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Deposit
    private static void deposit() {
        System.out.print("Enter account ID: ");
        int accountId = scanner.nextInt();
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();

        try {
            accountDAO.updateBalance(accountId, amount);
            transactionDAO.insertTransaction(new Transaction(0, accountId, "Deposit", amount, new Timestamp(System.currentTimeMillis())));
            System.out.println("Deposit successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Withdraw
    private static void withdraw() {
        System.out.print("Enter account ID: ");
        int accountId = scanner.nextInt();
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();

        try {
            Account account = accountDAO.getAccountById(accountId);
            if (account instanceof CurrentAccount) {
                if (((CurrentAccount) account).canWithdraw(amount)) {
                    accountDAO.updateBalance(accountId, -amount);
                    transactionDAO.insertTransaction(new Transaction(0, accountId, "Withdrawal", amount, new Timestamp(System.currentTimeMillis())));
                    System.out.println("Withdrawal successful.");
                } else {
                    System.out.println("Withdrawal amount exceeds overdraft limit.");
                }
            } else if (account instanceof SavingsAccount) {
                if (account.getbalance() >= amount) {
                    accountDAO.updateBalance(accountId, -amount);
                    transactionDAO.insertTransaction(new Transaction(0, accountId, "Withdrawal", amount, new Timestamp(System.currentTimeMillis())));
                    System.out.println("Withdrawal successful.");
                } else {
                    System.out.println("Insufficient balance.");
                }
            } else {
                System.out.println("Account not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Show Transaction History
    private static void showTransactionHistory() {
        System.out.print("Enter account ID: ");
        int accountId = scanner.nextInt();

        try {
            List<Transaction> transactions = transactionDAO.getTransactionsByAccountId(accountId);
            System.out.println("Transaction History for Account ID: " + accountId);
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Show Bank Statement
    private static void showBankStatement() {
        System.out.print("Enter account ID: ");
        int accountId = scanner.nextInt();

        try {
            Account account = accountDAO.getAccountById(accountId);
            List<Transaction> transactions = transactionDAO.getTransactionsByAccountId(accountId);

            System.out.println("Bank Statement for Account ID: " + accountId);
            System.out.println("Current Balance: " + account.getbalance());
            System.out.println("Transactions:");
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
