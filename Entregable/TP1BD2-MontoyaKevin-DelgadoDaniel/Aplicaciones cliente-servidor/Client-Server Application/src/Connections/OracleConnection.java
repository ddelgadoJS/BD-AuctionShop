/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connections;

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

/**
 *
 * @author Daniel
 */
public class OracleConnection {
    public ArrayList EjecutarSP(String query, Connection con) {
        ArrayList<String> rowsList = new ArrayList<>(); // List to store the rows from the query.
        try{  
            CallableStatement cstmt = con.prepareCall("{call dbms_output.enable(32000)}");
            cstmt.execute();

            Statement stmt = con.createStatement();
            String query_ = "{call " + query +"}";
            stmt.execute(query_);        

            cstmt = con.prepareCall("{call dbms_output.get_line(?,?)}");
            cstmt.registerOutParameter(1,java.sql.Types.VARCHAR);
            cstmt.registerOutParameter(2,java.sql.Types.INTEGER);

            int status = 0;
            while (status == 0)
            {
                cstmt.execute();
                String line = cstmt.getString(1);
                status = cstmt.getInt(2);
                if (line != null && status == 0)
                {
                    rowsList.add(line);
                }
            }

        }catch(Exception e){
            System.out.println("The exception raised is:" + e);
        }
        return rowsList;
    }
    
    public Connection CrearConexion(){
        try{  
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("Conectando con la base de datos...");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "SYS AS SYSDBA", "12345");
            //"jdbc:oracle:thin:@localhost:1521:orcl", "C##_ibolzen2g", "Tvh3D8ct5");
            return con;
            
        }catch(Exception e){
            System.out.println("The exception raised is:" + e);
        }
        return null;
    }
    
    public void CerrarConexion(Connection con) throws SQLException {
        con.close();
    }
}
