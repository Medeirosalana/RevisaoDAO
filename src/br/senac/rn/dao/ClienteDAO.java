package br.senac.rn.dao;

import br.senac.rn.model.Cliente;
import br.senac.rn.model.Sexo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    
     private final DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    
    public ClienteDAO(){
    db = new DataBase();
    }
    public boolean inset(Cliente cli){
     if(db.open()){
            sql = "INSERT INTO cliente(nome, cpf, id_sexo)VALUES(?,?,?)";
            try{
                ps = db.connerction.prepareStatement(sql);
                ps.setString(1, cli.getNome());
                ps.setString(2, cli.getCpf());
                ps.setInt(3, cli.getSexo().getId());
                if(ps.executeUpdate() == 1){
                    ps.close();
                    db.close();
                    return true;
                }
            }catch(SQLException error){
                System.out.println("ERROR: " + error.toString());
            }
        }
        db.close();
        return false;
    }
    public boolean delete(Cliente cli){
     if(db.open()){
        sql = "DELETE FROM cliente WHERE id = ?";
            try{
                ps = db.connerction.prepareStatement(sql);
                ps.setInt(1, cli.getId());
                if(ps.executeUpdate() == 1){
                ps.close();
                db.close();
                return true;
                }
            }catch(SQLException error){
                System.out.println("ERROR: " + error.toString());
            }
        }
        db.close();
        return false;
         
    }
    public boolean update(Cliente cli){
     if(db.open()){
        sql = "UPDATE cliente SET nome = ?, cpf = ?, id_sexo = ?  WHERE id = ?";
            try{
            ps = db.connerction.prepareStatement(sql);
            ps.setString(1, cli.getNome());
            ps.setString(2, cli.getCpf());
            ps.setInt(3, cli.getSexo().getId());
            ps.setInt(4, cli.getId());
            if(ps.executeUpdate() == 1){
            ps.close();
            db.close();
                
            return true;
            }            
            }catch(SQLException error){
                System.out.println("ERROR: " + error.toString());
            }        
        }
     db.close();
        return false;
        
    }
    public List<Cliente> selectAll(){
     if(db.open()){            
            List<Cliente> clientes = new ArrayList();
            
            sql ="SELECT * FROM cliente";
            try{
                ps = db.connerction.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                Cliente cli = new Cliente();
                SexoDAO dao = new SexoDAO();
                
                cli.setId(rs.getInt(1));
                cli.setNome(rs.getString(2));
                cli.setCpf(rs.getString(3)); 
                cli.setSexo(dao.selectbyId(rs.getInt(4)));
                clientes.add(cli);
                }
                rs.close();
                ps.close();
                db.close();
                return clientes;
            }catch(SQLException error){
             System.out.println("ERROR: " + error.toString());
            }
        }
        db.close();               
        return null;
    }
    public Cliente selectbyId(int id){
    if(db.open()){
            Cliente cli = new Cliente();
            SexoDAO dao = new SexoDAO();
            sql ="SELECT * FROM cliente WHERE id = ?";
            try{
                ps = db.connerction.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if(rs.next()){
                 cli.setId(rs.getInt(1));
                cli.setNome(rs.getString(2));
                cli.setCpf(rs.getString(3));
                cli.setSexo(dao.selectbyId(rs.getInt(4)));
                rs.close();
                ps.close();
                db.close();
                return cli;
                }
            }catch(SQLException error){
             System.out.println("ERROR: " + error.toString());
            }
        }
        db.close();
        return null;
    }
    public List<Cliente> selectbyFilter(String filter){
    if(db.open()){            
            List<Cliente> clientes = new ArrayList();
            String filtro = "%" + filter + "%";
            sql ="SELECT * FROM cliente WHERE nome LIKE ? OR id_sexo LIKE ?";            
            try{
                ps = db.connerction.prepareStatement(sql);
                ps.setString(1, filtro);
                ps.setString(2, filtro);
                rs = ps.executeQuery();
                while(rs.next()){
                Cliente cli = new Cliente();
                SexoDAO dao = new SexoDAO();
                cli.setId(rs.getInt(1));
                cli.setNome(rs.getString(2));
                cli.setCpf(rs.getString(3));
                cli.setSexo(dao.selectbyId(rs.getInt(4)));
                clientes.add(cli);
                }
                rs.close();
                ps.close();
                db.close();
                return clientes;
            }catch(SQLException error){
             System.out.println("ERROR: " + error.toString());
            }
        }
        db.close();
        return null; 
    }
}
