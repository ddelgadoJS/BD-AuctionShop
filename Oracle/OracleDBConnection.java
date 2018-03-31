import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.util.concurrent.Callable;
import java.util.ArrayList;
import datos.Conexion;

// Create connection
ArrayList<String> rowsList = new ArrayList<>(); // List to store the rows from the query.
        
Conexion con = new Conexion();
rowsList = con.Conectar("SP_LISTAR_SUBASTAS(CATEGORIAv=>1, SUBCATEGORIAv=>150)");

for (String row: rowsList) {
    System.out.println(row);
}

// Body
try{  
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    System.out.println("Conectando con la base de datos...");
    Connection con = DriverManager.getConnection(
    "jdbc:oracle:thin:@localhost:1521:orcl", "SYS AS SYSDBA", "12345");
    //"jdbc:oracle:thin:@localhost:1521:orcl", "C##_ibolzen2g", "Tvh3D8ct5");
    
    CallableStatement cstmt = con.prepareCall("{call dbms_output.enable(32000)}");
    cstmt.execute();
    
    Statement stmt = con.createStatement();
    String query = "{call SP_LISTAR_SUBASTAS(CATEGORIAv=>1, SUBCATEGORIAv=>150)}";
    stmt.execute(query);        
    
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
            System.out.println(line);
        }
    }

}catch(Exception e){
    System.out.println("The exception raised is:" + e);
}

// Method body
public ArrayList Conectar(String sp){
    ArrayList<String> rowsList = new ArrayList<>(); // List to store the rows from the query.
    try{  
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        System.out.println("Conectando con la base de datos...");
        Connection con = DriverManager.getConnection(
        "jdbc:oracle:thin:@localhost:1521:orcl", "SYS AS SYSDBA", "12345");
        //"jdbc:oracle:thin:@localhost:1521:orcl", "C##_ibolzen2g", "Tvh3D8ct5");

        CallableStatement cstmt = con.prepareCall("{call dbms_output.enable(32000)}");
        cstmt.execute();

        Statement stmt = con.createStatement();
        String query = "{call " + sp +"}";
        stmt.execute(query);        

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