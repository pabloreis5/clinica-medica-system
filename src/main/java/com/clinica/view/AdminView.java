package com.clinica.view;

import javax.swing.*;
import java.awt.*;

public class AdminView extends BaseView {
    private final JPanel contentPanel;

    public AdminView(String username) {
        setTitle("Admin - Clínica Médica");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(44, 62, 80));
        topPanel.setPreferredSize(new Dimension(800, 50));
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel userLabel = new JLabel("Administrador: " + username);
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(userLabel);

        JPanel sideMenu = new JPanel();
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setPreferredSize(new Dimension(200, 500));
        sideMenu.setBackground(new Color(52, 73, 94));


        JButton btnCadastrarUsuario = criarBotao("Cadastrar Usuário");
        JButton btnExcluirUsuario = criarBotao("Excluir Usuário");
        JButton btnPesquisarUsuario = criarBotao("Pesquisar Usuário");
        JButton btnSair = criarBotaoSair();


        sideMenu.add(Box.createVerticalStrut(50));
        sideMenu.add(btnCadastrarUsuario);
        sideMenu.add(Box.createVerticalStrut(20));
        sideMenu.add(btnExcluirUsuario);
        sideMenu.add(Box.createVerticalStrut(20));
        sideMenu.add(btnPesquisarUsuario);
        sideMenu.add(Box.createVerticalStrut(50));
        sideMenu.add(btnSair);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        add(topPanel, BorderLayout.NORTH);
        add(sideMenu, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);


        btnCadastrarUsuario.addActionListener(e -> trocarConteudo(new CadastrarUsuarioPanel()));
        btnExcluirUsuario.addActionListener(e -> trocarConteudo(new ExcluirUsuarioPanel()));
        btnPesquisarUsuario.addActionListener(e -> trocarConteudo(new PesquisarUsuarioPanel()));

        trocarConteudo(new CadastrarUsuarioPanel());
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

    private void trocarConteudo(JPanel novoPainel) {
        contentPanel.removeAll();
        contentPanel.add(novoPainel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
