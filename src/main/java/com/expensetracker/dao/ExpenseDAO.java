package com.expensetracker.dao;

import com.expensetracker.model.Expense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {

    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        String sql = "SELECT * FROM expenses ORDER BY expense_date DESC, id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                expenses.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching expenses", e);
        }
        return expenses;
    }

    public Expense getExpenseById(int id) {
        String sql = "SELECT * FROM expenses WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching expense with id " + id, e);
        }
        return null;
    }

    public void addExpense(Expense expense) {
        String sql = "INSERT INTO expenses (description, category, amount, expense_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, expense.getDescription());
            ps.setString(2, expense.getCategory());
            ps.setDouble(3, expense.getAmount());
            ps.setString(4, expense.getDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding expense", e);
        }
    }

    public void updateExpense(Expense expense) {
        String sql = "UPDATE expenses SET description = ?, category = ?, amount = ?, expense_date = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, expense.getDescription());
            ps.setString(2, expense.getCategory());
            ps.setDouble(3, expense.getAmount());
            ps.setString(4, expense.getDate());
            ps.setInt(5, expense.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating expense with id " + expense.getId(), e);
        }
    }

    public void deleteExpense(int id) {
        String sql = "DELETE FROM expenses WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting expense with id " + id, e);
        }
    }

    public double getTotalExpenses() {
        String sql = "SELECT COALESCE(SUM(amount), 0) AS total FROM expenses";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error calculating total expenses", e);
        }
        return 0;
    }

    private Expense mapRow(ResultSet rs) throws SQLException {
        return new Expense(
            rs.getInt("id"),
            rs.getString("description"),
            rs.getString("category"),
            rs.getDouble("amount"),
            rs.getDate("expense_date").toString()
        );
    }
}
