package model;

public class WithdrawalTransaction extends Transaction{
	public WithdrawalTransaction(int accountId, double amount, java.sql.Timestamp timestamp) {
        this.setAccountId(accountId);
        this.setTransactionType("Withdrawal");
        this.setAmount(amount);
        this.setTransactionDate(timestamp);
    }
}
