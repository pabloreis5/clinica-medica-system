package com.clinica.controller;

import com.clinica.model.DatabaseSetup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConsultaController {
    public static boolean agendarConsulta(String data, String hora, String medico, String paciente, String motivo) {
        String sql = "INSERT INTO consultas (data, hora, medico, paciente, motivo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseSetup.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, data);
            pstmt.setString(2, hora);
            pstmt.setString(3, medico);
            pstmt.setString(4, paciente);
            pstmt.setString(5, motivo);

            pstmt.executeUpdate();
            return true; // Consulta agendada com sucesso

        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                System.out.println("Erro: Médico já tem uma consulta nesse horário.");
            } else {
                e.printStackTrace();
            }
            return false; // Falha ao agendar consulta
        }
    }
}
