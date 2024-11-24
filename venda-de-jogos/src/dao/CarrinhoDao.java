package dao;

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
                stmt.setInt(1, carrinho.getUsuario_id());
                stmt.setInt(2, carrinho.getListaJogos().get(i));
                stmt.setDouble(3, Double.parseDouble(carrinho.getValorTotal().get(i) + ""));
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Jogo adicionada com Sucesso!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar jogo ao carrinho: " + e.getMessage(), e);
        }
    }

    public void listJogosCarrinho(DefaultTableModel model, int id) {
        String sql = "SELECT u.nome AS usuario, j.nome AS jogo, j.descricao, j.dataLancamento, \n" +
                                             "j.classificacaoIndicativa, j.imagem, j.preco\n" +
                    "FROM usuario u\n" +
                    "INNER JOIN carrinho c ON u.id = c.usuario_id\n" +
                    "INNER JOIN jogo j ON c.jogo_idjogo = j.idjogo\n" +
                    "WHERE c.usuario_id = ?;";
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
}
