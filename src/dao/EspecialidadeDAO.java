package dao;

import java.sql.*;
import java.util.*;
import model.Especialidade;
import util.Conexao;

public class EspecialidadeDAO {
    public List<Especialidade> listar() {
        List<Especialidade> lista = new ArrayList<>();
        String sql = "SELECT * FROM especialidades";

        try (Connection conn = Conexao.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                lista.add(new Especialidade(id, nome));
            }
        } catch (SQLException e) {
        }

        return lista;
    }
}
