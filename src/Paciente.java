import java.time.LocalDate;

public class Paciente {
    private int id;
    private String nome;
    private String cpf;
    private String rg;
    private String cartaoSUS;
    private String endereco;
    private String telefone;
    private LocalDate dataNascimento;  

    // Construtor
    public Paciente(int id, String nome, String cpf, String rg, String cartaoSUS, String endereco, String telefone, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.cartaoSUS = cartaoSUS;
        this.endereco = endereco;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;  
    }

  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCartaoSUS() {
        return cartaoSUS;
    }

    public void setCartaoSUS(String cartaoSUS) {
        this.cartaoSUS = cartaoSUS;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() { 
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) { 
        this.dataNascimento = dataNascimento;
    }

    
    public void exibirInformacoes() {
        System.out.println("Paciente: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("RG: " + rg);
        System.out.println("Cartão SUS: " + cartaoSUS);
        System.out.println("Endereço: " + endereco);
        System.out.println("Telefone: " + telefone);
        System.out.println("Data de Nascimento: " + dataNascimento);  
    }
}
