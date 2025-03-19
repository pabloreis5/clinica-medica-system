package com.clinica.view;

import com.clinica.controller.MedicoController;
import com.clinica.controller.PacienteController;
import com.clinica.controller.ConsultaController;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.List;

public class RecepcionistaPanel extends JPanel {
    public RecepcionistaPanel(String opcao) {
        setLayout(new BorderLayout());

        switch (opcao) {
            case "cadastrar":
                add(criarPainelCadastrarPaciente(), BorderLayout.CENTER);
                break;
            case "agendar":
                add(criarPainelAgendarConsulta(), BorderLayout.CENTER);
                break;
            case "gerenciar":
                add(criarPainelGerenciarConsultas(), BorderLayout.CENTER);
                break;
        }
    }

    // 🔹 Painel de Cadastro de Paciente
    private JPanel criarPainelCadastrarPaciente() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField nomeField = new JTextField(15);
        JTextField cpfField = new JTextField(15);
        JFormattedTextField dataNascimentoField = criarCampoData();
        JTextField telefoneField = new JTextField(15);

        JButton btnSalvar = new JButton("Salvar Paciente");
        estilizarBotao(btnSalvar);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; panel.add(nomeField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("CPF:"), gbc);
        gbc.gridx = 1; panel.add(cpfField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Data de Nascimento:"), gbc);
        gbc.gridx = 1; panel.add(dataNascimentoField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Telefone:"), gbc);
        gbc.gridx = 1; panel.add(telefoneField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(btnSalvar, gbc);

        btnSalvar.addActionListener(e -> {
            String nome = nomeField.getText().trim();
            String cpf = cpfField.getText().trim();
            String dataNascimento = dataNascimentoField.getText().trim();
            String telefone = telefoneField.getText().trim();

            if (nome.isEmpty() || cpf.isEmpty() || dataNascimento.isEmpty() || telefone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean sucesso = PacienteController.cadastrarPaciente(nome, cpf, dataNascimento, telefone);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Paciente cadastrado com sucesso!");
                nomeField.setText("");
                cpfField.setText("");
                dataNascimentoField.setText("");
                telefoneField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Erro: CPF já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    // 🔹 Painel de Agendamento de Consulta
    private JPanel criarPainelAgendarConsulta() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JFormattedTextField dataConsultaField = criarCampoData();
        JFormattedTextField horaConsultaField = criarCampoHora();

        List<String> listaMedicos = MedicoController.getListaMedicos();
        List<String> listaPacientes = PacienteController.getListaPacientes();

        if (listaMedicos.isEmpty() || listaPacientes.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "⚠ Nenhum médico ou paciente cadastrado! Cadastre antes de agendar consultas.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
        }

        JComboBox<String> medicoComboBox = new JComboBox<>(listaMedicos.toArray(new String[0]));
        JComboBox<String> pacienteComboBox = new JComboBox<>(listaPacientes.toArray(new String[0]));
        JTextField motivoField = new JTextField(15);

        JButton btnAgendar = new JButton("Agendar Consulta");
        estilizarBotao(btnAgendar);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Data:"), gbc);
        gbc.gridx = 1; panel.add(dataConsultaField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Hora:"), gbc);
        gbc.gridx = 1; panel.add(horaConsultaField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Médico:"), gbc);
        gbc.gridx = 1; panel.add(medicoComboBox, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Paciente:"), gbc);
        gbc.gridx = 1; panel.add(pacienteComboBox, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Motivo:"), gbc);
        gbc.gridx = 1; panel.add(motivoField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(btnAgendar, gbc);

        gbc.gridy++;
        JLabel infoLabel = new JLabel("⚠ Para agendar, o paciente deve estar cadastrado.");
        infoLabel.setForeground(Color.RED);
        panel.add(infoLabel, gbc);

        btnAgendar.addActionListener(e -> {
            String data = dataConsultaField.getText().trim();
            String hora = horaConsultaField.getText().trim();
            String medico = (String) medicoComboBox.getSelectedItem();
            String paciente = (String) pacienteComboBox.getSelectedItem();
            String motivo = motivoField.getText().trim();

            if (data.isEmpty() || hora.isEmpty() || medico == null || paciente == null || motivo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean sucesso = ConsultaController.agendarConsulta(data, hora, medico, paciente, motivo);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Consulta agendada com sucesso!");
                dataConsultaField.setText("");
                horaConsultaField.setText("");
                motivoField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Erro: Médico já tem uma consulta nesse horário!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    // 🔹 Painel de Gerenciamento de Consultas
    private JPanel criarPainelGerenciarConsultas() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("📅 Aqui será exibida a lista de consultas agendadas."));
        return panel;
    }

    private JFormattedTextField criarCampoData() {
        try {
            MaskFormatter mask = new MaskFormatter("##/##/####");
            mask.setPlaceholderCharacter('_');
            return new JFormattedTextField(mask);
        } catch (ParseException e) {
            e.printStackTrace();
            return new JFormattedTextField();
        }
    }

    private JFormattedTextField criarCampoHora() {
        try {
            MaskFormatter mask = new MaskFormatter("##:##");
            mask.setPlaceholderCharacter('_');
            return new JFormattedTextField(mask);
        } catch (ParseException e) {
            e.printStackTrace();
            return new JFormattedTextField();
        }
    }

    private void estilizarBotao(JButton botao) {
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setForeground(Color.WHITE);
        botao.setBackground(new Color(46, 204, 113));
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setPreferredSize(new Dimension(180, 30));
    }
}
