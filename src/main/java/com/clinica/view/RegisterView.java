package com.clinica.view;

import com.clinica.controller.UserController;

import javax.swing.*;
import java.awt.*;

public class RegisterView extends JFrame {
    public RegisterView() {
        setTitle("Cadastro de Usuário");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(44, 62, 80));

        JLabel titleLabel = new JLabel("Cadastro");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(140, 30, 200, 30);
        panel.add(titleLabel);

        JTextField userField = new JTextField();
        userField.setBounds(100, 100, 200, 35);
        panel.add(userField);

        JLabel userLabel = new JLabel("Usuário:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(100, 80, 200, 20);
        panel.add(userLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(100, 170, 200, 35);
        panel.add(passwordField);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(100, 150, 200, 20);
        panel.add(passwordLabel);

        String[] roles = {"admin", "recepcionista", "medico"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        roleComboBox.setBounds(100, 240, 200, 35);
        panel.add(roleComboBox);

        JLabel roleLabel = new JLabel("Tipo de Usuário:");
        roleLabel.setForeground(Color.WHITE);
        roleLabel.setBounds(100, 220, 200, 20);
        panel.add(roleLabel);

        JButton registerButton = new JButton("Cadastrar");
        registerButton.setBounds(100, 320, 200, 40);
        registerButton.setBackground(new Color(46, 204, 113));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBorder(BorderFactory.createEmptyBorder());
        registerButton.setFocusPainted(false);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(registerButton);

        registerButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passwordField.getPassword());
            String role = (String) roleComboBox.getSelectedItem();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = UserController.registerUser(username, password, role);
            if (success) {
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar! Usuário já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        getContentPane().add(panel);
    }
}
