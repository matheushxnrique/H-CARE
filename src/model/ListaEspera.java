package model;

import java.time.LocalDate;

public class ListaEspera {
    private int id;
    private String nomePaciente;
    private String especialidade;
    private String tipo;
    private LocalDate dataCadastro;

    
    public ListaEspera() {}

    
    public ListaEspera(int id, String nomePaciente, String especialidade, String tipo, LocalDate dataCadastro) {
        this.id = id;
        this.nomePaciente = nomePaciente;
        this.especialidade = especialidade;
        this.tipo = tipo;
        this.dataCadastro = dataCadastro;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
