package telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.Usuario;
import dao.UsuarioDAO;


public class TelaCadastroUsuario extends JFrame {

    public TelaCadastroUsuario() {
        setTitle("H-CARE - Cadastro de Usuário");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
        Color azulFundo = new Color(224, 232, 250);
        Color azulMenu = new Color(173, 191, 217);
        Color azulBotao = new Color(181, 198, 235);

        getContentPane().setBackground(azulFundo);
        setLayout(null);

        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        int largura = tela.width;
        int altura = tela.height;

        
        JPanel menu = new JPanel();
        menu.setBackground(azulMenu);
        menu.setBounds(0, 0, 200, altura);
        menu.setLayout(null);
        add(menu);

        JLabel tituloLateral = new JLabel("H-CARE", SwingConstants.CENTER);
        tituloLateral.setFont(new Font("Montserrat", Font.BOLD, 22));
        tituloLateral.setBounds(0, 30, 200, 40);
        menu.add(tituloLateral);

       
        JLabel titulo = new JLabel("Cadastro de usuário", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setBounds(200, 40, largura - 200, 40);
        add(titulo);

        
        int painelLargura = 500;
        int painelAltura = 300;
        int painelX = (largura - painelLargura) / 2 + 100;
        int painelY = (altura - painelAltura) / 2 - 50;

        JPanel painelForm = new JPanel();
        painelForm.setBackground(Color.WHITE);
        painelForm.setBounds(painelX, painelY, painelLargura, painelAltura);
        painelForm.setLayout(null);
        add(painelForm);

        
        JTextField campoNome = criarCampoComPlaceholder("Nome", 20, painelLargura);
        JTextField campoCPF = criarCampoComPlaceholder("CPF", 80, painelLargura);
        JTextField campoEmail = criarCampoComPlaceholder("E-mail", 140, painelLargura);
        JTextField campoTelefone = criarCampoComPlaceholder("Telefone", 200, painelLargura);

        painelForm.add(campoNome);
        painelForm.add(campoCPF);
        painelForm.add(campoEmail);
        painelForm.add(campoTelefone);

        
        JButton btnCadastrar = new JButton("CADASTRAR");
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCadastrar.setBackground(azulBotao);
        btnCadastrar.setBounds(painelX + 270, painelY + painelAltura + 30, 150, 35);
        add(btnCadastrar);

        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
        btnVoltar.setBackground(azulBotao);
        btnVoltar.setBounds(painelX, painelY + painelAltura + 30, 150, 35);
        add(btnVoltar);

        
     btnCadastrar.addActionListener(e -> {
    String nome = campoNome.getText();
    String cpf = campoCPF.getText();
    String telefone = campoTelefone.getText();
    String email = campoEmail.getText();

    if (nome.equals("Nome") || cpf.equals("CPF") || telefone.equals("Telefone") || email.equals("E-mail")) {
        JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente.");
        return;
    }

    Usuario novoUsuario = new Usuario(nome, cpf, telefone, email);
    UsuarioDAO dao = new UsuarioDAO();
    dao.cadastrarUsuario(novoUsuario);

    new TelaInicial().setVisible(true);
    dispose();
});


        btnVoltar.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            dispose();
        });
    }

  
    private JTextField criarCampoComPlaceholder(String placeholder, int y, int larguraPainel) {
        JTextField campo = new JTextField(placeholder);
        campo.setFont(new Font("Arial", Font.PLAIN, 16));
        campo.setBounds(30, y, larguraPainel - 60, 40);
        campo.setBackground(new Color(224, 232, 250));
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
                    campo.setText(placeholder);
                    campo.setForeground(Color.GRAY);
                }
            }
        });

        return campo;
    }

  
    
}
