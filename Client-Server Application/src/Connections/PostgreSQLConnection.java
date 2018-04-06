/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connections;

import Oracle.OraclePantallaInicial;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.util.concurrent.Callable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class PostgreSQLConnection {
    public ArrayList FunctionReturningInt(String query, String columnName) {
        Connection con = this.CrearConexion("postgres", "1234");
        
        ArrayList<String> rowsList = new ArrayList<>(); // List to store the rows from the query.
        
        try{
            java.sql.Statement st = con.createStatement();
            ResultSet result = st.executeQuery(query);
            
            while(result.next()){
                String desc = result.getString(columnName);
                rowsList.add(desc);
            }
            result.close();
            st.close();
            con.close();
        } catch (Exception e){
            System.out.println("Problemas de Conexion: " + e.getMessage());
        }
        
        return rowsList;
    }
    
    public ArrayList FunctionReturningString(String query, String columnName) {
        Connection con = this.CrearConexion("postgres", "1234");
        
        ArrayList<String> rowsList = new ArrayList<>(); // List to store the rows from the query.
        
        try{
            java.sql.Statement st = con.createStatement();
            ResultSet result = st.executeQuery(query);
            
            while(result.next()){
                String desc = result.getString(columnName);
                desc = desc.replace("(", "");
                desc = desc.replace(")", "");
                rowsList.add(desc);
            }
            result.close();
            st.close();
            con.close();
        } catch (Exception e){
            System.out.println("Problemas de Conexion: " + e.getMessage());
        }
        
        return rowsList;
    }
    
    public Connection CrearConexion(String user, String pass){
        String url = "jdbc:postgresql://localhost:5432/test01";

        try{  
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, pass); //cadena,usuario,contraseña
            
            if(con!= null){
                System.out.println("Conectando a la base de datos...");
            }
            
            return con;
            
        }catch(Exception e){
            System.out.println("The exception raised is:" + e);
        }
        return null;
    }
    
    public static ArrayList Conexion(String query){
        ArrayList<String> rowsList = new ArrayList<>(); // List to store the rows from the query.
        
        String url = "jdbc:postgresql://localhost:5432/test01";
        String password = "1234";
        try{
            Class.forName("org.postgresql.Driver");
            
            Connection con = DriverManager.getConnection(url, "postgres", password); //cadena,usuario,contraseña
            
            if(con!= null){
                System.out.println("Conectando a la base de datos...");
            }
            
            Statement st = con.createStatement();
            //ResultSet result = st.executeQuery("SELECT * FROM \"CATEGORIA\"");
            ResultSet result = st.executeQuery(query);
            
            // This is when returning a table
            while(result.next()){
                String desc = result.getString("ft_login");
                System.out.println(desc);
            }
            
            result.close();
            st.close();
            con.close();
        } catch (Exception e){
            System.out.println("Problemas de Conexion: " + e.getMessage());
        }
        
        return rowsList;
    }
    
    public void CerrarConexion(Connection con) throws SQLException {
        con.close();
    }
}
