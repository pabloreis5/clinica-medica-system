package com.clinica.view;

import javax.swing.*;
import java.awt.*;

public class AdminView extends JFrame {
    private JPanel contentPanel;

    public AdminView(String username) {
        setTitle("Admin - Clínica Médica");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 🔹 Painel Superior (Nome do Admin)
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(44, 62, 80));
        topPanel.setPreferredSize(new Dimension(800, 50));
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel userLabel = new JLabel("Administrador: " + username);
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(userLabel);

        // 🔹 Menu Lateral com Botões Estilizados
        JPanel sideMenu = new JPanel();
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setPreferredSize(new Dimension(200, 500));
        sideMenu.setBackground(new Color(52, 73, 94));

        JButton btnCadastrarUsuario = criarBotao("Cadastrar Usuário");
        JButton btnExcluirUsuario = criarBotao("Excluir Usuário");
        JButton btnPesquisarUsuario = criarBotao("Pesquisar Usuário");

        sideMenu.add(Box.createVerticalStrut(50)); // 🔹 Espaçamento no topo
        sideMenu.add(btnCadastrarUsuario);
        sideMenu.add(Box.createVerticalStrut(20)); // 🔹 Espaçamento entre botões
        sideMenu.add(btnExcluirUsuario);
        sideMenu.add(Box.createVerticalStrut(20));
        sideMenu.add(btnPesquisarUsuario);

        // 🔹 Painel Central (Conteúdo dinâmico)
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout()); // Mantém melhor alinhamento
        contentPanel.setBackground(Color.WHITE);

        // 🔹 Adicionando Componentes ao Frame
        add(topPanel, BorderLayout.NORTH);
        add(sideMenu, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        // 🔹 Eventos dos Botões para Trocar o Conteúdo Central
        btnCadastrarUsuario.addActionListener(e -> trocarConteudo(new CadastrarUsuarioPanel()));
        btnExcluirUsuario.addActionListener(e -> trocarConteudo(new ExcluirUsuarioPanel()));
        btnPesquisarUsuario.addActionListener(e -> trocarConteudo(new PesquisarUsuarioPanel()));

        // 🔹 Define a tela inicial como "Cadastrar Usuário"
        trocarConteudo(new CadastrarUsuarioPanel());
    }

    /**
     * Cria botões estilizados e modernos.
     */
    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setForeground(Color.WHITE);
        botao.setBackground(new Color(41, 128, 185));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setMaximumSize(new Dimension(180, 40));
        botao.setAlignmentX(Component.CENTER_ALIGNMENT); // 🔹 Mantém os botões centralizados
        return botao;
    }

    /**
     * Troca o painel central pelo conteúdo desejado.
     */
    private void trocarConteudo(JPanel novoPainel) {
        contentPanel.removeAll();
        contentPanel.add(novoPainel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
