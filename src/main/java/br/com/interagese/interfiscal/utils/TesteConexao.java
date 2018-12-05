package br.com.interagese.interfiscal.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConexao {
    
    public static void main(String[] args) throws SQLException {
        
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/integrado3", "postgres", "qwe123");
        
        System.out.println(connection.getMetaData().getDriverName());
        
    }
    
}
