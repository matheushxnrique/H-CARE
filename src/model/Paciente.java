package model;

import java.sql.Date;

public class Paciente {
    private int id;
    private String nome;
    private String rg;
    private String cpf;
    private Date dataNascimento;
    private String email;
    private String endereco;
    private String telefone;
    private String cartaoSUS;

    public Paciente() {
    }

    public Paciente(String nome, String rg, String cpf, Date dataNascimento,
                    String email, String endereco, String telefone, String cartaoSUS) {
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cartaoSUS = cartaoSUS;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getRg() {
        return rg;
    }

    public String getCpf() {
        return cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCartaoSUS() {
        return cartaoSUS;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCartaoSUS(String cartaoSUS) {
        this.cartaoSUS = cartaoSUS;
    }

    @Override
    public String toString() {
        return nome;
    }
}
