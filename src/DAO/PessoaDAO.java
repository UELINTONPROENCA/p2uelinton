package DAO;

import DAO.Conexao;
import Pessoas.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



public class PessoaDAO implements IDAO{
   
    public void cadastrar(Object o){
        Pessoa p = (Pessoa) o;
        
        try {
                  Connection conn = Conexao.getConexao();
                  String sql = "insert into pessoa( id,numero,marca, pessoaid) values (?, ?, ?, ?)";
                  
                  PreparedStatement stmt = conn.prepareStatement(sql);
                  
                  stmt.setLong(1, p.getId());
                  stmt.setString(2, p.getNumero());
                  stmt.setString(3, p.getMarca());
                  stmt.setString(4, p.getPessoaId());
                  
                  stmt.executeUpdate();
                  stmt.close();
                  conn.close();

        
        } catch (SQLException e) {
            e.printStackTrace();
        }
  
    
    }
        public void alterar (Object o){
        Pessoa p = (Pessoa) o;
        
        try {
                  Connection conn = Conexao.getConexao();
                  String sql = "update id set "
                          + "numero = ?, "
                          + "marca = ?, "
                          + " where pessoaid = ?";
                                                     
                  PreparedStatement stmt = conn.prepareStatement(sql);
                  
                  stmt.setLong(1, p.getId());
                  stmt.setString(2, p.getNumero());
                  stmt.setString(3, p.getMarca());
                  stmt.setString(4, p.getPessoaId());
                  
                  stmt.executeUpdate();
                  stmt.close();
                  conn.close();

        
        } catch (SQLException e) {
            e.printStackTrace();
        }
  
    
    }

    public ArrayList listarPessoa() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ArrayList lista  = new ArrayList();
        try {
            Connection conn = Conexao.getConexao();
            String sql = "Select numero from pessoa where pessoaid = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
           // stmt.setInt(1, );
            
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      return lista;  
    }
    
    
    


    
    
}
