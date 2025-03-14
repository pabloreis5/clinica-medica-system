package com.clinica.view;

import com.clinica.controller.UserController;
import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    public LoginView() {
        setTitle("Clínica Médica - Login");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(44, 62, 80));

        JLabel titleLabel = new JLabel("Acesso ao Sistema");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(100, 40, 250, 30);
        panel.add(titleLabel);

        JTextField userField = new JTextField();
        userField.setBounds(100, 120, 200, 35);
        userField.setFont(new Font("Arial", Font.PLAIN, 16));
        userField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.add(userField);

        JLabel userLabel = new JLabel("Usuário:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(100, 100, 200, 20);
        panel.add(userLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(100, 190, 200, 35);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.add(passwordField);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(100, 170, 200, 20);
        panel.add(passwordLabel);

        JButton loginButton = new JButton("Entrar");
        loginButton.setBounds(100, 280, 200, 40);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(52, 152, 219));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(loginButton);

        // 🔹 Ação do Botão de Login
        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passwordField.getPassword());
            String role = UserController.authenticateUser(username, password);

            if (role == null) {
                JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Bem-vindo " + username + "!");
                dispose(); // Fecha a tela de login

                if ("admin".equalsIgnoreCase(role)) {
                    new AdminView(username).setVisible(true);
                } else if ("recepcionista".equalsIgnoreCase(role)) {
                    new RecepcionistaView(username).setVisible(true);
                } else if ("medico".equalsIgnoreCase(role)) {
                    new MedicoView(username).setVisible(true);
                }
            }
        });

        getContentPane().add(panel);
    }
}
