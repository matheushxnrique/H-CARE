package telas;

import dao.AgendamentoDAO;
import dao.MedicoDAO;
import model.Agendamento;
import model.EncaminhamentoView;
import model.Medico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.SimpleDateFormat;
import java.sql.Time;
import java.util.List;


public class TelaAgendamento extends JFrame {

    private JPanel menuLateral;
    private JTextField campoLocal;
    private JTextField campoData;
    private JTextField campoHorario;
    private JComboBox<Medico> cbMedico;

    private JLabel lblPacienteValor;
    private JLabel lblEspecialidadeValor;

    public TelaAgendamento(EncaminhamentoView encaminhamentoInicial) {
        setTitle("Agendamento");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color corPrincipal = new Color(224, 232, 250);
        Color corMenu = new Color(181, 198, 235);
        Color corSelecionado = new Color(150, 170, 210);
        Color corCards = new Color(255, 255, 255);
   

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(corPrincipal);
        setContentPane(painelPrincipal);

        menuLateral = new JPanel();
        menuLateral.setLayout(null);
        menuLateral.setBackground(corMenu);
        painelPrincipal.add(menuLateral, BorderLayout.WEST);

        JLabel tituloMenu = new JLabel("H-CARE", SwingConstants.CENTER);
        tituloMenu.setFont(new Font("Arial", Font.BOLD, 24));
        tituloMenu.setBounds(0, 20, 200, 40);
        menuLateral.add(tituloMenu);

        JButton btnInicio = new JButton("Início");
        JButton btnPacientes = new JButton("Pacientes");
        JButton btnListaEspera = new JButton("Listas de espera");

        JButton[] botoesMenu = {btnInicio, btnPacientes, btnListaEspera};
        int y = 80;
        for (JButton btn : botoesMenu) {
            btn.setFont(new Font("Arial", Font.PLAIN, 16));
            btn.setBounds(0, y, 200, 40);
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setBackground(corMenu);
            btn.setOpaque(true);
            menuLateral.add(btn);
            y += 50;
        }

        btnListaEspera.setBackground(corSelecionado);

        btnInicio.addActionListener(e -> {
            new TelaInicial().setVisible(true);
            dispose();
        });

        btnPacientes.addActionListener(e -> {
            new TelaPacientes().setVisible(true);
            dispose();
        });

        btnListaEspera.addActionListener(e -> {
            new TelaListaEspera().setVisible(true);
            dispose();
        });

        JPanel painelCentro = new JPanel(new BorderLayout());
        painelCentro.setBackground(corPrincipal);
        painelPrincipal.add(painelCentro, BorderLayout.CENTER);

        JLabel lblTitulo = new JLabel("Agendamento", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        painelCentro.add(lblTitulo, BorderLayout.NORTH);

        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBackground(corCards);
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        painelCentro.add(painelFormulario, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblPaciente = new JLabel("Paciente");
        lblPacienteValor = new JLabel(encaminhamentoInicial.getPacienteNome());

        JLabel lblEspecialidade = new JLabel("Especialidade");
        lblEspecialidadeValor = new JLabel(encaminhamentoInicial.getEspecialidadeNome());

        adicionarCampo(painelFormulario, gbc, lblPaciente, lblPacienteValor, 0);
        adicionarCampo(painelFormulario, gbc, lblEspecialidade, lblEspecialidadeValor, 1);

        JLabel lblLocal = new JLabel("Local");
        campoLocal = new JTextField();

        JLabel lblData = new JLabel("Data");
        campoData = new JTextField();

        JLabel lblHorario = new JLabel("Horário");
        campoHorario = new JTextField();

        JLabel lblMedico = new JLabel("Médico");
        cbMedico = new JComboBox<>();
        preencherMedicos();

        adicionarCampo(painelFormulario, gbc, lblLocal, campoLocal, 2);
        adicionarCampo(painelFormulario, gbc, lblData, campoData, 3);
        adicionarCampo(painelFormulario, gbc, lblHorario, campoHorario, 4);
        adicionarCampo(painelFormulario, gbc, lblMedico, cbMedico, 5);

        JButton btnNovoMedico = new JButton("+ Novo médico");
        btnNovoMedico.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy++;
        painelFormulario.add(btnNovoMedico, gbc);

        btnNovoMedico.addActionListener(e -> {
    new TelaCadastroMedico(encaminhamentoInicial).setVisible(true);
    dispose();
});


        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(corPrincipal);
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        JButton btnConfirmar = new JButton("Confirmar agendamento");
        btnConfirmar.setFont(new Font("Arial", Font.BOLD, 16));
        btnConfirmar.setBackground(corMenu);
        btnConfirmar.setPreferredSize(new Dimension(240, 50));
        painelBotoes.add(btnConfirmar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCancelar.setBackground(Color.WHITE);
        btnCancelar.setPreferredSize(new Dimension(180, 50));
        painelBotoes.add(btnCancelar);

        painelCentro.add(painelBotoes, BorderLayout.SOUTH);

       btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String local = campoLocal.getText();
                    String dataTexto = campoData.getText();
                    SimpleDateFormat formatoUsuario = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date dataUtil = formatoUsuario.parse(dataTexto);
                    java.sql.Date dataSQL = new java.sql.Date(dataUtil.getTime());
                    String horarioTexto = campoHorario.getText();
                    Time horario = Time.valueOf(horarioTexto + ":00");
                    Medico medicoSelecionado = (Medico) cbMedico.getSelectedItem();
                    int medicoId = medicoSelecionado.getId();
                    int encaminhamentoId = encaminhamentoInicial.getId();
                    Agendamento agendamento = new Agendamento(dataSQL, horario, local, medicoId, encaminhamentoId);
                    new AgendamentoDAO().cadastrarAgendamento(agendamento);
                    String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(dataUtil);
                    String horaFormatada = new SimpleDateFormat("HH:mm").format(horario);
                    JOptionPane.showMessageDialog(TelaAgendamento.this, "Agendamento salvo com sucesso!");
                    new TelaComprovante(
                            encaminhamentoInicial.getPacienteNome(),
                            local,
                            encaminhamentoInicial.getEspecialidadeNome(),
                            dataFormatada,
                            medicoSelecionado.getNome(),
                            horaFormatada
                    ).setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(TelaAgendamento.this, "Erro ao salvar agendamento. Verifique os campos.");
                }
            }
        });
        btnCancelar.addActionListener(e -> {
            new TelaListaEspera().setVisible(true);
            dispose();
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int largura = getWidth();
                int altura = getHeight();
                int larguraMenu = (int) (largura * 0.18);
                menuLateral.setPreferredSize(new Dimension(larguraMenu, altura));

                tituloMenu.setBounds(0, 20, larguraMenu, 40);
                int botaoY = 80;
                for (JButton btn : botoesMenu) {
                    btn.setBounds(0, botaoY, larguraMenu, 40);
                    botaoY += 50;
                }

                menuLateral.revalidate();
                menuLateral.repaint();
            }
        });
    }

    private void adicionarCampo(JPanel painel, GridBagConstraints gbc, JLabel label, JComponent campo, int linha) {
        label.setFont(new Font("Arial", Font.BOLD, 16));
        campo.setPreferredSize(new Dimension(250, 30));
        if (campo instanceof JTextField) {
            campo.setBackground(new Color(221, 230, 245));
            ((JTextField) campo).setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        }

        gbc.gridx = linha % 2;
        gbc.gridy = linha / 2 * 2;
        painel.add(label, gbc);

        gbc.gridy++;
        painel.add(campo, gbc);
    }

    private void preencherMedicos() {
        List<Medico> lista = new MedicoDAO().listarTodos();
        for (Medico m : lista) {
            cbMedico.addItem(m);
        }
    }
}
