package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar jogo ao carrinho: " + e.getMessage(), e);
        }
    }

    public int criarNovoCarrinho() {
        // Inserir um novo carrinho sem especificar o idCarrinho, que será gerado automaticamente
        String sql = "INSERT INTO carrinho () VALUES ()"; // A coluna idCarrinho será gerada automaticamente
        try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Retorna o ID gerado automaticamente
                } else {
                    throw new SQLException("Erro ao obter ID do carrinho.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar novo carrinho: " + e.getMessage(), e);
        }
    }

    public List<Jogo> listarJogosNoCarrinho(int idCarrinho) {
        String sql = "SELECT j.* FROM carrinho_jogo cj "
                + "JOIN jogo j ON cj.idjogo = j.idjogo "
                + "WHERE cj.idcarrinho = ?";
        List<Jogo> jogos = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idCarrinho);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Jogo jogo = new Jogo();
                    jogo.setIdJogo(rs.getInt("idjogo"));
                    jogo.setNome(rs.getString("nome"));
                    jogo.setDescricao(rs.getString("descricao"));
                    jogo.setPreco(rs.getDouble("preco"));
                    jogo.setDataLancamento(rs.getString("datalancamento"));
                    jogo.setClassificacaoIndicativa(rs.getString("classificacaoindicativa"));
                    jogo.setImagem(rs.getBytes("imagem"));
                    jogos.add(jogo);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar jogos no carrinho: " + e.getMessage(), e);
        }

        return jogos;
    }
}
