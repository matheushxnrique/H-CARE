package telas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaLogin extends JFrame {

    private JPanel painelLogin;
    private JButton btnCadastrar;
    private JLabel titulo;

    public TelaLogin() {
        setTitle("H-CARE - Login");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(224, 232, 250));
        setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        
        titulo = new JLabel("H-CARE", SwingConstants.CENTER);
        titulo.setFont(new Font("Montserrat", Font.BOLD, 30));
        titulo.setSize(200, 40);
        add(titulo);

        
        painelLogin = new JPanel();
        painelLogin.setLayout(null);
        painelLogin.setBackground(Color.WHITE);
        painelLogin.setSize(300, 250); 
        painelLogin.setBorder(BorderFactory.createLineBorder(new Color(224, 232, 250), 0, true));
        add(painelLogin);

        
        JLabel lblLogin = new JLabel("LOGIN", SwingConstants.CENTER);
        lblLogin.setFont(new Font("Arial", Font.BOLD, 18));
        lblLogin.setBounds(0, 10, 300, 30);
        painelLogin.add(lblLogin);

        JLabel lblUsuario = new JLabel("USUÁRIO");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 12));
        lblUsuario.setBounds(20, 50, 100, 20);
        painelLogin.add(lblUsuario);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBackground(new Color(224, 232, 250));
        txtUsuario.setBounds(20, 70, 260, 30);
        painelLogin.add(txtUsuario);

        JLabel lblSenha = new JLabel("SENHA");
        lblSenha.setFont(new Font("Arial", Font.BOLD, 12));
        lblSenha.setBounds(20, 110, 100, 20);
        painelLogin.add(lblSenha);

        JPasswordField txtSenha = new JPasswordField();
        txtSenha.setBackground(new Color(224, 232, 250));
        txtSenha.setBounds(20, 130, 260, 30);
        painelLogin.add(txtSenha);

        JButton btnEntrar = new JButton("ENTRAR");
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 12));
        btnEntrar.setBackground(new Color(181, 198, 235));
        btnEntrar.setBounds(100, 180, 100, 30);
        painelLogin.add(btnEntrar);

        
        btnCadastrar = new JButton("CADASTRAR");
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCadastrar.setBackground(new Color(181, 198, 235));
        btnCadastrar.setSize(150, 30);
        add(btnCadastrar);

        
        btnEntrar.addActionListener(e -> {
            new TelaInicial().setVisible(true);
            dispose();
        });

        btnCadastrar.addActionListener(e -> {
            new TelaCadastroUsuario().setVisible(true);
            dispose();
        });

       
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                centralizarComponentes();
            }
        });

      
        centralizarComponentes();
    }

    private void centralizarComponentes() {
        int width = getWidth();
        int height = getHeight();

       
        painelLogin.setLocation((width - painelLogin.getWidth()) / 2, (height - painelLogin.getHeight()) / 2);

        
        btnCadastrar.setLocation((width - btnCadastrar.getWidth()) / 2, painelLogin.getY() + painelLogin.getHeight() + 20);

      
        titulo.setLocation((width - titulo.getWidth()) / 2, 30);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaLogin tela = new TelaLogin();
            tela.setVisible(true);
        });
    }
}
