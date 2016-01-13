/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8;

import java.awt.Color;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tom
 */

public class MyTableModel extends AbstractTableModel {
   
    Data2D dataContainer;
    
    public void setData(Data2D object)
    {
        this.dataContainer = object;
    }
    
    public int getColumnCount() {
        return dataContainer.getColumnCount();
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
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
       return dataContainer.isCellEditable(col);
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        dataContainer.setCellValue(value, row, col);
        fireTableCellUpdated(row, col);
    }
}
