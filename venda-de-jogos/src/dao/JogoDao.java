package dao;

import model.Jogo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gabriel_piske
 */
public class JogoDao {

    private Connection con;
    ResultSet rs;
    PreparedStatement pstm;

    public JogoDao() {
        this.con = new ConexaoBanco().getConnection();
    }

    public int cadJogo(Jogo jogo) {
        String sql = "INSERT INTO jogo (nome, descricao, preco, datalancamento, classificacaoindicativa, imagem) VALUES (?, ?, ?, ?, ?, ?)";
        int idGerado = -1;

        try (Connection conn = new ConexaoBanco().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, jogo.getNome());
            stmt.setString(2, jogo.getDescricao());
            stmt.setDouble(3, jogo.getPreco());
            stmt.setString(4, jogo.getDataLancamento());
            stmt.setString(5, jogo.getClassificacaoIndicativa());
            stmt.setBytes(6, jogo.getImagem());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    idGerado = rs.getInt(1); // ID gerado pelo banco
                    jogo.setIdJogo(idGerado);   // Atualiza o ID no objeto
                }
            }
            JOptionPane.showMessageDialog(null, "Jogo Salvo com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar o jogo: " + e.getMessage(), e);
        }

        return idGerado;
    }

    public void listJogos(DefaultTableModel model) {
        String sql = "SELECT * FROM jogo";

        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            //model.setRowCount(0);
            while (rs.next()) {
                Object[] row = new Object[7];

                row[0] = rs.getInt("idjogo");
                row[1] = rs.getString("nome");
                row[2] = rs.getString("descricao");
                row[3] = rs.getDouble("preco");
                row[4] = rs.getString("dataLancamento");
                row[5] = rs.getString("classificacaoIndicativa");

                // Converte a imagem do banco para ImageIcon
                byte[] imgBytes = rs.getBytes("imagem");
                if (imgBytes != null) {
                    ImageIcon imageIcon = new ImageIcon(imgBytes);
                    row[6] = imageIcon;
                } else {
                    row[6] = null;
                }

                // Adiciona a linha à tabela
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateJogo(Jogo jogo) {
        String sql = "UPDATE jogo SET nome = ?, descricao = ?, preco = ?, datalancamento = ?, "
                + "classificacaoindicativa = ?, imagem = ? "
                + "WHERE idjogo = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, jogo.getNome());
            stmt.setString(2, jogo.getDescricao());
            stmt.setDouble(3, jogo.getPreco());
            stmt.setString(4, jogo.getDataLancamento());
            stmt.setString(5, jogo.getClassificacaoIndicativa());
            stmt.setBytes(6, jogo.getImagem());
            stmt.setInt(7, jogo.getIdJogo());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Jogo atualizado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o jogo: " + e.getMessage(), e);
        }
    }

    public void deleteById(int id) {
        if (isJogoEmUso(id)) {
            JOptionPane.showMessageDialog(null, "Não é possivel excluir um jogo presente em algum carrinho.");
            return;
        }

        String sql = "DELETE FROM jogo WHERE idjogo = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Jogo excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir jogo. Jogo não encontrado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar excluir o jogo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean isJogoEmUso(int id) {
        String sql = "SELECT COUNT(*) FROM carrinho WHERE jogo_idjogo = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao verificar dependências: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
