package dao;

import java.sql.*;
import java.util.*;
import model.Medico;
import util.Conexao;

public class MedicoDAO {

    public boolean inserirMedico(Medico medico) {
        String sql = "INSERT INTO medicos (crm, nome, especialidade) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medico.getCrm());
            stmt.setString(2, medico.getNome());
            stmt.setString(3, medico.getEspecialidade()); 

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir médico: " + e.getMessage());
            return false;
        }
    }

    public List<Medico> listarTodos() {
        List<Medico> lista = new ArrayList<>();
        String sql = "SELECT * FROM medicos ORDER BY nome";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Medico medico = new Medico(
                    rs.getInt("id"),
                    rs.getString("crm"),
                    rs.getString("nome"),
                    rs.getString("especialidade") 
                );
                lista.add(medico);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar médicos: " + e.getMessage());
        }

        return lista;
    }
}
