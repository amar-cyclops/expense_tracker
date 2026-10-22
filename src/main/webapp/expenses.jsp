<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Expense Tracker</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="container">
    <h1>Expense Tracker</h1>

    <div class="card">
        <h2>Add Expense</h2>
        <form action="expenses" method="post" class="expense-form">
            <label>Description
                <input type="text" name="description" required>
            </label>
            <label>Category
                <select name="category" required>
                    <option value="Food">Food</option>
                    <option value="Transport">Transport</option>
                    <option value="Housing">Housing</option>
                    <option value="Utilities">Utilities</option>
                    <option value="Entertainment">Entertainment</option>
                    <option value="Health">Health</option>
                    <option value="Other">Other</option>
                </select>
            </label>
            <label>Amount
                <input type="number" name="amount" step="0.01" min="0" required>
            </label>
            <label>Date
                <input type="date" name="date" required>
            </label>
            <button type="submit">Add Expense</button>
        </form>
    </div>

    <div class="card">
        <div class="summary">
            <h2>All Expenses</h2>
            <div class="total">
                Total: <fmt:formatNumber value="${total}" type="currency" currencySymbol="$"/>
            </div>
        </div>

        <c:choose>
            <c:when test="${empty expenses}">
                <p class="empty">No expenses recorded yet. Add one above to get started.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Description</th>
                            <th>Category</th>
                            <th>Amount</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="expense" items="${expenses}">
                            <tr>
                                <td>${expense.date}</td>
                                <td>${expense.description}</td>
                                <td><span class="badge">${expense.category}</span></td>
                                <td><fmt:formatNumber value="${expense.amount}" type="currency" currencySymbol="$"/></td>
                                <td class="actions">
                                    <a href="expenses?action=edit&id=${expense.id}" class="btn-edit">Edit</a>
                                    <a href="expenses?action=delete&id=${expense.id}"
                                       class="btn-delete"
                                       onclick="return confirm('Delete this expense?');">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</div>

</body>
</html>
