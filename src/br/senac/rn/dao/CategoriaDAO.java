package br.senac.rn.dao;

import br.senac.rn.model.Categoria;
import br.senac.rn.model.Sexo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    
     private final DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    
    public CategoriaDAO(){
    db = new DataBase();
    }
    public boolean inset(Categoria cat){
     if(db.open()){
            sql = "INSERT INTO categoria(nome)VALUES(?)";
            try{
                ps = db.connerction.prepareStatement(sql);
                ps.setString(1, cat.getNome());
                   
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
    public boolean delete(Categoria cat){
     if(db.open()){
        sql = "DELETE FROM categoria WHERE id = ?";
            try{
                ps = db.connerction.prepareStatement(sql);
                ps.setInt(1, cat.getId());
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
    public boolean update(Categoria cat){
     if(db.open()){
        sql = "UPDATE categoria SET nome = ?  WHERE id = ?";
            try{
            ps = db.connerction.prepareStatement(sql);
            ps.setString(1, cat.getNome());
            ps.setInt(2, cat.getId());
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
    public List<Categoria> selectAll(){
     if(db.open()){            
            List<Categoria> categorias = new ArrayList();
            
            sql ="SELECT * FROM categoria";
            try{
                ps = db.connerction.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                Categoria cat = new Categoria();
                
                
                cat.setId(rs.getInt(1));
                cat.setNome(rs.getString(2));
                            
                categorias.add(cat);
                }
                rs.close();
                ps.close();
                db.close();
                return categorias;
            }catch(SQLException error){
             System.out.println("ERROR: " + error.toString());
            }
        }
        db.close();               
        return null;
    }
    public Categoria selectbyId(int id){
    if(db.open()){
            Categoria cat = new Categoria();
            sql ="SELECT * FROM categoria WHERE id = ?";
            try{
                ps = db.connerction.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if(rs.next()){
                 cat.setId(rs.getInt(1));
                cat.setNome(rs.getString(2));
               
                rs.close();
                ps.close();
                db.close();
                return cat;
                }
            }catch(SQLException error){
             System.out.println("ERROR: " + error.toString());
            }
        }
        db.close();
        return null;
    }
    public List<Categoria> selectbyFilter(String filter){
    if(db.open()){            
            List<Categoria> categorias = new ArrayList();
            String filtro = "%" + filter + "%";
            sql ="SELECT * FROM categoria WHERE nome LIKE ?";            
            try{
                ps = db.connerction.prepareStatement(sql);
                ps.setString(1, filtro);
                ps.setString(2, filtro);
                rs = ps.executeQuery();
                while(rs.next()){
                Categoria cat = new Categoria();
                cat.setId(rs.getInt(1));
                cat.setNome(rs.getString(2));
                categorias.add(cat);
                }
                rs.close();
                ps.close();
                db.close();
                return categorias;
            }catch(SQLException error){
             System.out.println("ERROR: " + error.toString());
            }
        }
        db.close();
        return null; 
    }
}
