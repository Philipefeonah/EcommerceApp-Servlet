package com.maxiflexy.servlet;

import com.maxiflexy.dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/saveProduct")
public class SaveProductForLater extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("userId") != null) {
            String userId = session.getAttribute("userId").toString();
            String productId = req.getParameter("productId");

            ProductDAO productDAO = new ProductDAO();
            boolean saved = productDAO.saveProductForLater(userId, productId);
            if (saved) {
                req.setAttribute("message", "Product saved for later.");
                //resp.sendRedirect("product-page.jsp");
            } else {
                req.setAttribute("error", "Failed to save the product.");
                //resp.getWriter().println("Failed to save the product.");
            }
            req.getRequestDispatcher("product_page.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("product_page.jsp");
        }
    }
}
