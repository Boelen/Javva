/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
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
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
        
        FileInputStream fis = null;
        PreparedStatement ps = null;
        int getal;
        boolean bool;
        String query;
        
        try
        {
        query = "INSERT INTO dbo.JavaTable(";
        String QueryString = "SELECT * FROM dbo.JavaTable";
        Statement stmt = conn.createStatement();
         
        ResultSet resultset = stmt.executeQuery(QueryString);
         ResultSetMetaData meta = resultset.getMetaData();
         
          query += " " + meta.getColumnName(2);    
            for(int i = 3; i < (meta.getColumnCount() + 1);i++){                
                 query += ", " + meta.getColumnName(i) ;                         
            }
            
            query += " ) VALUES (?";
            
            for(int i = 1; i < (meta.getColumnCount() -1); i++){
                query += ", ?";                                                   
            }
            
            query += " )";
        
                 
         
        ps = conn.prepareStatement(query);
        
        for(int i = 0; i < gridValues.size() - 1; i++) {
            Object value = gridValues.elementAt(i+1);
            if (value instanceof JTextField)
            {
                getal = Integer.parseInt(((JTextField)value).getText());
                ps.setInt(i + 1, getal);
            } else if (value instanceof JCheckBox) {
                bool = ((JCheckBox)value).isSelected();
                ps.setBoolean(i + 1, bool);
            } else if (value instanceof File) {
                
                fis = new FileInputStream((File)value);
                ps.setBinaryStream(i + 1, fis, (int) ((File)value).length());
            }
        }   
        
        ps.executeQuery();
        conn.commit();
        } catch(Exception ex) {
           
            String FOUT = ex.getMessage();
            System.err.println("Caught IOException: " + ex.getMessage());
        } 
    }
    
    
    public void DeleteData(int ID){
        
        String sql = "DELETE FROM dbo.JavaTable WHERE Sleutel="+ID;
        
        openConnection();
        try{
            Statement stmt = conn.createStatement();
            
            stmt.execute(sql);
             
        }catch(Exception ex){
            System.out.println(ex);
        }
        closeConnection();
        
    }
    
    public void UpdateData(int KeyID,Vector<Object> data)
    {
        FileInputStream fis = null;
        PreparedStatement ps = null;
        int getal;
        boolean bool;
        Foto foto;
       
        String sql = "UPDATE dbo.JavaTable SET ";
      
        
        String QueryString = "SELECT * FROM dbo.JavaTable";
        
        openConnection();
        
        try{
            Statement statment = conn.createStatement();                              
            
            ResultSet resultset = statment.executeQuery(QueryString);              
            
            ResultSetMetaData meta =  resultset.getMetaData();                        
            
            sql += " " + meta.getColumnName(2);                                 
            
            for(int i = 3; i < (meta.getColumnCount() + 1);i++){                
                 sql += " = ?, " + meta.getColumnName(i) ;                          
            }
            
            sql += "= ?  WHERE " + meta.getColumnName(1).toString() + " = " + data.elementAt(0).toString();
            
            
            ps= conn.prepareStatement(sql);
         
            
              for(int i = 1; i < data.size(); i++) {
            Object value = data.elementAt(i);
            
            if (value instanceof Integer)
            {
                getal = (Integer) value;
                ps.setInt(i, getal);
            } else if (value instanceof Boolean) {
                bool = (Boolean) value;
                ps.setBoolean(i, bool);
            } else if (value instanceof Foto) {
                
                foto = (Foto) value;
                
                if(foto.Path == null)
                {
                Blob blob = foto.file;
                InputStream in = blob.getBinaryStream();
                
                ps.setBinaryStream(i, in, blob.length() );
                }
                else
                {
                    
                File file = new File(foto.Path);
                fis = new FileInputStream(file);
                ps.setBinaryStream(i, fis,(int) file.length());
                }
                
                //ps.setBinaryStream(i + 1, in, (int) ((File)value).length());
            }
        }   
              
              
         ps.executeUpdate();   
         conn.commit();
         closeConnection();
         
        } catch(Exception ex) {
           
            String FOUT = ex.getMessage();
            System.err.println("Caught IOException: " + ex.getMessage());
        }
         
        
            
    }
        
  
    
}


