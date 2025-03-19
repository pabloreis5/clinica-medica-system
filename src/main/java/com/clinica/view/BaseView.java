package com.clinica.view;

import javax.swing.*;
import java.awt.*;

public abstract class BaseView extends JFrame {

    protected void sairDoSistema() {
        int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose(); // Fecha a janela atual
            new LoginView().setVisible(true); // Retorna para a tela de login
        }
    }

    protected JButton criarBotaoSair() {
        JButton botao = new JButton("Sair");
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setForeground(Color.WHITE);
        botao.setBackground(new Color(231, 76, 60)); // 🔹 Vermelho chamativo
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setMaximumSize(new Dimension(180, 40));
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.addActionListener(e -> sairDoSistema());
        return botao;
    }
}
