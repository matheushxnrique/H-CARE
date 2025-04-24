package telas;

import dao.PacienteDAO;
import model.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class TelaCadastroPaciente extends JFrame {
    private JTextField campoNome, campoRG, campoCPF;
    private JFormattedTextField campoDataNascimento;

    private JTextField campoEmail, campoEndereco, campoTelefone, campoCartaoSUS;

    public TelaCadastroPaciente() {
        setTitle("Cadastro de Pacientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        Color azulClaro = new Color(220, 229, 245);
        Color azulLateral = new Color(189, 209, 233);
        Color branco = Color.WHITE;

        JPanel painelLateral = new JPanel();
        painelLateral.setBackground(azulLateral);
        painelLateral.setBounds(0, 0, 200, getHeight());
        painelLateral.setLayout(null);

        JLabel labelLogo = new JLabel("H-CARE");
        labelLogo.setFont(new Font("Arial", Font.BOLD, 24));
        labelLogo.setBounds(30, 30, 150, 30);
        painelLateral.add(labelLogo);

        add(painelLateral);
        
        getContentPane().setBackground(azulClaro);

        JPanel painelFormulario = new JPanel(null);
        painelFormulario.setBackground(branco);
        painelFormulario.setBounds(250, 100, 900, 450);
        add(painelFormulario);

        JLabel titulo = new JLabel("Cadastro de Pacientes");
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setBounds(500, 30, 400, 30);
        add(titulo);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(1200, 20, 100, 30);
        btnVoltar.setBackground(new Color(189, 209, 233));
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
      btnVoltar.addActionListener(e -> {
    new TelaPacientes().setVisible(true);
    dispose();
});

        add(btnVoltar);

        campoNome = criarCampo("Nome", 30, 30, painelFormulario);
        campoRG = criarCampo("RG", 470, 30, painelFormulario);

        campoCPF = criarCampo("CPF", 30, 90, painelFormulario);
        try {
    MaskFormatter formatter = new MaskFormatter("##/##/####");
    formatter.setPlaceholderCharacter('_');
    campoDataNascimento = new JFormattedTextField(formatter);
    campoDataNascimento.setBounds(470, 90, 350, 40);
    campoDataNascimento.setFont(new Font("Arial", Font.PLAIN, 14));
    campoDataNascimento.setBackground(new Color(230, 236, 250));
    campoDataNascimento.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    campoDataNascimento.setForeground(Color.GRAY);
    campoDataNascimento.setText("Data de nascimento (DD/MM/AAAA)");

    campoDataNascimento.addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusGained(java.awt.event.FocusEvent evt) {
            if (campoDataNascimento.getText().equals("Data de nascimento (DD/MM/AAAA)")) {
                campoDataNascimento.setText("");
                campoDataNascimento.setForeground(Color.BLACK);
            }
        }

        @Override
        public void focusLost(java.awt.event.FocusEvent evt) {
            if (campoDataNascimento.getText().replace("_", "").replace("/", "").isEmpty()) {
                campoDataNascimento.setForeground(Color.GRAY);
                campoDataNascimento.setText("Data de nascimento (DD/MM/AAAA)");
            }
        }
    });





    campoDataNascimento.addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusGained(java.awt.event.FocusEvent evt) {
            if (campoDataNascimento.getText().equals("Data de nascimento")) {
                campoDataNascimento.setText("");
                campoDataNascimento.setForeground(Color.BLACK);
            }
        }

        @Override
        public void focusLost(java.awt.event.FocusEvent evt) {
            if (campoDataNascimento.getText().replace("_", "").replace("-", "").isEmpty()) {
                campoDataNascimento.setForeground(Color.GRAY);
                campoDataNascimento.setText("Data de nascimento");
            }
        }
    });

    painelFormulario.add(campoDataNascimento);
} catch (ParseException e) {
    e.printStackTrace();
}


        campoEmail = criarCampo("E-mail", 30, 150, painelFormulario);
        campoEndereco = criarCampo("Endereço", 470, 150, painelFormulario);

        campoTelefone = criarCampo("Telefone", 30, 210, painelFormulario);
        campoCartaoSUS = criarCampo("Cartão SUS", 470, 210, painelFormulario);

        JButton btnCadastrar = new JButton("CADASTRAR");
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCadastrar.setBounds(360, 300, 180, 40);
        btnCadastrar.setBackground(new Color(189, 209, 233));
        painelFormulario.add(btnCadastrar);

        btnCadastrar.addActionListener((ActionEvent e) -> {
            salvarPaciente();
        });
    }

    private JTextField criarCampo(String placeholder, int x, int y, JPanel painel) {
        JTextField campo = new JTextField();
        campo.setBounds(x, y, 350, 40);
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setBackground(new Color(230, 236, 250));
        campo.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        campo.setText(placeholder);
        campo.setForeground(Color.GRAY);

        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (campo.getText().equals(placeholder)) {
                    campo.setText("");
                    campo.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (campo.getText().isEmpty()) {
                    campo.setForeground(Color.GRAY);
                    campo.setText(placeholder);
                }
            }
        });

        painel.add(campo);
        return campo;
    }

           private void salvarPaciente() {
    try {
        String nome = campoNome.getText();
        String rg = campoRG.getText();
        String cpf = campoCPF.getText();
        String dataNascimentoTexto = campoDataNascimento.getText();
        String email = campoEmail.getText();
        String endereco = campoEndereco.getText();
        String telefone = campoTelefone.getText();
        String cartaoSUS = campoCartaoSUS.getText();

        if (dataNascimentoTexto.equals("Data de nascimento (DD/MM/AAAA)")) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha a data de nascimento.");
            return;
        }

        DateTimeFormatter formatoUsuario = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimentoLocalDate = LocalDate.parse(dataNascimentoTexto, formatoUsuario);
        java.sql.Date dataNascimentoSQL = java.sql.Date.valueOf(dataNascimentoLocalDate);

        Paciente paciente = new Paciente(nome, rg, cpf, dataNascimentoSQL, email, endereco, telefone, cartaoSUS);
        PacienteDAO pacienteDAO = new PacienteDAO();
        pacienteDAO.inserir(paciente);

        JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!");

        // Retornar à TelaPacientes
        new TelaPacientes().setVisible(true);
        dispose();

    } catch (DateTimeParseException e) {
        JOptionPane.showMessageDialog(null, "Data inválida! Use o formato DD/MM/AAAA.");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao salvar paciente: " + e.getMessage());
    }
}

}
