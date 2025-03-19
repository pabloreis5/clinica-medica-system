package com.clinica.view;

import javax.swing.*;
import java.awt.*;

public class RecepcionistaView extends BaseView {
    private JPanel contentPanel;

    public RecepcionistaView(String username) {
        setTitle("Recepcionista - Clínica Médica");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 🔹 Painel Superior (Nome do Recepcionista)
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(44, 62, 80));
        topPanel.setPreferredSize(new Dimension(800, 50));
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel userLabel = new JLabel("Recepcionista: " + username);
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(userLabel);

        // 🔹 Menu Lateral com Botões
        JPanel sideMenu = new JPanel();
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setPreferredSize(new Dimension(200, 500));
        sideMenu.setBackground(new Color(52, 73, 94));

        JButton btnCadastrarPaciente = criarBotao("Cadastrar Paciente");
        JButton btnAgendarConsulta = criarBotao("Agendar Consulta");
        JButton btnGerenciarConsultas = criarBotao("Gerenciar Consultas");
        JButton btnSair = criarBotaoSair(); // 🔹 Botão "Sair" vermelho

        sideMenu.add(Box.createVerticalStrut(50));
        sideMenu.add(btnCadastrarPaciente);
        sideMenu.add(Box.createVerticalStrut(20));
        sideMenu.add(btnAgendarConsulta);
        sideMenu.add(Box.createVerticalStrut(20));
        sideMenu.add(btnGerenciarConsultas);
        sideMenu.add(Box.createVerticalStrut(50));
        sideMenu.add(btnSair); // 🔹 Adiciona o botão "Sair" no final

        // 🔹 Painel Central (Conteúdo dinâmico)
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        // 🔹 Adicionando Componentes ao Frame
        add(topPanel, BorderLayout.NORTH);
        add(sideMenu, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        // 🔹 Evento dos Botões para Mudar o Conteúdo Dinamicamente
        btnCadastrarPaciente.addActionListener(e -> trocarConteudo("cadastrar"));
        btnAgendarConsulta.addActionListener(e -> trocarConteudo("agendar"));
        btnGerenciarConsultas.addActionListener(e -> trocarConteudo("gerenciar"));

        // 🔹 Define a tela inicial como "Cadastrar Paciente"
        trocarConteudo("cadastrar");
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setForeground(Color.WHITE);
        botao.setBackground(new Color(41, 128, 185));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setMaximumSize(new Dimension(180, 40));
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        return botao;
    }

    private void trocarConteudo(String opcao) {
        contentPanel.removeAll();
        contentPanel.add(new RecepcionistaPanel(opcao), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
