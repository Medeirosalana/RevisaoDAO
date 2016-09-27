package br.senac.rn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBase {

    public Connection connerction = null;
    private final String DRIVE = "com.mysql.jdbc.Driver";
    private final String DATABASE = "revisao_db";
    private final String URL  = "jdbc:mysql://localhost:3306/"+ DATABASE;
    private final String USER = "root";
    private final String PASSWORD = "senac";
    
    public boolean open(){
        try{
            Class.forName(DRIVE);
            connerction = DriverManager.getConnection(URL, USER,PASSWORD);
            System.out.println("CONNECT");
            return true;
        }catch(ClassNotFoundException error){
            System.out.println("DRIVER NOT FOUND");
            System.out.println("ERROR" + error.toString());
        }catch(SQLException error){
            System.out.println("PROBLEMS WITH CONECTION");
            System.out.println("Erro: " + error.toString());
        }
        return false;       
    }
    public boolean close(){
        try{
            connerction.close();
            System.out.println("DISCONNECT");
            return true;
        }catch(SQLException error){
            System.out.println("PROBLEMS WITH DESCONECTION");
            System.out.println("ERRO: "+ error.toString());        
        }return false;
    
    }

}
