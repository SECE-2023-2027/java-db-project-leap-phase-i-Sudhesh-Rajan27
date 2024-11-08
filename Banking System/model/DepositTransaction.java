package model;


public class DepositTransaction extends Transaction{
	public DepositTransaction(int accountId, double amount, java.sql.Timestamp timestamp) {
        this.setAccountId(accountId);
        this.setTransactionType("Deposit");
        this.setAmount(amount);
        this.setTransactionDate(timestamp);
    }
}
