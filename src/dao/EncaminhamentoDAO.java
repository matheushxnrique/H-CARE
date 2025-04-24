package dao;

import model.Encaminhamento;
import model.EncaminhamentoView;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EncaminhamentoDAO {

    public boolean inserir(Encaminhamento enc) {
        String sql = "INSERT INTO encaminhamentos (paciente_id, especialidade_id, tipo, descricao, data) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, enc.getPacienteId());
            stmt.setInt(2, enc.getEspecialidadeId());
            stmt.setString(3, enc.getTipo());
            stmt.setString(4, enc.getDescricao());
            stmt.setDate(5, new java.sql.Date(enc.getDataCadastro().getTime()));

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<EncaminhamentoView> listarView() {
        List<EncaminhamentoView> lista = new ArrayList<>();
        String sql = "SELECT e.id, p.nome AS nome_paciente, esp.nome AS nome_especialidade " +
                     "FROM encaminhamentos e " +
                     "JOIN pacientes p ON e.paciente_id = p.id " +
                     "JOIN especialidades esp ON e.especialidade_id = esp.id";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nomePaciente = rs.getString("nome_paciente");
                String nomeEspecialidade = rs.getString("nome_especialidade");
                lista.add(new EncaminhamentoView(id, nomePaciente, nomeEspecialidade));
            }

        } catch (SQLException e) {
        }

        return lista;
    }

    public EncaminhamentoView buscarPorId(int id) {
        String sql = "SELECT e.id, p.nome AS nome_paciente, esp.nome AS nome_especialidade " +
                     "FROM encaminhamentos e " +
                     "JOIN pacientes p ON e.paciente_id = p.id " +
                     "JOIN especialidades esp ON e.especialidade_id = esp.id " +
                     "WHERE e.id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nomePaciente = rs.getString("nome_paciente");
                String nomeEspecialidade = rs.getString("nome_especialidade");
                return new EncaminhamentoView(id, nomePaciente, nomeEspecialidade);
            }

        } catch (SQLException e) {
        }

        return null;
    }
}
