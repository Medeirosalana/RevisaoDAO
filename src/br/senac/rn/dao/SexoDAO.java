package br.senac.rn.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SexoDAO {

     private final DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    
    public SexoDAO(){
    db = new DataBase();
    }
    
}
