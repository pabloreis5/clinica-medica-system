package com.clinica.view;

import com.clinica.controller.UserController;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ExcluirUsuarioPanel extends JPanel {
    private JTable userTable;
    private DefaultTableModel tableModel;

    public ExcluirUsuarioPanel() {
        setLayout(new BorderLayout());

        String[] columnNames = {"Username", "Password", "Role", "Excluir"};
        tableModel = new DefaultTableModel(columnNames, 0);
        userTable = new JTable(tableModel);
        userTable.setRowHeight(30); // 🔹 Aumenta a altura das células para o botão ficar visível

        userTable.getColumn("Excluir").setCellRenderer(new ButtonRenderer());
        userTable.getColumn("Excluir").setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(userTable);
        carregarUsuarios(); // 🔹 Preenche a tabela com usuários

        add(scrollPane, BorderLayout.CENTER);
    }

    private void carregarUsuarios() {
        List<String[]> users = UserController.getAllUsers();
        tableModel.setRowCount(0); // 🔹 Limpa a tabela antes de atualizar

        for (String[] user : users) {
            tableModel.addRow(new Object[]{user[0], user[1], user[2], "Excluir"});
        }
    }
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setText("Excluir");
            setForeground(Color.WHITE);
            setBackground(new Color(231, 76, 60)); // 🔹 Cor vermelha
            setFont(new Font("Arial", Font.BOLD, 12));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // 🔹 Editor para capturar cliques no botão
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String username;
        private boolean isClicked;

        public ButtonEditor(JCheckBox checkBox) {

            super(checkBox);
            button = new JButton("Excluir");
            button.setOpaque(true);
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(231, 76, 60));
            button.setFont(new Font("Arial", Font.BOLD, 12));
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }


        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            username = (String) table.getValueAt(row, 0);
            isClicked = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isClicked) {
                excluirUsuario(username);
            }
            isClicked = false;
            return "Excluir";
        }
    }

    private void excluirUsuario(String username) {
        String token = JOptionPane.showInputDialog(this, "Digite o Token de Confirmação:");
        if (token == null || token.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Exclusão cancelada.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir " + username + "?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = UserController.deleteUser(username, token);
            if (success) {
                JOptionPane.showMessageDialog(this, "Usuário excluído com sucesso!");

                SwingUtilities.invokeLater(() -> {
                    carregarUsuarios(); // 🔹 Atualiza a tabela sem erros
                });

            } else {
                JOptionPane.showMessageDialog(this, "Erro: Token inválido ou usuário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
