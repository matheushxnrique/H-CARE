import java.time.LocalDate;

public class ListasDeEspera {
    private int id;
    private int pacienteId;
    private int especialidadeId;
    private LocalDate dataCadastro;

 
    public ListasDeEspera(int id, int pacienteId, int especialidadeId, LocalDate dataCadastro) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.especialidadeId = especialidadeId;
        this.dataCadastro = dataCadastro;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public int getEspecialidadeId() {
        return especialidadeId;
    }

    public void setEspecialidadeId(int especialidadeId) {
        this.especialidadeId = especialidadeId;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

   
    public void exibirInformacoes() {
        System.out.println("Lista de Espera ID: " + id);
        System.out.println("Paciente ID: " + pacienteId);
        System.out.println("Especialidade ID: " + especialidadeId);
        System.out.println("Data de Cadastro: " + dataCadastro);
    }
}
