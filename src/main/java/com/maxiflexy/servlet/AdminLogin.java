package com.maxiflexy.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "admin-login", value = "/admin-login")
public class AdminLogin extends HttpServlet {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/EcommerceDB";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String adminToken = request.getParameter("adminToken");

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                String sql = "SELECT * FROM admin WHERE email= ? AND password=? AND adminToken=?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, email);
                    statement.setString(2, password);
                    statement.setString(3, adminToken);
                    try (ResultSet result = statement.executeQuery()) {
                        if (result.next()) {
                            HttpSession session = request.getSession();
                            session.setAttribute("Email", email);
                            //response.sendRedirect("adminDashboard.jsp");
                            response.sendRedirect("adminDashboard");
                        } else {
                            response.sendRedirect("signup-error.jsp");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("signup-error.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("signup-error.jsp");
        }

    }
}
