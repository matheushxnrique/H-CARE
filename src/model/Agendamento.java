package model;

import java.sql.Date;
import java.sql.Time;

public class Agendamento {
    private final Date data;
    private final Time horario;
    private final String local;
    private final int medicoId;
    private final int encaminhamentoId;

    public Agendamento(Date data, Time horario, String local, int medicoId, int encaminhamentoId) {
        this.data = data;
        this.horario = horario;
        this.local = local;
        this.medicoId = medicoId;
        this.encaminhamentoId = encaminhamentoId;
    }

    public Date getData() {
        return data;
    }

    public Time getHorario() {
        return horario;
    }

    public String getLocal() {
        return local;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public int getEncaminhamentoId() {
        return encaminhamentoId;
    }
}
