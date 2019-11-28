package br.com.interagese.interfiscal.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConexao {

    public static void main(String[] args) throws SQLException {

//        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/integrado3", "postgres", "qwe123");
//        
//        System.out.println(connection.getMetaData().getDriverName());
        try (PrintWriter writer = new PrintWriter(new File("C:\\Users\\Bruno Martins\\Desktop\\test.csv"))) {

            StringBuilder sb = new StringBuilder();
            sb.append("id;Name\r\n1;Prashant Ghimire");
            
            writer.write(sb.toString());

            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

}
