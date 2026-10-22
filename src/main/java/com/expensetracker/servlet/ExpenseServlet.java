package com.expensetracker.servlet;

import com.expensetracker.dao.ExpenseDAO;
import com.expensetracker.model.Expense;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/expenses")
public class ExpenseServlet extends HttpServlet {

    private final ExpenseDAO expenseDAO = new ExpenseDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteExpense(request, response);
                break;
            default:
                listExpenses(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("update".equals(action)) {
            updateExpense(request, response);
        } else {
            addExpense(request, response);
        }
    }

    private void listExpenses(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Expense> expenses = expenseDAO.getAllExpenses();
        double total = expenseDAO.getTotalExpenses();

        request.setAttribute("expenses", expenses);
        request.setAttribute("total", total);

        RequestDispatcher dispatcher = request.getRequestDispatcher("expenses.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Expense expense = expenseDAO.getExpenseById(id);

        request.setAttribute("expense", expense);

        RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
        dispatcher.forward(request, response);
    }

    private void addExpense(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String description = request.getParameter("description");
        String category = request.getParameter("category");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String date = request.getParameter("date");

        Expense expense = new Expense(0, description, category, amount, date);
        expenseDAO.addExpense(expense);

        response.sendRedirect("expenses");
    }

    private void updateExpense(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String date = request.getParameter("date");

        Expense expense = new Expense(id, description, category, amount, date);
        expenseDAO.updateExpense(expense);

        response.sendRedirect("expenses");
    }

    private void deleteExpense(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        expenseDAO.deleteExpense(id);

        response.sendRedirect("expenses");
    }
}
