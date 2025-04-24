package dao;

import model.Agendamento;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AgendamentoDAO {

    public void cadastrarAgendamento(Agendamento agendamento) {
        String sql = "INSERT INTO agendamentos (data, horario, local, medico_id, encaminhamento_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, agendamento.getData());
            stmt.setTime(2, agendamento.getHorario());
            stmt.setString(3, agendamento.getLocal());
            stmt.setInt(4, agendamento.getMedicoId());
            stmt.setInt(5, agendamento.getEncaminhamentoId());

            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
