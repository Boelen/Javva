/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author MichielAdmin
 */
public class JdbcSQLServerConnection {
     
        Connection conn = null;
    
        public JdbcSQLServerConnection() {
    } 
        
        public void openConnection() {
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
        }   catch (ClassNotFoundException ex) {
                Logger.getLogger(JdbcSQLServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        public void closeConnection() {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex ) {
                ex.printStackTrace();
            }
        }
        
        public Vector GetRowData() {
            
            openConnection();
            ResultSet resultset = null;
            Vector<Vector<Object>> vectorRij = new Vector<Vector<Object>>();
            
            try {
                    Statement stmt = conn.createStatement();
                    String QueryString = "SELECT * FROM dbo.JavaTable";
                    resultset = stmt.executeQuery(QueryString);
                } catch (Exception ex) {
                    System.err.println("Caught IOException: " + ex.getMessage());
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
            return vectorRij;
              }
            catch (SQLException ex) {
                Logger.getLogger(MyData.class.getName()).log(Level.SEVERE, null, ex);
            return null;
            }
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
            String QueryString = "select * from dbo.JavaTable";
            resultset = statement.executeQuery(QueryString);
            
            ResultSetMetaData meta = resultset.getMetaData();
            int columnCount = meta.getColumnCount();
               for (int i=1; i<=columnCount; i++) {
                   vectorKolom.addElement(meta.getColumnName(i));
               }
               return vectorKolom;
             } catch (SQLException ex) {
            Logger.getLogger(MyData.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }   
    }  
    
    public int GetCollumnCount() {
        ResultSet resultset = null;
        int columnCount = 0;
        openConnection();
        
        try {
            Statement statement = conn.createStatement();
            String QueryString = "SELECT * FROM dbo.JavaTable";
            resultset = statement.executeQuery(QueryString);
            ResultSetMetaData metaData = resultset.getMetaData();
            columnCount = metaData.getColumnCount();
        } catch (SQLException ex) {
            Logger.getLogger(JdbcSQLServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return columnCount;
    }
    
    public void InsertData(Vector<Object> gridValues) {
        openConnection();
        String query = "INSERT INTO dbo.JavaTable(A, B, C, D, Foto) values(?, ?, ?, ?, ?)";
        FileInputStream fis = null;
        PreparedStatement ps = null;
        int kolomA, kolomB, kolomC;
        
        try {
        
        for(int i = 0; i < gridValues.size(); i++) {
            Object value = gridValues.elementAt(i);
            if (value instanceof JTextField)
            {
                kolomA = Integer.parseInt(((JTextField)value).getText());
            }
        }   
            
        File file = new File(/*picture*/"C:\\Users\\Michiel\\Documents\\NetBeansProjects\\Javva\\JavaApplication1\\src\\javaapplication1\\SED.jpg");
        fis = new FileInputStream(file);
        ps = conn.prepareStatement(query);
        ps.setInt(1, /*kolomA*/7);
        ps.setInt(2, /*kolomB*/8);
        ps.setInt(3, /*kolomC*/9);
        ps.setBoolean(4, /*checkBox*/true);
        ps.setBinaryStream(5, fis, (int) file.length());
        ps.executeQuery();
        conn.commit();
        } catch(Exception ex) {
           
        } 
    }
}

