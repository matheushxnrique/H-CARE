import java.time.LocalDate;  
public class Encaminhamento {
    private int id;
    private String tipo;
    private LocalDate data;  
    private String descricao;
    private int pacienteId;
    private int usuarioId;

  
    public Encaminhamento(int id, String tipo, LocalDate data, String descricao, int pacienteId, int usuarioId) {
        this.id = id;
        this.tipo = tipo;
        this.data = data;  
        this.descricao = descricao;
        this.pacienteId = pacienteId;
        this.usuarioId = usuarioId;
    }

  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getData() {  
        return data;
    }

    public void setData(LocalDate data) {  
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    
    public void exibirInformacoes() {
        System.out.println("Encaminhamento Tipo: " + tipo);
        System.out.println("Data: " + data);
        System.out.println("Descrição: " + descricao);
    }
}
