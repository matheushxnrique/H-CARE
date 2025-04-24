package telas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TelaInicial extends JFrame {

    public TelaInicial() {
        setTitle("H-CARE - Tela Inicial");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1200, 700));
        getContentPane().setLayout(null);

        Color corPrincipal = new Color(224, 232, 250);
        Color corMenu = new Color(181, 198, 235);
        Color corSelecionado = new Color(150, 170, 210);

        getContentPane().setBackground(corPrincipal);

       
        JPanel menuLateral = new JPanel();
        menuLateral.setLayout(null);
        menuLateral.setBackground(corMenu);
        menuLateral.setBounds(0, 0, 200, getHeight());
        getContentPane().add(menuLateral);
        
        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(25, 500, 150, 30); 
        btnSair.setBackground(new Color(230, 236, 250));
        btnSair.setFont(new Font("Arial", Font.BOLD, 14));
        btnSair.addActionListener(e -> {
        new TelaLogin().setVisible(true);
      dispose();
    });
       menuLateral.add(btnSair);

        
        
       JButton btnEncerrar = new JButton("Encerrar");
       btnEncerrar.setBounds(25, 540, 150, 30); 
       btnEncerrar.setBackground(new Color(245, 118, 118));
       btnEncerrar.setForeground(Color.WHITE);
       btnEncerrar.setFont(new Font("Arial", Font.BOLD, 14));
       btnEncerrar.addActionListener(e -> {
         int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente encerrar o sistema?", "Confirmação", JOptionPane.YES_NO_OPTION);
         if (confirm == JOptionPane.YES_OPTION) {
        System.exit(0);
        }
    });
        menuLateral.add(btnEncerrar);
 
       

        JLabel tituloMenu = new JLabel("H-CARE", SwingConstants.CENTER);
        tituloMenu.setFont(new Font("Montserrat", Font.BOLD, 24));
        tituloMenu.setBounds(0, 20, 200, 40);
        menuLateral.add(tituloMenu);

       
        JButton btnTelaInicial = new JButton("Início");
        btnTelaInicial.setBounds(0, 80, 200, 40);
        btnTelaInicial.setFont(new Font("Arial", Font.PLAIN, 16));
        btnTelaInicial.setBackground(corSelecionado);
        btnTelaInicial.setOpaque(true);
        btnTelaInicial.setBorderPainted(false);
        btnTelaInicial.setFocusPainted(false);
        menuLateral.add(btnTelaInicial);

       
        JButton btnPacientes = new JButton("Pacientes");
        btnPacientes.setBounds(0, 130, 200, 40);
        btnPacientes.setFont(new Font("Arial", Font.PLAIN, 16));
        btnPacientes.setBackground(corMenu);
        btnPacientes.setOpaque(true);
        btnPacientes.setBorderPainted(false);
        btnPacientes.setFocusPainted(false);
        menuLateral.add(btnPacientes);

        
        JButton btnListaEspera = new JButton("Listas de espera");
        btnListaEspera.setBounds(0, 180, 200, 40);
        btnListaEspera.setFont(new Font("Arial", Font.PLAIN, 16));
        btnListaEspera.setBackground(corMenu);
        btnListaEspera.setOpaque(true);
        btnListaEspera.setBorderPainted(false);
        btnListaEspera.setFocusPainted(false);
        menuLateral.add(btnListaEspera);

        
        JLabel lblBemVindo = new JLabel("Bem vindo!", SwingConstants.CENTER);
        lblBemVindo.setFont(new Font("Arial", Font.BOLD, 32));
        lblBemVindo.setBounds(220, 50, 1000, 50);
        getContentPane().add(lblBemVindo);

        
        JPanel painelIndicadores = new JPanel();
        painelIndicadores.setLayout(new GridLayout(2, 3, 20, 10));
        painelIndicadores.setOpaque(false);
        painelIndicadores.setBounds(220, 120, 1000, 100);
        getContentPane().add(painelIndicadores);

        JLabel lblTotalPacientes = new JLabel("Total de pacientes", SwingConstants.CENTER);
        lblTotalPacientes.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel lblEmEspera = new JLabel("Em lista de espera", SwingConstants.CENTER);
        lblEmEspera.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel lblConsultas = new JLabel("Consultas agendadas", SwingConstants.CENTER);
        lblConsultas.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel valorTotalPacientes = new JLabel("201", SwingConstants.CENTER);
        valorTotalPacientes.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel valorEspera = new JLabel("5", SwingConstants.CENTER);
        valorEspera.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel valorConsultas = new JLabel("10", SwingConstants.CENTER);
        valorConsultas.setFont(new Font("Arial", Font.BOLD, 20));

        painelIndicadores.add(lblTotalPacientes);
        painelIndicadores.add(lblEmEspera);
        painelIndicadores.add(lblConsultas);
        painelIndicadores.add(valorTotalPacientes);
        painelIndicadores.add(valorEspera);
        painelIndicadores.add(valorConsultas);

        
        JLabel lblAlertas = new JLabel("Alertas", SwingConstants.LEFT);
        lblAlertas.setFont(new Font("Arial", Font.BOLD, 24));
        lblAlertas.setBounds(220, 250, 300, 30);
        getContentPane().add(lblAlertas);

        JLabel lblSemAlerta = new JLabel("Nenhum alerta no momento", SwingConstants.LEFT);
        lblSemAlerta.setFont(new Font("Arial", Font.PLAIN, 18));
        lblSemAlerta.setBounds(220, 290, 500, 30);
        getContentPane().add(lblSemAlerta);

        
        btnPacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaPacientes().setVisible(true);
                dispose();
            }
        });

        btnListaEspera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaListaEspera().setVisible(true);
                dispose();
            }
        });
    }

    
  
}
