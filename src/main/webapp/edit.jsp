<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Expense</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="container">
    <h1>Edit Expense</h1>

    <div class="card">
        <form action="expenses" method="post" class="expense-form">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${expense.id}">

            <label>Description
                <input type="text" name="description" value="${expense.description}" required>
            </label>
            <label>Category
                <select name="category" required>
                    <option value="Food" ${expense.category == 'Food' ? 'selected' : ''}>Food</option>
                    <option value="Transport" ${expense.category == 'Transport' ? 'selected' : ''}>Transport</option>
                    <option value="Housing" ${expense.category == 'Housing' ? 'selected' : ''}>Housing</option>
                    <option value="Utilities" ${expense.category == 'Utilities' ? 'selected' : ''}>Utilities</option>
                    <option value="Entertainment" ${expense.category == 'Entertainment' ? 'selected' : ''}>Entertainment</option>
                    <option value="Health" ${expense.category == 'Health' ? 'selected' : ''}>Health</option>
                    <option value="Other" ${expense.category == 'Other' ? 'selected' : ''}>Other</option>
                </select>
            </label>
            <label>Amount
                <input type="number" name="amount" step="0.01" min="0" value="${expense.amount}" required>
            </label>
            <label>Date
                <input type="date" name="date" value="${expense.date}" required>
            </label>

            <div class="form-actions">
                <button type="submit">Save Changes</button>
                <a href="expenses" class="btn-cancel">Cancel</a>
            </div>
        </form>
    </div>
</div>

</body>
</html>
