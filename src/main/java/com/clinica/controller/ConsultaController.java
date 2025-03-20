package com.clinica.controller;

import com.clinica.model.DatabaseSetup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaController {
    public static boolean agendarConsulta(String data, String hora, String medico, String paciente, String motivo) {
        // 🔹 Verifica se já existe uma consulta para o médico na mesma data e hora
        String checkSql = "SELECT COUNT(*) FROM consultas WHERE medico = ? AND data = ? AND hora = ?";

        try (Connection conn = DatabaseSetup.connect();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setString(1, medico);
            checkStmt.setString(2, data);
            checkStmt.setString(3, hora);

            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Erro: Médico já tem uma consulta nesse horário.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        // 🔹 Insere a nova consulta se não houver conflito
        String sql = "INSERT INTO consultas (data, hora, medico, paciente, motivo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseSetup.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, data);
            pstmt.setString(2, hora);
            pstmt.setString(3, medico);
            pstmt.setString(4, paciente);
            pstmt.setString(5, motivo);

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

