package com.clinica.controller;

import com.clinica.model.DatabaseSetup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {

    public static String authenticateUser(String username, String password) {
        String sql = "SELECT role FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseSetup.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("role"); // Retorna o papel do usuário
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null se a autenticação falhar
    }

    public static boolean registerUser(String username, String password, String role) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseSetup.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }








}
