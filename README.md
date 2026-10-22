# Expense Tracker (Java Servlets + JSP + Maven)

A simple web app for recording and managing personal expenses. Built with
Servlets, JSP/JSTL, and an embedded H2 database, packaged with Maven as a WAR file.

## Features

- Add an expense (description, category, amount, date)
- View all expenses in a table, with a running total
- Edit an existing expense
- Delete an expense
- Data persists in a local H2 database file (no external DB server needed)

## Tech Stack

- Java 11
- Maven (build tool)
- Servlets + JSP + JSTL (`javax.servlet`, so it targets **Tomcat 9.x / 8.5.x**)
- H2 embedded database (file-based)

## Project Structure

```
expense-tracker/
├── pom.xml
└── src/main/
    ├── java/com/expensetracker/
    │   ├── model/Expense.java         # Expense POJO
    │   ├── dao/DBConnection.java      # H2 connection + table setup
    │   ├── dao/ExpenseDAO.java        # CRUD database operations
    │   └── servlet/ExpenseServlet.java# Handles list/add/edit/update/delete
    └── webapp/
        ├── index.jsp                  # Redirects to expenses list
        ├── expenses.jsp               # Main page: add form + list
        ├── edit.jsp                   # Edit form
        ├── css/style.css
        └── WEB-INF/web.xml
```

## How to Build and Run

### Prerequisites
- JDK 11+
- Maven
- Apache Tomcat 9.x (or 8.5.x)

### Steps

1. **Build the WAR file:**
   ```
   cd expense-tracker
   mvn clean package
   ```
   This produces `target/expense-tracker.war`.

2. **Deploy to Tomcat:**
   Copy `target/expense-tracker.war` into Tomcat's `webapps/` folder, then
   start Tomcat:
   ```
   cp target/expense-tracker.war $CATALINA_HOME/webapps/
   $CATALINA_HOME/bin/startup.sh
   ```

3. **Open the app:**
   ```
   http://localhost:8080/expense-tracker/
   ```

The H2 database file (`expensetrackerdb.mv.db`) is created automatically in
Tomcat's working directory the first time the app runs — no manual DB setup
required.

### Running from an IDE
If you're using IntelliJ IDEA or Eclipse with a built-in Tomcat integration,
just import this folder as a Maven project and add a Tomcat run
configuration pointing at the WAR/exploded artifact — no extra steps needed.

## Ideas for Extending This Project

- Add user login so each user has their own expenses
- Filter expenses by date range or category
- Add a monthly summary chart (e.g. with Chart.js)
- Export expenses to CSV
- Add input validation and error messages on the JSP forms
