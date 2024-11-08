package model;

import java.sql.Timestamp;

public class Account {
    private int accountId;
    private int customerId;
    private String accountType;
    private double balance;
    private Timestamp createdAt;
    public Account(int accountId, int customerId, double balance, String accountType) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.balance = balance;
        this.accountType = accountType;
        this.createdAt = new Timestamp(System.currentTimeMillis()); // Set to current time by default
    }
    public Account() {};
	public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public int getaccountId() {
        return accountId;
    }

    public void setaccountId(int accountId) {
        this.accountId = accountId;
    }
    public String getaccountType() {
        return accountType;
    }

    public void setaccountType(String accountType) {
        this.accountType = accountType;
    }
    public double getbalance() {
        return balance;
    }

    public void setbalance(double balance) {
        this.balance = balance;
    }
    public Timestamp getcreatedAt() {
    	return createdAt;
    }
    public void setcreatedAt(Timestamp createdAt) {
    	this.createdAt = createdAt;
    }
}
