/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Connections.OracleConnection;
import Connections.PostgreSQLConnection;
import Oracle.OraclePantallaInicial;
import PostgreSQL.PostgreSQLPantallaInicial;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Kevin MM
 */
public class SistemaDeSubastas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DatabaseSelection frame = new DatabaseSelection();
        frame.setVisible(true); //or whatever the method is in jframe.class
        /*
        ArrayList<String> rowsList = new ArrayList<>(); // List to store the rows from the query.
        
        // Update PostgreSQL system parameters
        PostgreSQLConnection PostqreSQLcon_ = new PostgreSQLConnection();
        String columnName = "ft_select_parametros"; // Name of the returned column.
        
        rowsList = PostqreSQLcon_.FunctionReturningString("SELECT FT_SELECT_PARAMETROS()", columnName);
        
        String[] parts = rowsList.get(0).split(",");
        
        PostgreSQLPantallaInicial.incrementominimo = parts[0];
        PostgreSQLPantallaInicial.porcentajemejora = parts[2];*/
    }
    
}
