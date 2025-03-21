package com.clinica.view;

import com.clinica.controller.ConsultaController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MedicoPanel extends JPanel {

    public MedicoPanel(String opcao, String username) {
        setLayout(new BorderLayout());

        switch (opcao) {
            case "consultas":
                add(criarPainelConsultas(username), BorderLayout.CENTER);
                break;
            case "prontuario":
                add(criarPainelProntuario(username), BorderLayout.CENTER);
                break;
        }
    }

    private JPanel criarPainelConsultas(String medico) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("Consultas atribuídas ao médico: " + medico);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titulo, BorderLayout.NORTH);

        String[] colunas = {"Data", "Hora", "Paciente", "Motivo"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(model);
        JScrollPane scroll = new JScrollPane(tabela);

        List<String[]> consultas = ConsultaController.getConsultasPorMedico(medico);
        for (String[] c : consultas) {
            model.addRow(new Object[]{c[0], c[1], c[2], c[3]});
        }

        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    private JPanel criarPainelProntuario(String medico) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JComboBox<String> pacienteBox = new JComboBox<>(ConsultaController.getPacientesPorMedico(medico).toArray(new String[0]));
        JTextField dataField = new JTextField(10);
        JTextArea diagnosticoArea = new JTextArea(3, 20);
        JTextArea observacoesArea = new JTextArea(3, 20);
        JTextArea receitaArea = new JTextArea(3, 20);
        JButton btnSalvar = new JButton("Salvar Prontuário");

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Paciente:"), gbc);
        gbc.gridx = 1; panel.add(pacienteBox, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Data (DD/MM/AAAA):"), gbc);
        gbc.gridx = 1; panel.add(dataField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Diagnóstico:"), gbc);
        gbc.gridx = 1; panel.add(new JScrollPane(diagnosticoArea), gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Observações:"), gbc);
        gbc.gridx = 1; panel.add(new JScrollPane(observacoesArea), gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Receita:"), gbc);
        gbc.gridx = 1; panel.add(new JScrollPane(receitaArea), gbc);

        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(btnSalvar, gbc);

        btnSalvar.addActionListener(e -> {
            String paciente = (String) pacienteBox.getSelectedItem();
            String data = dataField.getText().trim();
            String diagnostico = diagnosticoArea.getText().trim();
            String observacoes = observacoesArea.getText().trim();
            String receita = receitaArea.getText().trim();

            if (paciente.isEmpty() || data.isEmpty() || diagnostico.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha os campos obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean sucesso = ConsultaController.salvarProntuario(medico, paciente, data, diagnostico, observacoes, receita);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Prontuário salvo com sucesso!");
                diagnosticoArea.setText("");
                observacoesArea.setText("");
                receitaArea.setText("");
                dataField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao salvar prontuário.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }
}
