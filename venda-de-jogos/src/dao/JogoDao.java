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

    public void cadJogo(Jogo jogo) throws SQLException {
        String sql = "INSERT INTO jogo (nome, descricao, preco, dataLancamento, classificacaoIndicativa, imagem) values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, jogo.getNome());
            ps.setString(2, jogo.getDescricao());
            ps.setDouble(3, jogo.getPreco());
            ps.setString(4, jogo.getDataLancamento());
            ps.setString(5, jogo.getClassificacaoIndicativa());
            ps.setBytes(6, jogo.getImagem());

            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Jogo Cadastrado com Sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o jogo: " + e.getMessage());
        }
    }

    public void listJogos(DefaultTableModel model) {
        String sql = "SELECT * FROM jogo";

        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            //model.setRowCount(0);

            while (rs.next()) {
                Object[] row = new Object[6];

                row[0] = rs.getString("nome");
                row[1] = rs.getString("descricao");
                row[2] = rs.getDouble("preco");
                row[3] = rs.getString("dataLancamento");
                row[4] = rs.getString("classificacaoIndicativa");

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
