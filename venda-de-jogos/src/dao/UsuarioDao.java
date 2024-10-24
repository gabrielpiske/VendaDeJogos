package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;

/**
 *
 * @author gabriel_piske
 */
public class UsuarioDao {
    
    private Connection con;
    ResultSet rs;
    PreparedStatement pstm;

    public UsuarioDao() {
        this.con = new ConexaoBanco().getConnection();
    }
    
    public Usuario login(String nome, String senha) {
        Usuario usuario = null;
        String sql = "SELECT * FROM login WHERE nome_usuario = ? AND senha_usuario = ?";

        try (Connection connection = new ConexaoBanco().getConnection(); PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, nome);
            stm.setString(2, senha);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setNome(rs.getString("nome_usuario"));
                    usuario.setSenha(rs.getString("senha_usuario"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
}
