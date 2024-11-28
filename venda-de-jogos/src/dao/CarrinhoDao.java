package dao;

import connection.ConexaoBanco;
import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Carrinho;
import model.Jogo;

/**
 *
 * @author gabriel_piske
 */
public class CarrinhoDao {

    private Connection con;
    ResultSet rs;
    PreparedStatement pstm;

    public CarrinhoDao() {
        this.con = new ConexaoBanco().getConnection();
    }

    public void addJogoAoCarrinho(Carrinho carrinho) {
        String sql = "INSERT INTO carrinho (usuario_id, jogo_idjogo, valorTotal) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            for (int i = 0; i < carrinho.getListaJogos().size(); i++) {
                int usuarioId = carrinho.getUsuario_id();
                int jogoId = carrinho.getListaJogos().get(i);

                // Verificando se jogo já está no carrinho
                if (jogoJaAdicionado(usuarioId, jogoId)) {
                    return;
                }

                stmt.setInt(1, usuarioId);
                stmt.setInt(2, jogoId);
                stmt.setDouble(3, Double.parseDouble(carrinho.getValorTotal().get(i) + ""));
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar jogo ao carrinho: " + e.getMessage(), e);
        }
    }

    public boolean jogoJaAdicionado(int usuarioId, int jogoId) {
        String sql = "SELECT COUNT(*) FROM carrinho WHERE usuario_id = ? AND jogo_idjogo = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, jogoId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar jogo no carrinho: " + e.getMessage(), e);
        }
        return false;
    }

    public void listJogosCarrinho(DefaultTableModel model, int id) {
        String sql = "SELECT u.nome AS usuario, j.nome AS jogo, j.descricao, j.dataLancamento, \n"
                + "j.classificacaoIndicativa, j.imagem, j.preco\n"
                + "FROM usuario u\n"
                + "INNER JOIN carrinho c ON u.id = c.usuario_id\n"
                + "INNER JOIN jogo j ON c.jogo_idjogo = j.idjogo\n"
                + "WHERE c.usuario_id = ?;";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id); // Define o valor do parâmetro

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[7];

                    row[0] = rs.getString("usuario");
                    row[1] = rs.getString("jogo");
                    row[2] = rs.getString("descricao");
                    row[3] = rs.getString("dataLancamento");
                    row[4] = rs.getString("classificacaoIndicativa");
                    row[6] = rs.getDouble("preco");

                    // Converte a imagem do banco para ImageIcon
                    byte[] imgBytes = rs.getBytes("imagem");
                    if (imgBytes != null) {
                        ImageIcon imageIcon = new ImageIcon(imgBytes);
                        row[5] = imageIcon;
                    } else {
                        row[5] = null;
                    }

                    // Adiciona a linha à tabela
                    model.addRow(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll(int id) {
        String checkSql = "SELECT COUNT(*) FROM carrinho WHERE usuario_id = ?";
        String deleteSql = "DELETE FROM carrinho WHERE usuario_id = ?";

        try (PreparedStatement checkStmt = con.prepareStatement(checkSql)) {
            checkStmt.setInt(1, id);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) == 0) {
                    JOptionPane.showMessageDialog(null, "O carrinho já está vazio!");
                    return;
                }
            }

            try (PreparedStatement deleteStmt = con.prepareStatement(deleteSql)) {
                deleteStmt.setInt(1, id);
                int rowsAffected = deleteStmt.executeUpdate();
                //JOptionPane.showMessageDialog(null, rowsAffected + " itens removidos do carrinho com sucesso!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao processar a remoção do carrinho: " + e.getMessage(), e);
        }
    }
}
