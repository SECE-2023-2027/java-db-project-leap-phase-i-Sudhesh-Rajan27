package model;

public class CurrentAccount extends Account {
    private double overdraftLimit;

    // Constructor
    public CurrentAccount(int accountId, int customerId, double balance, String accountType, double overdraftLimit) {
        super(accountId, customerId, balance, accountType);
        this.overdraftLimit = overdraftLimit;
    }
    public CurrentAccount() {}
    // Getter and setter
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    // Method to check if a withdrawal is within overdraft limit
    public boolean canWithdraw(double amount) {
        return getbalance() + overdraftLimit >= amount;
    }

    @Override
    public String toString() {
        return "CurrentAccount{" +
                "accountId=" + getaccountId() +
                ", customerId=" + getCustomerId() +
                ", balance=" + getbalance() +
                ", accountType='" + getaccountType() + '\'' +
                ", overdraftLimit=" + overdraftLimit +
                '}';
    }

    // Additional methods specific to current accounts can go here
}
