package telas;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;


public class TelaComprovante extends JFrame {

    public TelaComprovante(String paciente, String local, String especialidade, String data, String medico, String horario) {
        setTitle("Comprovante de Consulta");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
try {
    SimpleDateFormat formatoOriginalData = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formatoExibicaoData = new SimpleDateFormat("dd/MM/yyyy");
    data = formatoExibicaoData.format(formatoOriginalData.parse(data));

    SimpleDateFormat formatoOriginalHora = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat formatoExibicaoHora = new SimpleDateFormat("HH:mm");
    horario = formatoExibicaoHora.format(formatoOriginalHora.parse(horario));
} catch (ParseException e) {
            
}


        JPanel fundo = new JPanel(new GridBagLayout());
        fundo.setBackground(new Color(224, 231, 244));
        add(fundo, BorderLayout.CENTER);

        JPanel painelComprovante = new JPanel(new GridBagLayout());
        painelComprovante.setBackground(Color.WHITE);
        painelComprovante.setPreferredSize(new Dimension(700, 550));
        painelComprovante.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        fundo.add(painelComprovante);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 20, 12, 40);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Consulta Agendada!");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 26));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        painelComprovante.add(lblTitulo, gbc);

        Font fonteLabel = new Font("SansSerif", Font.BOLD, 16);
        Font fonteInfo = new Font("SansSerif", Font.PLAIN, 16);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;

        gbc.gridy++;
        gbc.gridx = 0;
        painelComprovante.add(criarLabel("DATA:", fonteLabel), gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(12, 120, 12, 20);
        painelComprovante.add(criarLabel("HORÁRIO:", fonteLabel), gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        painelComprovante.add(criarLabel(data, fonteInfo), gbc);
        gbc.gridx = 1;
        painelComprovante.add(criarLabel(horario, fonteInfo), gbc);
        gbc.insets = new Insets(12, 20, 12, 40);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        painelComprovante.add(criarLabel("Paciente:", fonteLabel), gbc);
        gbc.gridy++;
        painelComprovante.add(criarLabel(paciente, fonteInfo), gbc);

        gbc.gridy++;
        painelComprovante.add(criarLabel("Especialidade:", fonteLabel), gbc);
        gbc.gridy++;
        painelComprovante.add(criarLabel(especialidade, fonteInfo), gbc);

        gbc.gridy++;
        painelComprovante.add(criarLabel("Médico:", fonteLabel), gbc);
        gbc.gridy++;
        painelComprovante.add(criarLabel(medico, fonteInfo), gbc);


        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(12, 20, 12, 40);
        painelComprovante.add(criarLabel("Local:", fonteLabel), gbc);

        gbc.gridy++;
        painelComprovante.add(criarLabel(local, fonteInfo), gbc);



        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(new Color(173, 194, 215));
        btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 14));
        JPanel painelTopo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelTopo.setBackground(new Color(224, 231, 244));
        painelTopo.add(btnVoltar);
        add(painelTopo, BorderLayout.NORTH);

        btnVoltar.addActionListener(e -> {
            new TelaPacientes().setVisible(true);
            dispose();
        });
    }

    private JLabel criarLabel(String texto, Font fonte) {
        JLabel label = new JLabel(texto);
        label.setFont(fonte);
        return label;
    }

  
}