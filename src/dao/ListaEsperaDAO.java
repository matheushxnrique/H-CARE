package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ListaEspera;
import util.Conexao;

public class ListaEsperaDAO {

    public List<ListaEspera> listarTodosComFiltro(String nomePaciente) {
        List<ListaEspera> lista = new ArrayList<>();

        String sql = "SELECT le.id, p.nome AS nome_paciente, e.nome AS especialidade, le.tipo, le.data_cadastro " +
                     "FROM listas_de_espera le " +
                     "JOIN pacientes p ON le.paciente_id = p.id " +
                     "JOIN especialidades e ON le.especialidade_id = e.id " +
                     "WHERE p.nome LIKE ? " +
                     "ORDER BY le.data_cadastro DESC";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nomePaciente + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ListaEspera le = new ListaEspera();
                le.setId(rs.getInt("id"));
                le.setNomePaciente(rs.getString("nome_paciente"));
                le.setEspecialidade(rs.getString("especialidade"));
                le.setTipo(rs.getString("tipo"));
                le.setDataCadastro(rs.getDate("data_cadastro").toLocalDate());

                lista.add(le);
            }

        } catch (SQLException e) {
        }

        return lista;
    }
}
