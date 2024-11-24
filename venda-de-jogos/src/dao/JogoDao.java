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

    public Jogo buscarJogoPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM jogo WHERE nome = ?";
        Jogo jogo = null;

        try (Connection conn = new ConexaoBanco().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    jogo = new Jogo();
                    jogo.setIdJogo(rs.getInt("idjogo"));
                    jogo.setNome(rs.getString("nome"));
                    jogo.setDescricao(rs.getString("descricao"));
                    jogo.setPreco(rs.getDouble("preco"));
                    jogo.setDataLancamento(rs.getString("datalancamento"));
                    jogo.setClassificacaoIndicativa(rs.getString("classificacaoindicativa"));
                    jogo.setImagem(rs.getBytes("imagem"));
                }
            }
        }

        return jogo;
    }
}
