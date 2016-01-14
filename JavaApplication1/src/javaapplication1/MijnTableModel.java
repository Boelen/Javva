/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.Color;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author MichielAdmin
 */
class MijnTableModel extends AbstractTableModel {
    
    Data2D dataContainer;
    
    public void setData(Data2D object) {
        this.dataContainer = object;
    }
    
     public int getColumnCount() {
        return dataContainer.getColumnCount(); //Hier nooit de data gebruiken
    }

    public int getRowCount() {
        return dataContainer.getRowCount();
    }

    public String getColumnName(int col) {
        return dataContainer.getColumnName(col);
    }

    public Object getValueAt(int row, int col) {
        return dataContainer.getCellValue(row, col);
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass(); //Geeft het datatype van de kolom
    }
    
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        
        //return dataContainer.isCellEditable(col);
        
        if (col < 2) {
            return false;
        } else {
            return dataContainer.isCellEditable(col);
        }
    }
    
    public void setValueAt(Object value, int row, int col) {
        dataContainer.setCellValue(value, row, col);
        fireTableCellUpdated(row, col);
    }
}
