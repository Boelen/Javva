/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.Color;
import java.awt.Image;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Types;
import java.util.Vector;

/**
 *
 * @author MichielAdmin
 */
public class MyData implements Data2D {
    Vector<Vector<Object>> vectorRowData = new Vector<Vector<Object>>();
    private JdbcSQLServerConnection db = new JdbcSQLServerConnection();
    Vector<String> vectorCollumnNames = new Vector<String>();
    
    public MyData(){
        vectorRowData = db.GetRowData();
        vectorCollumnNames = db.GetCollumnNameData();
    }
    
    @Override
    public Object getCellValue(int row, int column) {
        return ((Vector)vectorRowData.elementAt(row)).elementAt(column);
    }

    @Override
    public void setCellValue(Object value, int row, int column) {
       ((Vector)vectorRowData.elementAt(row)).setElementAt(value,column);
    }

    @Override
    public boolean isCellEditable(int column) {
        return true;
    }

    @Override
    public String getColumnName(int column) {
        return vectorCollumnNames.elementAt(column);
    }

    @Override
    public int getRowCount() {
        return vectorRowData.size();
    }

    @Override
    public int getColumnCount() {
        return vectorCollumnNames.size();
    }
    
    public Class getColumnClass(int c) {
        return this.getCellValue(0, c).getClass();
    }
}
