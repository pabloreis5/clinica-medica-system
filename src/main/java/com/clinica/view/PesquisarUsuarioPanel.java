package com.clinica.view;

import com.clinica.controller.UserController;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PesquisarUsuarioPanel extends JPanel {
    private JTextField searchField;
    private JTable userTable;
    private DefaultTableModel tableModel;

    public PesquisarUsuarioPanel() {
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel searchLabel = new JLabel("Pesquisar Usuário:");
        searchField = new JTextField(20);

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);

        String[] columnNames = {"Username", "Password", "Role"};
        tableModel = new DefaultTableModel(columnNames, 0);
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);

        carregarUsuarios(""); // 🔹 Inicializa a tabela com todos os usuários

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                carregarUsuarios(searchField.getText());
            }
            public void removeUpdate(DocumentEvent e) {
                carregarUsuarios(searchField.getText());
            }
            public void changedUpdate(DocumentEvent e) {
                carregarUsuarios(searchField.getText());
            }
        });

        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void carregarUsuarios(String filtro) {
        List<String[]> users = UserController.getAllUsers();
        tableModel.setRowCount(0); // 🔹 Limpa a tabela antes de atualizar

        for (String[] user : users) {
            if (user[0].toLowerCase().contains(filtro.toLowerCase())) {
                tableModel.addRow(user);
            }
        }
    }
}
