package com.Railway.System;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {
    public static boolean login(String username, String password) {
        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful! Welcome, " + rs.getString("username"));
                return true;
            } else {
                System.out.println("Invalid username or password.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

