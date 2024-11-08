package model;

public class SavingsAccount extends Account {
    private double interestRate;

    // Constructor
    public SavingsAccount(int accountId, int customerId, double balance, String accountType, double interestRate) {
        super(accountId, customerId, balance, accountType);
        this.interestRate = interestRate;
    }
    public SavingsAccount() {}    // Getter and setter
    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    // Method to calculate interest based on balance
    public double calculateInterest() {
        return getbalance() * interestRate;
    }

    @Override
    public String toString() {
        return "SavingsAccount{" +
                "accountId=" + getaccountId() +
                ", customerId=" + getCustomerId() +
                ", balance=" + getbalance() +
                ", accountType='" + getaccountType() + '\'' +
                ", interestRate=" + interestRate +
                '}';
    }

    // Additional methods specific to savings accounts can go here
}
