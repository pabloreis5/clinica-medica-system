package com.clinica.controller;

import com.clinica.model.DatabaseSetup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaController {

    public static boolean agendarConsulta(String data, String hora, String medico, String paciente, String motivo) {
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
    public static List<String[]> getTodasConsultas() {
        List<String[]> consultas = new ArrayList<>();
        String sql = "SELECT medico, paciente, data, hora, motivo FROM consultas ORDER BY data, hora";

        try (Connection conn = DatabaseSetup.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String[] dados = new String[5];
                dados[0] = rs.getString("medico");
                dados[1] = rs.getString("paciente");
                dados[2] = rs.getString("data");
                dados[3] = rs.getString("hora");
                dados[4] = rs.getString("motivo");
                consultas.add(dados);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consultas;
    }


    public static List<String[]> getConsultasPorMedico(String medico) {
        List<String[]> consultas = new ArrayList<>();
        String sql = "SELECT data, hora, paciente, motivo FROM consultas WHERE medico = ? ORDER BY data, hora";

        try (Connection conn = DatabaseSetup.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, medico);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                consultas.add(new String[]{
                        rs.getString("data"),
                        rs.getString("hora"),
                        rs.getString("paciente"),
                        rs.getString("motivo")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consultas;
    }

    public static List<String> getPacientesPorMedico(String medico) {
        List<String> pacientes = new ArrayList<>();
        String sql = "SELECT DISTINCT paciente FROM consultas WHERE medico = ?";

        try (Connection conn = DatabaseSetup.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, medico);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                pacientes.add(rs.getString("paciente"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pacientes;
    }

    public static boolean salvarProntuario(String medico, String paciente, String data, String diagnostico, String observacoes, String receita) {
        String sql = "INSERT INTO prontuario (medico, paciente, data, diagnostico, observacoes, receita) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseSetup.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, medico);
            pstmt.setString(2, paciente);
            pstmt.setString(3, data);
            pstmt.setString(4, diagnostico);
            pstmt.setString(5, observacoes);
            pstmt.setString(6, receita);

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean excluirConsulta(String medico, String paciente, String data, String hora) {
        String sql = "DELETE FROM consultas WHERE medico = ? AND paciente = ? AND data = ? AND hora = ?";
        try (Connection conn = DatabaseSetup.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, medico);
            stmt.setString(2, paciente);
            stmt.setString(3, data);
            stmt.setString(4, hora);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
