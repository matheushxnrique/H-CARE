import java.time.LocalDate;
import java.time.LocalTime;

public class Agendamentos {
    private int id;
    private LocalDate data;
    private LocalTime horario;
    private String local;
    private int medicoId;
    private int encaminhamentoId;

    
    public Agendamentos(int id, LocalDate data, LocalTime horario, String local, int medicoId, int encaminhamentoId) {
        this.id = id;
        this.data = data;
        this.horario = horario;
        this.local = local;
        this.medicoId = medicoId;
        this.encaminhamentoId = encaminhamentoId;
    }

  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }

    public int getEncaminhamentoId() {
        return encaminhamentoId;
    }

    public void setEncaminhamentoId(int encaminhamentoId) {
        this.encaminhamentoId = encaminhamentoId;
    }

   
    public void exibirInformacoes() {
        System.out.println("Agendamento ID: " + id);
        System.out.println("Data: " + data);
        System.out.println("Horário: " + horario);
        System.out.println("Local: " + local);
        System.out.println("Médico ID: " + medicoId);
        System.out.println("Encaminhamento ID: " + encaminhamentoId);
    }
}
