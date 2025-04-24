package telas;

import model.EncaminhamentoView;
import model.Medico;
import dao.MedicoDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TelaCadastroMedico extends JFrame {

    private EncaminhamentoView encaminhamento;

    public TelaCadastroMedico(EncaminhamentoView encaminhamento) {
        this.encaminhamento = encaminhamento;

        setTitle("Cadastro de Médico");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Color corFundo = new Color(224, 231, 244);
        Color corMenu = new Color(173, 194, 215);

        JPanel menuLateral = new JPanel();
        menuLateral.setBackground(corMenu);
        menuLateral.setPreferredSize(new Dimension(200, getHeight()));
        menuLateral.setLayout(new BoxLayout(menuLateral, BoxLayout.Y_AXIS));

        JLabel lblLogo = new JLabel("H-CARE");
        lblLogo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblLogo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuLateral.add(lblLogo);
        add(menuLateral, BorderLayout.WEST);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(corFundo);
        add(painelPrincipal, BorderLayout.CENTER);

        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.setOpaque(false);
        painelTopo.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 30));

        JLabel lblTitulo = new JLabel("Cadastro de Médico", SwingConstants.LEFT);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        painelTopo.add(lblTitulo, BorderLayout.WEST);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnVoltar.setBackground(corMenu);
        painelTopo.add(btnVoltar, BorderLayout.EAST);

        painelPrincipal.add(painelTopo, BorderLayout.NORTH);

        JPanel painelForm = new JPanel(new GridBagLayout());
        painelForm.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        JTextField txtNome = criarPlaceholder("Nome");
        JTextField txtCRM = criarPlaceholder("CRM");
        JComboBox<String> comboEspecialidade = new JComboBox<>(new String[] {
                "Cardiologia", "Dermatologia", "Ortopedia"
        });

        comboEspecialidade.setPreferredSize(new Dimension(300, 40));
        comboEspecialidade.setFont(new Font("SansSerif", Font.PLAIN, 16));

        gbc.gridy = 0;
        painelForm.add(txtNome, gbc);
        gbc.gridy++;
        painelForm.add(txtCRM, gbc);
        gbc.gridy++;
        painelForm.add(comboEspecialidade, gbc);

        JButton btnCadastrar = new JButton("CADASTRAR");
        btnCadastrar.setBackground(corMenu);
        btnCadastrar.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridy++;
        painelForm.add(btnCadastrar, gbc);

        painelPrincipal.add(painelForm, BorderLayout.CENTER);

        btnVoltar.addActionListener(e -> {
            new TelaAgendamento(encaminhamento).setVisible(true);
            dispose();
        });

        btnCadastrar.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String crm = txtCRM.getText().trim();
            String especialidade = (String) comboEspecialidade.getSelectedItem();

            if (!nome.isEmpty() && !crm.isEmpty()) {
                Medico medico = new Medico(0, crm, nome, especialidade);
                boolean sucesso = new MedicoDAO().inserirMedico(medico);

                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Médico cadastrado com sucesso!");
                    new TelaAgendamento(encaminhamento).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao cadastrar médico.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente.");
            }
        });
    }

    private JTextField criarPlaceholder(String texto) {
        JTextField campo = new JTextField(texto);
        campo.setForeground(Color.GRAY);
        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campo.getText().equals(texto)) {
                    campo.setText("");
                    campo.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setForeground(Color.GRAY);
                    campo.setText(texto);
                }
            }
        });
        campo.setPreferredSize(new Dimension(300, 40));
        campo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        return campo;
    }
}
