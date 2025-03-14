package com.clinica.controller;

import com.clinica.model.DatabaseSetup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
    private static final String TOKEN_ADMIN = "000"; //

    public static String authenticateUser(String username, String password) {
        String sql = "SELECT role FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseSetup.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean registerUser(String username, String password, String role, String crm, String especialidade, String token) {

        if (userExists(username)) {
            return false;
        }


        if (!"admin".equalsIgnoreCase(role) && !token.equals(TOKEN_ADMIN)) {
            return false;
        }


        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseSetup.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return false;
        }


        if ("medico".equalsIgnoreCase(role)) {
            String sqlMedico = "INSERT INTO medicos (username, crm, especialidade) VALUES (?, ?, ?)";
            try (Connection conn = DatabaseSetup.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sqlMedico)) {
                pstmt.setString(1, username);
                pstmt.setString(2, crm);
                pstmt.setString(3, especialidade);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                return false;
            }
        }

        return true; // Cadastro bem-sucedido
    }

    public static boolean userExists(String username) {
        String sql = "SELECT username FROM users WHERE username = ?";

        try (Connection conn = DatabaseSetup.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Retorna `true` se encontrar o username
        } catch (SQLException e) {
            return false;
        }
    }
}
