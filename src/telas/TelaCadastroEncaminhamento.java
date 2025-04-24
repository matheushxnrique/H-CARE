package telas;

import dao.EncaminhamentoDAO;
import dao.PacienteDAO;
import dao.EspecialidadeDAO;
import model.Paciente;
import model.Especialidade;
import model.Encaminhamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.List;

public class TelaCadastroEncaminhamento extends JFrame {
    private JComboBox<Paciente> comboPacientes;
    private JComboBox<Especialidade> comboEspecialidades;
    private JToggleButton btnConsulta;
    private JToggleButton btnExame;
    private JTextArea campoDescricao;

    public TelaCadastroEncaminhamento() {
        setTitle("Cadastro de Encaminhamento");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

      
        JPanel menuLateral = new JPanel();
        menuLateral.setBackground(new Color(173, 194, 215));
        menuLateral.setPreferredSize(new Dimension(200, getHeight()));
        menuLateral.setLayout(new BoxLayout(menuLateral, BoxLayout.Y_AXIS));

        JLabel lblLogo = new JLabel("H-CARE");
        lblLogo.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblLogo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuLateral.add(lblLogo);

        add(menuLateral, BorderLayout.WEST);

       
        JPanel painelPrincipal = new JPanel(null);
        painelPrincipal.setBackground(new Color(224, 231, 244));

        JLabel lblTitulo = new JLabel("Cadastro de Encaminhamento");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setBounds(340, 10, 500, 30);
        painelPrincipal.add(lblTitulo);

        comboPacientes = new JComboBox<>();
        comboPacientes.setBounds(280, 60, 250, 40);
        carregarPacientes();
        painelPrincipal.add(comboPacientes);

        comboEspecialidades = new JComboBox<>();
        comboEspecialidades.setBounds(550, 60, 250, 40);
        carregarEspecialidades();
        painelPrincipal.add(comboEspecialidades);

        JLabel lblTipo = new JLabel("Selecione o tipo de encaminhamento");
        lblTipo.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTipo.setBounds(280, 110, 300, 30);
        painelPrincipal.add(lblTipo);

        btnConsulta = new JToggleButton("Consulta");
        btnExame = new JToggleButton("Exame");

        ButtonGroup grupoTipo = new ButtonGroup();
        grupoTipo.add(btnConsulta);
        grupoTipo.add(btnExame);

        btnConsulta.setBounds(280, 150, 200, 40);
        btnExame.setBounds(500, 150, 200, 40);
        painelPrincipal.add(btnConsulta);
        painelPrincipal.add(btnExame);

        campoDescricao = new JTextArea("Descrição");
        campoDescricao.setForeground(Color.GRAY);
        campoDescricao.setBounds(280, 210, 470, 100);
        campoDescricao.setLineWrap(true);
        campoDescricao.setWrapStyleWord(true);
        painelPrincipal.add(campoDescricao);

        campoDescricao.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campoDescricao.getText().equals("Descrição")) {
                    campoDescricao.setText("");
                    campoDescricao.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campoDescricao.getText().isEmpty()) {
                    campoDescricao.setText("Descrição");
                    campoDescricao.setForeground(Color.GRAY);
                }
            }
        });

        JButton btnEnviar = new JButton("Enviar para a lista de espera");
        btnEnviar.setBounds(280, 340, 250, 40);
        btnEnviar.setBackground(new Color(193, 211, 233));
        btnEnviar.setFont(new Font("SansSerif", Font.BOLD, 14));
        painelPrincipal.add(btnEnviar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(540, 340, 210, 40);
        btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 14));
        painelPrincipal.add(btnCancelar);

        btnEnviar.addActionListener(e -> cadastrarEncaminhamento());

        btnCancelar.addActionListener(e -> {
            new TelaListaEspera().setVisible(true);
            dispose();
        });

        add(painelPrincipal, BorderLayout.CENTER);
    }

    private void carregarPacientes() {
        PacienteDAO pacienteDAO = new PacienteDAO();
        List<Paciente> pacientes = pacienteDAO.listarTodos();
        for (Paciente p : pacientes) {
            comboPacientes.addItem(p);
        }
    }

    private void carregarEspecialidades() {
        EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
        List<Especialidade> especialidades = especialidadeDAO.listar();
        for (Especialidade esp : especialidades) {
            comboEspecialidades.addItem(esp);
        }
    }

    private void cadastrarEncaminhamento() {
        Paciente paciente = (Paciente) comboPacientes.getSelectedItem();
        Especialidade especialidade = (Especialidade) comboEspecialidades.getSelectedItem();
        String tipo = btnConsulta.isSelected() ? "Consulta" : btnExame.isSelected() ? "Exame" : "";
        String descricao = campoDescricao.getText().equals("Descrição") ? "" : campoDescricao.getText();

        if (paciente == null || especialidade == null || tipo.isEmpty() || descricao.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Encaminhamento enc;
        enc = new Encaminhamento(
                0,
                paciente.getId(),
                especialidade.getId(),
                tipo,
                descricao,
                new Date(System.currentTimeMillis())
        );

        EncaminhamentoDAO dao = new EncaminhamentoDAO();
        if (dao.inserir(enc)) {
            JOptionPane.showMessageDialog(this, "Encaminhamento cadastrado com sucesso!");
            new TelaListaEspera().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    
}
