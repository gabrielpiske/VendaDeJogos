package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
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
        String sql = "SELECT * FROM usuario WHERE nome = ? AND senha = ?";

        try (Connection connection = new ConexaoBanco().getConnection(); PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, nome);
            stm.setString(2, senha);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setNome(rs.getString("nome"));
                    usuario.setSenha(rs.getString("senha"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
    
    public void cadUsuario(Usuario usuario){
        String sql = "INSERT INTO usuario (nome, senha) values (?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());

            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Usuario Cadastrado!");
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Erro: " + e);
        }
    }
    
    public void alterarUsuario(int codigo, String password){
        String sql = "UPDATE usuario SET senha = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, password);
            ps.setInt(2, codigo);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }
}
