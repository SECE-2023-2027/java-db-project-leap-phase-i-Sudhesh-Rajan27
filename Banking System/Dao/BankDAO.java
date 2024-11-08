package Dao;

import java.sql.*;
import model.Bank;
import java.util.ArrayList;
import java.util.List;

public class BankDAO {
    public void insertBank(Bank bank) throws SQLException {
        String sql = "INSERT INTO Bank (bank_name, bank_branch) VALUES (?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, bank.getBankName());
            stmt.setString(2, bank.getBankBranch());
            stmt.executeUpdate();
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                bank.setBankId(generatedKeys.getInt(1));
            }
        }
    }

    public List<Bank> getAllBanks() throws SQLException {
        String sql = "SELECT * FROM Bank";
        List<Bank> banks = new ArrayList<>();
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             
            while (rs.next()) {
                Bank bank = new Bank();
                bank.setBankId(rs.getInt("bank_id"));
                bank.setBankName(rs.getString("bank_name"));
                bank.setBankBranch(rs.getString("bank_branch"));
                banks.add(bank);
            }
        }
        
        return banks;
    }
}
