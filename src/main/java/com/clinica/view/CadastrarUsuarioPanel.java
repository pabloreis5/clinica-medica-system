package com.clinica.view;

import com.clinica.controller.UserController;
import javax.swing.*;
import java.awt.*;

public class CadastrarUsuarioPanel extends JPanel {
    private JComboBox<String> roleComboBox;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField crmField;
    private JTextField especialidadeField;
    private JTextField tokenField;
    private JPanel extraFieldsPanel;

    public CadastrarUsuarioPanel() {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        tokenField = new JTextField(15);

        roleComboBox = new JComboBox<>(new String[]{"admin", "recepcionista", "medico"});
        roleComboBox.addActionListener(e -> atualizarCamposExtras());

        add(new JLabel("Tipo de Usuário:"), gbc);
        gbc.gridx++;
        add(roleComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Username:"), gbc);
        gbc.gridx++;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Password:"), gbc);
        gbc.gridx++;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Token de Confirmação:"), gbc);
        gbc.gridx++;
        add(tokenField, gbc);

        extraFieldsPanel = new JPanel(new GridBagLayout());
        extraFieldsPanel.setVisible(false);

        GridBagConstraints gbcExtra = new GridBagConstraints();
        gbcExtra.insets = new Insets(5, 5, 5, 5);
        gbcExtra.fill = GridBagConstraints.HORIZONTAL;
        gbcExtra.gridx = 0;
        gbcExtra.gridy = 0;

        crmField = new JTextField(15);
        especialidadeField = new JTextField(15);

        extraFieldsPanel.add(new JLabel("CRM:"), gbcExtra);
        gbcExtra.gridx++;
        extraFieldsPanel.add(crmField, gbcExtra);

        gbcExtra.gridx = 0;
        gbcExtra.gridy++;
        extraFieldsPanel.add(new JLabel("Especialidade:"), gbcExtra);
        gbcExtra.gridx++;
        extraFieldsPanel.add(especialidadeField, gbcExtra);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(extraFieldsPanel, gbc);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCadastrar.setBackground(new Color(46, 204, 113));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrar.setPreferredSize(new Dimension(150, 30));

        gbc.gridy++;
        add(btnCadastrar, gbc);

        btnCadastrar.addActionListener(e -> cadastrarUsuario());
    }

    private void atualizarCamposExtras() {
        boolean isMedico = "medico".equalsIgnoreCase((String) roleComboBox.getSelectedItem());
        extraFieldsPanel.setVisible(isMedico);
        revalidate();
        repaint();
    }

    private void limparCampos() {
        usernameField.setText("");
        passwordField.setText("");
        tokenField.setText("");
        crmField.setText("");
        especialidadeField.setText("");

        roleComboBox.setSelectedIndex(0);

        atualizarCamposExtras();
    }


    private void cadastrarUsuario() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String role = (String) roleComboBox.getSelectedItem();
        String token = tokenField.getText();
        String crm = "";
        String especialidade = "";

        if ("medico".equalsIgnoreCase(role)) {
            crm = crmField.getText();
            especialidade = especialidadeField.getText();
        }

        boolean success = UserController.registerUser(username, password, role.toLowerCase(), crm, especialidade, token);
        if (success) {
            JOptionPane.showMessageDialog(null, role + " cadastrado com sucesso!");
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Erro: Token inválido ou username já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
