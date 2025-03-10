package com.clinica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    private static final String URL = "jdbc:sqlite:database/clinica.db";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                System.out.println("✅ Banco de dados 'clinica.db' criado com sucesso!");

                String sql = "CREATE TABLE IF NOT EXISTS users ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "username TEXT UNIQUE NOT NULL, "
                        + "password TEXT NOT NULL"
                        + ");";

                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(sql);
                    System.out.println("✅ Tabela 'users' criada com sucesso!");
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao criar o banco: " + e.getMessage());
        }

        String url = "jdbc:sqlite:database/clinica.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("✅ Conexão com SQLite bem-sucedida!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao conectar ao banco: " + e.getMessage());
        }
    }
}
