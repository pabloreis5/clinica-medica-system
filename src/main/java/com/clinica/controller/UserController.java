package com.clinica.controller;

import com.clinica.model.DatabaseSetup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    private static final String TOKEN_ADMIN = "000";

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

        if (!"admin".equalsIgnoreCase(role) && !token.equals(TOKEN_ADMIN)) {
            System.out.println("Erro: Token inválido!");
            return false;
        }

        if (userExists(username)) {
            System.out.println("Erro: Usuário já cadastrado!");
            return false;
        }

        String sql;
        boolean isMedico = "medico".equalsIgnoreCase(role);

        if (isMedico) {
            sql = "INSERT INTO users (username, password, role, crm, especialidade) VALUES (?, ?, ?, ?, ?)";
        } else {
            sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        }

        try (Connection conn = DatabaseSetup.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);

            if (isMedico) {
                pstmt.setString(4, crm);
                pstmt.setString(5, especialidade);
            }

            pstmt.executeUpdate();
            return true; // Cadastro bem-sucedido!

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean userExists(String username) {
        String sql = "SELECT username FROM users WHERE username = ?";

        try (Connection conn = DatabaseSetup.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // 🔹 Retorna `true` se encontrar o usuário
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<String[]> getAllUsers() {
        List<String[]> users = new ArrayList<>();
        String sql = "SELECT username, password, role FROM users";

        try (Connection conn = DatabaseSetup.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                users.add(new String[]{rs.getString("username"), rs.getString("password"), rs.getString("role")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static boolean deleteUser(String username, String token) {
        if (!token.equals(TOKEN_ADMIN)) {
            System.out.println("Erro: Token inválido!");
            return false; // Token inválido
        }

        String sql = "DELETE FROM users WHERE username = ?";

        try (Connection conn = DatabaseSetup.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Lista todos os usuários do banco de dados
    public static List<String[]> getAllUsers() {
        List<String[]> users = new ArrayList<>();
        String sql = "SELECT username, password, role FROM users";

        try (Connection conn = DatabaseSetup.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                users.add(new String[]{rs.getString("username"), rs.getString("password"), rs.getString("role")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // 🔹 Exclui um usuário se o token for válido
    public static boolean deleteUser(String username, String token) {
        if (!token.equals(TOKEN_ADMIN)) {
            return false; // Token inválido
        }

        String sql = "DELETE FROM users WHERE username = ?";

        try (Connection conn = DatabaseSetup.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0; // Retorna true se o usuário foi excluído
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }







}
