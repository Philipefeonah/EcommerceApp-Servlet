package com.maxiflexy.dao;

import com.maxiflexy.model.Cart;
import com.maxiflexy.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/EcommerceDB";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "password";
    private Connection connection;
    private String query;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ProductDAO(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure MySQL JDBC driver is loaded
            this.connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean addProduct(Product product) {
        try {

            query = "INSERT INTO products (name, category, price, image) VALUES (?, ?, ?, ?)";
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getCategory());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setString(4, product.getImage());
            int rowCount = preparedStatement.executeUpdate();
            return rowCount > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {

            //query = "select * from products";
            preparedStatement = connection.prepareStatement("select * from products");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product row = new Product();
                row.setId(resultSet.getInt("id"));
                row.setName(resultSet.getString("name"));
                row.setCategory(resultSet.getString("category"));
                row.setPrice(resultSet.getDouble("price"));
                row.setImage(resultSet.getString("image"));

                products.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return products;
    }


    public Product getSingleProduct(int id) {
        Product row = null;
        try {
            query = "select * from products where id=? ";

            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                row = new Product();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setCategory(rs.getString("category"));
                row.setPrice(rs.getDouble("price"));
                row.setImage(rs.getString("image"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return row;
    }

    public double getTotalCartPrice(ArrayList<Cart> cartList) {
        double sum = 0;
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    query = "select price from products where id=?";
                    preparedStatement = this.connection.prepareStatement(query);
                    preparedStatement.setInt(1, item.getId());
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        sum+= resultSet.getDouble("price")*item.getQuantity();
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return sum;
    }


    public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
        List<Cart> book = new ArrayList<>();
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    query = "select * from products where id=?";
                    preparedStatement = this.connection.prepareStatement(query);
                    preparedStatement.setInt(1, item.getId());
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Cart row = new Cart();
                        row.setId(resultSet.getInt("id"));
                        row.setName(resultSet.getString("name"));
                        row.setCategory(resultSet.getString("category"));
                        row.setPrice(resultSet.getDouble("price")*item.getQuantity());
                        row.setQuantity(item.getQuantity());
                        book.add(row);
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }


    public boolean deleteProduct(int productId) {
        boolean success = false;
        try {
            String query = "DELETE FROM products WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean likeProduct(String userId, String productId) {

        try {
            String query = "INSERT INTO user_likes (user_id, product_id) VALUES (?, ?)";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, userId);
            prepareStatement.setString(2, productId);
            return prepareStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveProductForLater(String userId, String productId) {

        try {
            String query = "INSERT INTO user_saves (user_id, product_id) VALUES (?, ?)";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, userId);
            prepareStatement.setString(2, productId);
            return prepareStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
