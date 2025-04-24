package telas;

import dao.PacienteDAO;
import model.Paciente;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TelaPacientes extends JFrame {

    private JPanel menuLateral, painelPrincipal;
    private JButton btnInicio, btnPacientes, btnListaEspera, btnNovoPaciente;
    private JTextField campoNome, campoCPF, campoRG, campoCartaoSUS, campoDataNascimento;
    private JLabel titulo;

    public TelaPacientes() {
        setTitle("H-CARE - Pacientes");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        Color corPrincipal = new Color(224, 232, 250);
        Color corMenu = new Color(181, 198, 235);
        Color corSelecionado = new Color(150, 170, 210);

        getContentPane().setBackground(corPrincipal);

        menuLateral = new JPanel();
        menuLateral.setLayout(null);
        menuLateral.setBackground(corMenu);
        menuLateral.setBounds(0, 0, 200, getHeight());
        getContentPane().add(menuLateral);

        JLabel tituloMenu = new JLabel("H-CARE", SwingConstants.CENTER);
        tituloMenu.setFont(new Font("Arial", Font.BOLD, 24));
        tituloMenu.setBounds(0, 20, 200, 40);
        menuLateral.add(tituloMenu);

        btnInicio = new JButton("Início");
        btnInicio.setBounds(0, 80, 200, 40);
        btnInicio.setFont(new Font("Arial", Font.PLAIN, 16));
        btnInicio.setBackground(corMenu);
        btnInicio.setOpaque(true);
        btnInicio.setBorderPainted(false);
        btnInicio.setFocusPainted(false);
        menuLateral.add(btnInicio);
        btnInicio.addActionListener(e -> {
            new TelaInicial().setVisible(true);
            dispose();
        });

        btnPacientes = new JButton("Pacientes");
        btnPacientes.setBounds(0, 130, 200, 40);
        btnPacientes.setFont(new Font("Arial", Font.PLAIN, 16));
        btnPacientes.setBackground(corSelecionado);
        btnPacientes.setOpaque(true);
        btnPacientes.setBorderPainted(false);
        btnPacientes.setFocusPainted(false);
        menuLateral.add(btnPacientes);

        btnListaEspera = new JButton("Listas de espera");
        btnListaEspera.setBounds(0, 180, 200, 40);
        btnListaEspera.setFont(new Font("Arial", Font.PLAIN, 16));
        btnListaEspera.setBackground(corMenu);
        btnListaEspera.setOpaque(true);
        btnListaEspera.setBorderPainted(false);
        btnListaEspera.setFocusPainted(false);
        menuLateral.add(btnListaEspera);
        btnListaEspera.addActionListener(e -> {
            new TelaListaEspera().setVisible(true);
            dispose();
        });

        titulo = new JLabel("Buscar Pacientes", SwingConstants.LEFT);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        getContentPane().add(titulo);

        painelPrincipal = new JPanel();
        painelPrincipal.setBackground(corPrincipal);
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        getContentPane().add(painelPrincipal);

        campoNome = criarCampoComPlaceholder("Nome");
        campoCPF = criarCampoComPlaceholder("CPF");
        campoRG = criarCampoComPlaceholder("RG");
        campoCartaoSUS = criarCampoComPlaceholder("Cartão SUS");
        campoDataNascimento = criarCampoComPlaceholder("Data de nascimento");

        getContentPane().add(campoNome);
        getContentPane().add(campoCPF);
        getContentPane().add(campoRG);
        getContentPane().add(campoCartaoSUS);
        getContentPane().add(campoDataNascimento);

        btnNovoPaciente = new JButton("+ Novo Paciente");
        btnNovoPaciente.setFont(new Font("Arial", Font.BOLD, 14));
        btnNovoPaciente.setBackground(corPrincipal);
        btnNovoPaciente.setFocusPainted(false);
        btnNovoPaciente.addActionListener(e -> {
            new TelaCadastroPaciente().setVisible(true);
            dispose();
        });
        getContentPane().add(btnNovoPaciente);

        DocumentListener listenerBusca;
        listenerBusca = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { buscarPacientes(); }
            @Override
            public void removeUpdate(DocumentEvent e) { buscarPacientes(); }
            @Override
            public void changedUpdate(DocumentEvent e) { buscarPacientes(); }
        };

        campoNome.getDocument().addDocumentListener(listenerBusca);
        campoCPF.getDocument().addDocumentListener(listenerBusca);
        campoRG.getDocument().addDocumentListener(listenerBusca);
        campoCartaoSUS.getDocument().addDocumentListener(listenerBusca);
        campoDataNascimento.getDocument().addDocumentListener(listenerBusca);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int largura = getWidth();
                int altura = getHeight();

                menuLateral.setBounds(0, 0, 200, altura);

                btnInicio.setBounds(0, 80, 200, 40);
                btnPacientes.setBounds(0, 130, 200, 40);
                btnListaEspera.setBounds(0, 180, 200, 40);

                titulo.setBounds(220, 30, 400, 40);

                campoNome.setBounds(240, 90, 150, 35);
                campoCPF.setBounds(400, 90, 150, 35);
                campoDataNascimento.setBounds(560, 90, 200, 35);
                campoRG.setBounds(770, 90, 120, 35);
                campoCartaoSUS.setBounds(240, 140, 200, 35);

                btnNovoPaciente.setBounds(largura - 240, 30, 200, 40);

                painelPrincipal.setBounds(220, 200, largura - 250, altura - 220);
            }
        });
    }

    private JTextField criarCampoComPlaceholder(String placeholder) {
        JTextField campo = new JTextField(placeholder);
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setBackground(new Color(181, 198, 235));
        campo.setForeground(Color.GRAY);

        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campo.getText().equals(placeholder)) {
                    campo.setText("");
                    campo.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setForeground(Color.GRAY);
                    campo.setText(placeholder);
                }
            }
        });

        return campo;
    }

    private void buscarPacientes() {
        String nome = campoNome.getText().equals("Nome") ? "" : campoNome.getText();
        String cpf = campoCPF.getText().equals("CPF") ? "" : campoCPF.getText();
        String rg = campoRG.getText().equals("RG") ? "" : campoRG.getText();
        String cartao = campoCartaoSUS.getText().equals("Cartão SUS") ? "" : campoCartaoSUS.getText();
        String dataNasc = campoDataNascimento.getText().equals("Data de nascimento") ? "" : campoDataNascimento.getText();

        List<Paciente> lista = new PacienteDAO().buscarPacientes(nome, cpf, rg, cartao, dataNasc);

        painelPrincipal.removeAll();
        for (Paciente p : lista) {
            JLabel label = new JLabel(p.getNome() + " - " + p.getCpf());
            label.setFont(new Font("Arial", Font.PLAIN, 16));
            painelPrincipal.add(label);
        }
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

   
  
}
