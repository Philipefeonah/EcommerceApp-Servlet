package com.maxiflexy.servlet;

import com.maxiflexy.dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteProduct")
public class DeleteProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the ID of the product to be deleted from the request parameters
        int productId = Integer.parseInt(request.getParameter("id"));

        ProductDAO productDAO = new ProductDAO();
        boolean deleted = productDAO.deleteProduct(productId);

        if (deleted) {
            response.sendRedirect("adminDashboard"); // Redirect to the admin dashboard
        } else {
            // If deletion fails, you can handle it accordingly
            response.getWriter().println("Failed to delete product.");
        }
    }
}
