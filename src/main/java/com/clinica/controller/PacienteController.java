package com.clinica.controller;

import com.clinica.model.DatabaseSetup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PacienteController {
    public static boolean cadastrarPaciente(String nome, String cpf, String dataNascimento, String telefone) {
        String sql = "INSERT INTO pacientes (nome, cpf, data_nascimento, telefone) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseSetup.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, cpf);
            pstmt.setString(3, dataNascimento);
            pstmt.setString(4, telefone);
            pstmt.executeUpdate();
            return true; // Cadastro bem-sucedido
        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                return false; // CPF já cadastrado
            }
            e.printStackTrace();
            return false;
        }
    }
    public static List<String> getListaPacientes() {
        List<String> pacientes = new ArrayList<>();
        String sql = "SELECT nome FROM pacientes";

        try (Connection conn = DatabaseSetup.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                pacientes.add(rs.getString("nome"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pacientes;
    }
}
