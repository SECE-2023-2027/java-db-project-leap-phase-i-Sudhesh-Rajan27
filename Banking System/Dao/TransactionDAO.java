package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Transaction;
import model.DepositTransaction;
import model.WithdrawalTransaction;

public class TransactionDAO {
	public void insertTransaction(Transaction transaction) throws SQLException {
        String sql = "INSERT INTO Transaction (account_id, type, amount, timestamp) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, transaction.getAccountId());
            stmt.setString(2, transaction.getTransactionType());
            stmt.setDouble(3, transaction.getAmount());
            stmt.setTimestamp(4, transaction.getTransactionDate());
            stmt.executeUpdate();
        }
    }

    // Retrieve all transactions for a specific account ID
    public List<Transaction> getTransactionsByAccountId(int accountId) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transaction WHERE account_id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setTransactionId(rs.getInt("transaction_id"));
                    transaction.setAccountId(rs.getInt("account_id"));
                    transaction.setTransactionType(rs.getString("type"));
                    transaction.setAmount(rs.getDouble("amount"));
                    transaction.setTransactionDate(rs.getTimestamp("timestamp"));
                    transactions.add(transaction);
                }
            }
        }
        return transactions;
    }
    // Insert deposit transaction
    public void insertDepositTransaction(DepositTransaction transaction) throws SQLException {
        String sql = "INSERT INTO Transaction (account_id, transaction_type, amount, timestamp) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, transaction.getAccountId());
            stmt.setString(2, transaction.getTransactionType());
            stmt.setDouble(3, transaction.getAmount());
            stmt.setTimestamp(4, transaction.getTransactionDate());
            stmt.executeUpdate();
        }
    }

    // Insert withdrawal transaction
    public void insertWithdrawalTransaction(WithdrawalTransaction transaction) throws SQLException {
        String sql = "INSERT INTO Transaction (account_id, transaction_type, amount, timestamp) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, transaction.getAccountId());
            stmt.setString(2, transaction.getTransactionType());
            stmt.setDouble(3, transaction.getAmount());
            stmt.setTimestamp(4, transaction.getTransactionDate());
            stmt.executeUpdate();
        }
    }

    // Other transaction methods
}
