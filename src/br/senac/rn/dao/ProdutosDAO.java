package br.senac.rn.dao;

import br.senac.rn.model.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

     private final DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    
    public ProdutosDAO(){
    db = new DataBase();
    }
    public boolean inset(Produto pro){
     if(db.open()){
            sql = "INSERT INTO produto(nome, valor, id_categoria)VALUES(?,?,?)";
            try{
                ps = db.connerction.prepareStatement(sql);
                ps.setString(1, pro.getNome());
                ps.setFloat(2, pro.getValor());
                ps.setInt(3, pro.getCategoria().getId());
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
    public boolean delete(Produto pro){
     if(db.open()){
        sql = "DELETE FROM produto WHERE id = ?";
            try{
                ps = db.connerction.prepareStatement(sql);
                ps.setInt(1, pro.getId());
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
    public boolean update(Produto pro){
     if(db.open()){
        sql = "UPDATE produto SET nome = ?, id_categoria = ?, valor = ?  WHERE id = ?";
            try{
            ps = db.connerction.prepareStatement(sql);
            ps.setString(1, pro.getNome());
            ps.setInt(2, pro.getCategoria().getId());
            ps.setFloat(3, pro.getValor());
            ps.setInt(4, pro.getId());
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
    public List<Produto> selectAll(){
     if(db.open()){            
            List<Produto> produtos = new ArrayList();
            
            sql ="SELECT * FROM produto";
            try{
                ps = db.connerction.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                Produto pro = new Produto();
                CategoriaDAO dao = new CategoriaDAO();
                
                
                pro.setId(rs.getInt(1));
                pro.setNome(rs.getString(2));
                pro.setValor(rs.getFloat(3)); 
                pro.setCategoria(dao.selectbyId(rs.getInt(4)));
                produtos.add(pro);
                }
                rs.close();
                ps.close();
                db.close();
                return produtos;
            }catch(SQLException error){
             System.out.println("ERROR: " + error.toString());
            }
        }
        db.close();               
        return null;
    }
    public Produto selectbyId(int id){
    if(db.open()){
            Produto p = new Produto();
            CategoriaDAO dao = new CategoriaDAO();
            sql ="SELECT * FROM produto WHERE id = ?";
            try{
                ps = db.connerction.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if(rs.next()){
                 p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setValor(rs.getFloat(3));
                p.setCategoria(dao.selectbyId(rs.getInt(4)));
                rs.close();
                ps.close();
                db.close();
                return p;
                }
            }catch(SQLException error){
             System.out.println("ERROR: " + error.toString());
            }
        }
        db.close();
        return null;
    }
    public List<Produto> selectbyFilter(String filter){
    if(db.open()){            
            List<Produto> produtos = new ArrayList();
            String filtro = "%" + filter + "%";
            sql ="SELECT * FROM produto WHERE nome LIKE ?";            
            try{
                ps = db.connerction.prepareStatement(sql);
                ps.setString(1, filtro);
                ps.setString(2, filtro);
                rs = ps.executeQuery();
                while(rs.next()){
                Produto p = new Produto();
                CategoriaDAO dao = new CategoriaDAO();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setValor(rs.getFloat(3));
                p.setCategoria(dao.selectbyId(rs.getInt(4)));
                produtos.add(p);
                }
                rs.close();
                ps.close();
                db.close();
                return produtos;
            }catch(SQLException error){
             System.out.println("ERROR: " + error.toString());
            }
        }
        db.close();
        return null;
    }
}
