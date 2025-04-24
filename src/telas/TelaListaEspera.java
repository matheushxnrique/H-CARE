package telas;

import dao.EncaminhamentoDAO;
import model.EncaminhamentoView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TelaListaEspera extends JFrame {

    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JTextField campoBusca;

    public TelaListaEspera() {
        setTitle("H-CARE - Lista de Espera");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(224, 232, 250));

        Color corMenu = new Color(181, 198, 235);
        Color corSelecionado = new Color(150, 170, 210);

    
        JPanel menu = new JPanel(null);
        menu.setBackground(corMenu);
        menu.setBounds(0, 0, 200, getHeight());
        add(menu);

        JLabel titulo = new JLabel("H-CARE", SwingConstants.CENTER);
        titulo.setFont(new Font("Montserrat", Font.BOLD, 24));
        titulo.setBounds(0, 20, 200, 40);
        menu.add(titulo);

        JButton btnInicio = criarBotaoMenu("Início", 80, e -> abrirNovaJanela(new TelaInicial()));
        JButton btnPacientes = criarBotaoMenu("Pacientes", 130, e -> abrirNovaJanela(new TelaPacientes()));
        JButton btnLista = criarBotaoMenu("Listas de espera", 180, null);
        btnLista.setBackground(corSelecionado);
        menu.add(btnInicio);
        menu.add(btnPacientes);
        menu.add(btnLista);

        JButton btnNovo = new JButton("+ Novo encaminhamento");
        btnNovo.setFont(new Font("Arial", Font.BOLD, 14));
        btnNovo.setBounds(1130, 20, 240, 40);
        btnNovo.setBackground(corMenu);
        btnNovo.setFocusPainted(false);
        btnNovo.addActionListener(e -> abrirNovaJanela(new TelaCadastroEncaminhamento()));
        add(btnNovo);

        campoBusca = new JTextField("Buscar por paciente");
        campoBusca.setForeground(Color.GRAY);
        campoBusca.setFont(new Font("Arial", Font.PLAIN, 14));
        campoBusca.setBounds(240, 80, 240, 35);
        add(campoBusca);

        campoBusca.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campoBusca.getText().equals("Buscar por paciente")) {
                    campoBusca.setText("");
                    campoBusca.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campoBusca.getText().isEmpty()) {
                    campoBusca.setText("Buscar por paciente");
                    campoBusca.setForeground(Color.GRAY);
                }
            }
        });

        campoBusca.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                carregarTabela(campoBusca.getText().trim());
            }
        });

        modeloTabela = new DefaultTableModel(
            new Object[]{"ID", "Nome do Paciente", "Especialidade", "Tipo", "Data de Inserção"}, 0
        );

        tabela = new JTable(modeloTabela);
        tabela.getColumnModel().getColumn(0).setMinWidth(0);
        tabela.getColumnModel().getColumn(0).setMaxWidth(0);
        tabela.getColumnModel().getColumn(0).setWidth(0);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(220, 130, 1150, 500);
        add(scroll);

        JButton btnAgendar = new JButton("Agendar");
        btnAgendar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAgendar.setBounds(700, 650, 200, 40);
        btnAgendar.setBackground(corMenu);
        btnAgendar.setFocusPainted(false);
        btnAgendar.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada != -1) {
                try {
                    int idEncaminhamento = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
                    EncaminhamentoDAO dao = new EncaminhamentoDAO();
                    EncaminhamentoView enc = dao.buscarPorId(idEncaminhamento);

                    if (enc != null) {
                        abrirNovaJanela(new TelaAgendamento(enc));
                    } else {
                        JOptionPane.showMessageDialog(this, "Encaminhamento não encontrado.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao carregar dados do encaminhamento.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um item da lista para agendar.");
            }
        });
        add(btnAgendar);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                menu.setBounds(0, 0, 200, getHeight());
            }
        });

        carregarTabela("");
    }

    private JButton criarBotaoMenu(String texto, int y, ActionListener acao) {
        JButton botao = new JButton(texto);
        botao.setBounds(0, y, 200, 40);
        botao.setFont(new Font("Arial", Font.PLAIN, 16));
        botao.setBackground(new Color(181, 198, 235));
        botao.setBorderPainted(false);
        botao.setFocusPainted(false);
        if (acao != null) botao.addActionListener(acao);
        return botao;
    }

    private void carregarTabela(String filtro) {
        modeloTabela.setRowCount(0);
        EncaminhamentoDAO dao = new EncaminhamentoDAO();
        List<EncaminhamentoView> lista = dao.listarView();

        for (EncaminhamentoView e : lista) {
            if (filtro.isEmpty() || e.getPacienteNome().toLowerCase().contains(filtro.toLowerCase())) {
                modeloTabela.addRow(new Object[]{
                    e.getId(),
                    e.getPacienteNome(),
                    e.getEspecialidadeNome(),
                    e.getTipo(),
                    e.getDataCadastro()
                });
            }
        }
    }

    private void abrirNovaJanela(JFrame janela) {
        janela.setVisible(true);
        dispose();
    }


}
