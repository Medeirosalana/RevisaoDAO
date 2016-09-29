package br.senac.rn.dao;

import br.senac.rn.model.Produto;
import br.senac.rn.model.Venda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendasDAO {

     private final DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    
    public VendasDAO(){
    db = new DataBase();
    }
    public boolean inset(Venda ven){
     if(db.open()){
            sql = "INSERT INTO venda(id_cliente, valor)VALUES(?,?)";
            try{
                ps = db.connerction.prepareStatement(sql);
                ps.setInt(1, ven.getCliente().getId());
                ps.setFloat(2, ven.getValor());
               
               for(Produto p:ven.getProdutos() ){
               sql ="INSERT INTO produto_venda(id_venda, id_produto) values(?,?)";
                 ps = db.connerction.prepareStatement(sql);
                ps.setInt(1, ven.getId());
                ps.setInt(2, p.getId());
               }
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
    public boolean delete(Venda ven){
     if(db.open()){
        sql = "DELETE FROM venda WHERE id = ?";
            try{
                ps = db.connerction.prepareStatement(sql);
                ps.setInt(1, ven.getId());
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
    public boolean update(Venda ven){
     if(db.open()){
        sql = "UPDATE produto SET id_cliente = ?, valor = ?  WHERE id = ?";
            try{
            ps = db.connerction.prepareStatement(sql);
            ps.setInt(1, ven.getCliente().getId());
            
            ps.setFloat(2, ven.getValor());
            ps.setInt(3, ven.getId());
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
    public List<Venda> selectAll(){
     if(db.open()){            
            List<Venda> vendas = new ArrayList();
            ClienteDAO dao  = new ClienteDAO();
            sql ="SELECT * FROM venda";
            try{
                ps = db.connerction.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                Venda ven = new Venda();
              
                
                
                ven.setId(rs.getInt(1));
                ven.setCliente(dao.selectbyId(rs.getInt(2)));               
                ven.setValor(rs.getFloat(3)); 
               String sql2 = "SELECT * FROM venda_produto where id_venda = ?";
                 ps = db.connerction.prepareStatement(sql2);
                rs = ps.executeQuery();
                
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
