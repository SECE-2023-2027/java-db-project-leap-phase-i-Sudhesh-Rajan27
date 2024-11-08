package Dao;

import java.sql.*;
import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;

public class AccountDAO {

    // Method to insert a new account (savings or current)
    public void insertAccount(Account account) throws SQLException {
        String sql = "INSERT INTO Account (customer_id, balance, account_type, created_at) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, account.getCustomerId());
            stmt.setDouble(2, account.getbalance());
            stmt.setString(3, account.getaccountType());
            stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                account.setaccountId(generatedKeys.getInt(1));
            }
        }
    }

    // Method to update account balance
    public void updateBalance(int accountId, double amount) throws SQLException {
        String sql = "UPDATE Account SET balance = balance + ? WHERE account_id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, amount);
            stmt.setInt(2, accountId);
            stmt.executeUpdate();
        }
    }

    // Get account by ID
    public Account getAccountById(int accountId) throws SQLException {
        String sql = "SELECT * FROM Account WHERE account_id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String accountType = rs.getString("account_type");
                Account account;
                if ("Savings".equals(accountType)) {
                    account = new SavingsAccount();
                } else if ("Current".equals(accountType)) {
                    account = new CurrentAccount();
                } else {
                    account = new Account(); // Generic account type
                }
                account.setaccountId(rs.getInt("account_id"));
                account.setCustomerId(rs.getInt("customer_id"));
                account.setbalance(rs.getDouble("balance"));
                account.setaccountType(accountType);
                account.setcreatedAt(rs.getTimestamp("created_at"));
                return account;
            }
        }
        return null;
    }
}
