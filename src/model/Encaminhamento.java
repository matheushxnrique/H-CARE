package model;

import java.util.Date;

public class Encaminhamento {
    private final int id;
    private final int pacienteId;
    private final int especialidadeId;
    private final String tipo;
    private final String descricao;
    private final Date dataCadastro;

    public Encaminhamento(int id, int pacienteId, int especialidadeId, String tipo, String descricao, Date dataCadastro) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.especialidadeId = especialidadeId;
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
    }

    public int getId() {
        return id;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public int getEspecialidadeId() {
        return especialidadeId;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }
}
