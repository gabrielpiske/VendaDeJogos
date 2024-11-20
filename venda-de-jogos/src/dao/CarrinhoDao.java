package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Carrinho;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
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

    public void cadCarrinho(Carrinho carrinho) throws SQLException {
        String sqlCarrinho = "INSERT INTO carrinho (valorTotal) VALUES (?)";
        String sqlCarrinhoJogo = "INSERT INTO carrinho_jogo (idcarrinho, idjogo) VALUES (?, ?)";

        try {
            con.setAutoCommit(false);

            try (PreparedStatement psCarrinho = con.prepareStatement(sqlCarrinho, Statement.RETURN_GENERATED_KEYS)) {
                psCarrinho.setDouble(1, carrinho.getValorTotal());
                psCarrinho.executeUpdate();

                // Recupera o id do carrinho recém-criado
                try (ResultSet generatedKeys = psCarrinho.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idCarrinho = generatedKeys.getInt(1);

                        // Inserção dos jogos associados ao carrinho
                        try (PreparedStatement psCarrinhoJogo = con.prepareStatement(sqlCarrinhoJogo)) {
                            for (Jogo jogo : carrinho.getListaJogos()) {
                                psCarrinhoJogo.setInt(1, idCarrinho);
                                psCarrinhoJogo.setInt(2, jogo.getIdJogo());
                                psCarrinhoJogo.executeUpdate();
                            }
                        }
                    } else {
                        throw new SQLException("Erro ao obter o ID do carrinho.");
                    }
                }
            }

            con.commit();  // Confirma a transação
            JOptionPane.showMessageDialog(null, "Carrinho cadastrado com sucesso");
        } catch (SQLException e) {
            con.rollback();  // Desfaz a transação em caso de erro
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o carrinho: " + e.getMessage());
            System.out.println("erro: " + e.getMessage());
        }
    }

    public Carrinho buscarCarrinho(int idCarrinho) {
        String sqlCarrinho = "SELECT * FROM carrinho WHERE idcarrinho = ?";
        String sqlJogos = "SELECT j.* FROM jogo j INNER JOIN carrinho_jogo cj ON j.idjogo = cj.idjogo WHERE cj.idcarrinho = ?";

        try {
            Carrinho carrinho = null;

            // Busca os dados do carrinho
            try (PreparedStatement psCarrinho = con.prepareStatement(sqlCarrinho)) {
                psCarrinho.setInt(1, idCarrinho);
                try (ResultSet rsCarrinho = psCarrinho.executeQuery()) {
                    if (rsCarrinho.next()) {
                        carrinho = new Carrinho();
                        carrinho.setValorTotal(rsCarrinho.getDouble("valorTotal"));
                    }
                }
            }

            if (carrinho != null) {

                try (PreparedStatement psJogos = con.prepareStatement(sqlJogos)) {
                    psJogos.setInt(1, idCarrinho);
                    try (ResultSet rsJogos = psJogos.executeQuery()) {
                        while (rsJogos.next()) {
                            Jogo jogo = new Jogo();
                            jogo.setIdJogo(rsJogos.getInt("idjogo"));
                            jogo.setNome(rsJogos.getString("nome"));
                            jogo.setDescricao(rsJogos.getString("descricao"));
                            jogo.setPreco(rsJogos.getDouble("preco"));
                            jogo.setDataLancamento(rsJogos.getString("dataLancamento"));
                            jogo.setClassificacaoIndicativa(rsJogos.getString("classificacaoIndicativa"));
                            jogo.setImagem(rsJogos.getBytes("imagem"));
                            carrinho.addJogo(jogo);  // Adiciona o jogo à lista
                        }
                    }
                }
            }

            return carrinho;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o carrinho: " + e.getMessage());
            return null;
        }
    }
}
