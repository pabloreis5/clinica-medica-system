package com.clinica.view;

import javax.swing.*;
import java.awt.*;

public class MedicoView extends BaseView {
    private String username;

    public MedicoView(String username) {
        this.username = username;

        setTitle("Médico - Clínica Médica");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 🔹 Painel Superior (Exibe o nome do usuário)
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(44, 62, 80));
        topPanel.setPreferredSize(new Dimension(800, 50));

        JLabel userLabel = new JLabel("Médico: " + username);
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(userLabel);

        add(topPanel, BorderLayout.NORTH);
    }
}
