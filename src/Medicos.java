public class Medicos {
    private int id;
    private String crm;
    private String nome;
    private int especialidadeId;


    public Medicos(int id, String crm, String nome, int especialidadeId) {
        this.id = id;
        this.crm = crm;
        this.nome = nome;
        this.especialidadeId = especialidadeId;
    }

 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEspecialidadeId() {
        return especialidadeId;
    }

    public void setEspecialidadeId(int especialidadeId) {
        this.especialidadeId = especialidadeId;
    }

  
    public void exibirInformacoes() {
        System.out.println("Médico ID: " + id);
        System.out.println("CRM: " + crm);
        System.out.println("Nome: " + nome);
        System.out.println("Especialidade ID: " + especialidadeId);
    }
}
