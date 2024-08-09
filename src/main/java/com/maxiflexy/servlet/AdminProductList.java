package com.maxiflexy.servlet;

import com.maxiflexy.dao.ProductDAO;
import com.maxiflexy.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/adminDashboard")
public class AdminProductList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        HttpSession session = req.getSession(false);
//        if (session == null || session.getAttribute("email") == null) {
//            resp.sendRedirect("adminLogin.jsp");
//            return;
//        }

        // Create an instance of your DAO class
        ProductDAO productDAO = new ProductDAO();

        // Get the product list
        List<Product> productList = productDAO.getAllProducts();

        // Set the product list as a request attribute
        req.setAttribute("products", productList);

        // Forward the request to the JSP page
        req.getRequestDispatcher("adminDashboard.jsp").forward(req, resp);

    }

}
