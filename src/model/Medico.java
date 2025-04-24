package model;

public class Medico {
    private int id;
    private String crm;
    private String nome;
    private String especialidade;

    public Medico(int id, String crm, String nome, String especialidade) {
        this.id = id;
        this.crm = crm;
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public int getId() {
        return id;
    }

    public String getCrm() {
        return crm;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    @Override
public String toString() {
    return this.nome + " - " +this.crm; 
}

}
