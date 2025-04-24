package dao;

import model.Paciente;
import model.PacienteView;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public void inserir(Paciente p) throws SQLException {
        String sql = "INSERT INTO pacientes (nome, rg, cpf, data_nascimento, email, endereco, telefone, cartao_sus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getRg());
            stmt.setString(3, p.getCpf());
            stmt.setDate(4, p.getDataNascimento());
            stmt.setString(5, p.getEmail());
            stmt.setString(6, p.getEndereco());
            stmt.setString(7, p.getTelefone());
            stmt.setString(8, p.getCartaoSUS());
            stmt.executeUpdate();
        }
    }

    public List<Paciente> buscarPacientes(String nome, String rg, String cpf, String dataNascimento, String cartaoSUS) {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM pacientes WHERE 1=1";

        if (!nome.isEmpty()) sql += " AND nome LIKE ?";
        if (!rg.isEmpty()) sql += " AND rg LIKE ?";
        if (!cpf.isEmpty()) sql += " AND cpf LIKE ?";
        if (!dataNascimento.isEmpty()) sql += " AND data_nascimento LIKE ?";
        if (!cartaoSUS.isEmpty()) sql += " AND cartao_sus LIKE ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int index = 1;
            if (!nome.isEmpty()) stmt.setString(index++, "%" + nome + "%");
            if (!rg.isEmpty()) stmt.setString(index++, "%" + rg + "%");
            if (!cpf.isEmpty()) stmt.setString(index++, "%" + cpf + "%");
            if (!dataNascimento.isEmpty()) stmt.setString(index++, "%" + dataNascimento + "%");
            if (!cartaoSUS.isEmpty()) stmt.setString(index++, "%" + cartaoSUS + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente p = new Paciente(
                    rs.getString("nome"),
                    rs.getString("rg"),
                    rs.getString("cpf"),
                    rs.getDate("data_nascimento"),
                    rs.getString("email"),
                    rs.getString("endereco"),
                    rs.getString("telefone"),
                    rs.getString("cartao_sus")
                );
                p.setId(rs.getInt("id"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar pacientes: " + e.getMessage());
        }

        return lista;
    }

    public List<Paciente> listarTodos() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM pacientes ORDER BY nome";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar pacientes: " + e.getMessage());
        }

        return lista;
    }

    public List<PacienteView> listarTodosParaComboBox() {
        List<PacienteView> lista = new ArrayList<>();
        String sql = "SELECT id, nome FROM pacientes ORDER BY nome";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                lista.add(new PacienteView(id, nome));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar pacientes para ComboBox: " + e.getMessage());
        }

        return lista;
    }
}
