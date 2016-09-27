package br.senac.rn.dao;

import br.senac.rn.model.Sexo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class SexoDAO {

     private final DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    
    public SexoDAO(){
    db = new DataBase();
    }
    public boolean inset(Sexo sexo){}
    public boolean delete(Sexo sexo){}
    public boolean update(Sexo sexo){}
    public List<Sexo> selectAll(){}
    public Sexo selectbyId(int id){}
    public Sexo selectbyFilter(String filter){}
}
