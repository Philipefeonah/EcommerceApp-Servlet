package com.maxiflexy.controller;

import com.maxiflexy.dao.AdminDAO;
import com.maxiflexy.model.Admin;
import com.maxiflexy.utility.ConnectionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "admin_signup", value = "/admin_signup")
public class AdminController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password  = request.getParameter("password");
        String adminToken= request.getParameter("adminToken");

        //Instantiate User and UserDao class
        Admin admin = new Admin(fullName,email,password,adminToken);
        AdminDAO adminDAO = null;
        try {
            adminDAO = new AdminDAO(ConnectionUtil.getConnection());
            adminDAO.addUser(admin);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("signup-success.jsp");

    }


}
