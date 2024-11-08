package Dao;

import java.sql.*;
import model.Customer;
import model.Account;

public class CustomerDAO {
    private final AccountDAO accountDAO = new AccountDAO();

    public void insertCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customer (customer_name, customer_address, customer_phone) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, customer.getCustomerName());
            stmt.setString(2, customer.getCustomerAddress());
            stmt.setString(3, customer.getCustomerPhone());
            stmt.executeUpdate();
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                customer.setCustomerId(generatedKeys.getInt(1));  // Get the generated customerId
            }

            // Create an account for the new customer
            Account account = new Account();
            account.setCustomerId(customer.getCustomerId());
            account.setbalance(0); // Default balance
            account.setaccountType("Checking"); // Default account type (you can customize this)
            accountDAO.insertAccount(account);  // Insert the new account
        }
    }

    // Other methods like getAllCustomers, etc.
}
