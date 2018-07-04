
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
   //private Connection conexao;
    
    public static Connection getConexao() throws SQLException{
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/pessoa","postgres","12345678");
        
    }
    
    
}
