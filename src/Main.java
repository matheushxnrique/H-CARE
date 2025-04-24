import java.time.LocalDate;  

public class Main {
    public static void main(String[] args) {

        Paciente paciente = new Paciente(1, "Ana Paula", "45678901234", "123456789", "9876543210", 
                                          "Rua A, 123", "4445556666", LocalDate.parse("1975-03-15"));
        
      
        paciente.exibirInformacoes();

      
        Usuario usuario = new Usuario(1, "Matheus Silva", "12345678901", "51123456789", "matheus.silva@email.com");
        
       
        usuario.exibirInformacoes();
    }
}
