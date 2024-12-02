/*
    Classe que conecta com o banco de dados
 */

package connection;

import javax.swing.JOptionPane;
import java.sql.*;

/**
 *
 * @author gabriel_piske
 */
public class ConexaoBanco {
    public Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vendajogos", "root", "12345");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na conexao com banco: " + e.getMessage());
            return null;
        }
    }
}