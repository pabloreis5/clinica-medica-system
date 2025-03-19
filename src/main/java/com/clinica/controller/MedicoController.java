package com.clinica.controller;

import com.clinica.model.DatabaseSetup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MedicoController {
    public static List<String> getListaMedicos() {
        List<String> medicos = new ArrayList<>();
        String sql = "SELECT username FROM users WHERE role = 'medico'";

        try (Connection conn = DatabaseSetup.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                medicos.add(rs.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return medicos;
    }
}
