/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8;
 
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 * This program demonstrates how to establish database connection to Microsoft
 * SQL Server.
 * @author www.codejava.net
 *
 */
public class JdbcSQLServerConnection {
    
  Connection conn = null;
  
    public JdbcSQLServerConnection() {
    }
    
    
    public void openConnection()
    {
        
        try {
 
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433";
            
            
            String user = "test";
            String pass = "test";
            conn = DriverManager.getConnection(dbURL, user, pass);
            
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }
 
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JdbcSQLServerConnection.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
    public void closeConnection()
    {
        try {
             if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
        } catch (SQLException ex) {
                ex.printStackTrace();
                }
    }
    
    public Vector GetRowData()
    {
        
        openConnection();
        ResultSet resultset = null;
        Vector<Vector<Object>> vectorRij = new Vector<Vector<Object>>();
        
        try
        {
            Statement statement = conn.createStatement();
            String QueryString = "select * from dbo.Table1";
            resultset = statement.executeQuery(QueryString);
            
        }
        catch(Exception e)
        {
          System.err.println("Craught IOException: " + e.getMessage());
          
        }
        
        try {
            
             
            while(resultset.next())
            {
        int ColumnCount = resultset.getMetaData().getColumnCount();
                       Vector<Object> vectorKolom = new Vector<Object>();
                       

                    for (int i=1; i<=ColumnCount; i++)
                    {
                        String columnType = resultset.getMetaData().getColumnTypeName(i);
                        
                        switch(columnType)
                        {
                            case "int" : 
                                vectorKolom.addElement(resultset.getInt(i));
                                break;
                            case "string" : 
                                //Object celInhoud = resultset.getString(i);
                                vectorKolom.addElement(resultset.getString(i));
                                break;
                             case "bit" : 
                                //Object cellInhoud = resultset.getInt(i);
                                vectorKolom.addElement(resultset.getBoolean(i));
                                break;
                            case "image" : 
                                //Object cellInhoud = resultset.getInt(i);
                                Foto foto = new Foto(resultset.getBlob(i));
                                vectorKolom.addElement(foto);
                                break;  
                                 
                        
                    }
                    }
                    vectorRij.addElement(vectorKolom);
            }
              }
            catch (SQLException ex) {
            Logger.getLogger(MyData.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
            closeConnection();
            return vectorRij;
    }
    
    public Vector GetCollumnNameData()
    {
        ResultSet resultset = null;
        Object CollumnData = null;
        Vector<String> vectorKolom = new Vector<String>();
        
        openConnection();
                
        try
        {
            Statement statement = conn.createStatement();
            String QueryString = "select * from dbo.Table1";
            resultset = statement.executeQuery(QueryString);
            
            ResultSetMetaData meta = resultset.getMetaData();
            int columnCount = meta.getColumnCount();
               
               for (int i=1; i<=columnCount; i++) {
                   vectorKolom.addElement(meta.getColumnName(i));
               }
               
             } catch (SQLException ex) {
            Logger.getLogger(MyData.class.getName()).log(Level.SEVERE, null, ex);
        }
          closeConnection();
          return vectorKolom;
    
    }
    
    public int GetCollumnCount()
    {
            ResultSet resultset = null;
            int columnCount = 0;
            openConnection();
            
       try {
            Statement statement = conn.createStatement();
            String QueryString = "select * from dbo.Table1";
            resultset = statement.executeQuery(QueryString);
            ResultSetMetaData meta = resultset.getMetaData();
            columnCount = meta.getColumnCount();
            
      } catch (SQLException ex) {
          Logger.getLogger(JdbcSQLServerConnection.class.getName()).log(Level.SEVERE, null, ex);
      }
       closeConnection();
       return columnCount;
    } 
}
    
   