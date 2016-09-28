package br.senac.rn.dao;

import br.senac.rn.model.Sexo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SexoDAO {

     private final DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    
    public SexoDAO(){
    db = new DataBase();
    }
    public boolean inset(Sexo sexo){
     if(db.open()){
            sql = "INSERT INTO tb_sexo(nome, sigla)VALUES(?,?)";
            try{
                ps = db.connerction.prepareStatement(sql);
                ps.setString(1, sexo.getNome());
                ps.setString(2, sexo.getSigla());               
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
    public boolean delete(Sexo sexo){
     if(db.open()){
        sql = "DELETE FROM tb_sexo WHERE id = ?";
            try{
                ps = db.connerction.prepareStatement(sql);
                ps.setInt(1, sexo.getId());
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
    public boolean update(Sexo sexo){
     if(db.open()){
        sql = "UPDATE tb_sexo SET nome = ?, sigla = ?  WHERE id = ?";
            try{
            ps = db.connerction.prepareStatement(sql);
            ps.setString(1, sexo.getNome());
            ps.setString(2, sexo.getSigla());                           
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
    public List<Sexo> selectAll(){
     if(db.open()){            
            List<Sexo> sexos = new ArrayList();
            
            sql ="SELECT * FROM tb_sexo";
            try{
                ps = db.connerction.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                Sexo sexo = new Sexo();
                
                
                sexo.setId(rs.getInt(1));
                sexo.setNome(rs.getString(2));
                sexo.setSigla(rs.getString(3));               
                sexos.add(sexo);
                }
                rs.close();
                ps.close();
                db.close();
                return sexos;
            }catch(SQLException error){
             System.out.println("ERROR: " + error.toString());
            }
        }
        db.close();               
        return null;
    }
    public Sexo selectbyId(int id){
    if(db.open()){
            Sexo sexo = new Sexo();
            sql ="SELECT * FROM tb_sexo WHERE id = ?";
            try{
                ps = db.connerction.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if(rs.next()){
                 sexo.setId(rs.getInt(1));
                sexo.setNome(rs.getString(2));
                sexo.setSigla(rs.getString(3));
                rs.close();
                ps.close();
                db.close();
                return sexo;
                }
            }catch(SQLException error){
             System.out.println("ERROR: " + error.toString());
            }
        }
        db.close();
        return null;
    }
    public List<Sexo> selectbyFilter(String filter){
    if(db.open()){            
            List<Sexo> sexos = new ArrayList();
            String filtro = "%" + filter + "%";
            sql ="SELECT * FROM tb_sexo WHERE nome LIKE ? OR sigla LIKE ?";            
            try{
                ps = db.connerction.prepareStatement(sql);
                ps.setString(1, filtro);
                ps.setString(2, filtro);
                rs = ps.executeQuery();
                while(rs.next()){
                Sexo sexo = new Sexo();               
                sexo.setNome(rs.getString(1));
                sexo.setSigla(rs.getString(2));
              
                sexos.add(sexo);
                }
                rs.close();
                ps.close();
                db.close();
                return sexos;
            }catch(SQLException error){
             System.out.println("ERROR: " + error.toString());
            }
        }
        db.close();
        return null; 
    }
}
