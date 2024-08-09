package com.maxiflexy.controller;

import com.maxiflexy.dao.CustomerDAO;
import com.maxiflexy.model.Customer;
import com.maxiflexy.utility.ConnectionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "customer", value = "/signup")
public class CustomerController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email  = request.getParameter("email");
        String password= request.getParameter("password");

        //Instantiate User and UserDao class
        Customer customer = new Customer(firstName,lastName,email,password);
        CustomerDAO customerDAO = new CustomerDAO(ConnectionUtil.getConnection());

        try {
            customerDAO.addUser(customer);
        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("signup-success.jsp");

    }

}
