package model;

import java.util.Date;

public class EncaminhamentoView {
    private int id;
    private String nomePaciente;
    private String nomeEspecialidade;
    private String tipo;
    private Date dataCadastro;

    public EncaminhamentoView(int id, String nomePaciente, String nomeEspecialidade, String tipo, Date dataCadastro) {
        this.id = id;
        this.nomePaciente = nomePaciente;
        this.nomeEspecialidade = nomeEspecialidade;
        this.tipo = tipo;
        this.dataCadastro = dataCadastro;
    }

    public EncaminhamentoView(int id, String nomePaciente, String nomeEspecialidade) {
        this.id = id;
        this.nomePaciente = nomePaciente;
        this.nomeEspecialidade = nomeEspecialidade;
        this.tipo = "";
        this.dataCadastro = null;
    }

    public int getId() {
        return id;
    }

    public String getPacienteNome() {
        return nomePaciente;
    }

    public String getEspecialidadeNome() {
        return nomeEspecialidade;
    }

    public String getTipo() {
        return tipo;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }
}
