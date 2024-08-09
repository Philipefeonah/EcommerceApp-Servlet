package com.maxiflexy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.maxiflexy.model.Admin;
import com.maxiflexy.utility.ConnectionUtil;


public class AdminDAO {

    private Connection connection;

    public AdminDAO(Connection connection){
        this.connection = connection;
    }

    public void addUser(Admin admin) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String sql = "INSERT INTO admin (fullName, email, password, adminToken) VALUES (?, ?, ?, ?)";

        try {
            connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, admin.getFullName());
            preparedStatement.setString(2, admin.getEmail());
            preparedStatement.setString(3, admin.getPassword());
            preparedStatement.setString(4, admin.getAdminToken());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (SQLException exception){
            throw new RuntimeException();
        }
        connection.close();

    }

//    private static Connection connection;
//
//    public AdminDAO(Connection connection) throws SQLException {
//        this.connection = ConnectionUtil.getConnection();
//    }
//
//    public void addAdmin(Admin admin) {
//        try {
//            // Check if the email already exists before adding a new admin
//            if (exists(admin.getEmail())) {
//                throw new RuntimeException("signup-error.jsp");
//            }
//
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "INSERT INTO newAdmin (fullName, email, password, adminToken) VALUES (?, ?, ?, ?)");
//            preparedStatement.setString(1, admin.getFullName());
//            preparedStatement.setString(2, admin.getEmail());
//            preparedStatement.setString(3, admin.getPassword());
//            preparedStatement.setString(4, admin.getAdminToken());
//
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static boolean exists(String email) {
//        try {
//
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM newAdmin WHERE email = ?");
//            preparedStatement.setString(1, email);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return resultSet.getInt(1) > 0;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return false;
//    }

//    public static boolean authenticate(String email, String password) {
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM newAdmin WHERE email = ? AND password = ?");
//            preparedStatement.setString(1, email);
//            preparedStatement.setString(2, password);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            return resultSet.next(); // Return true if a row is found, indicating authentication success
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}