/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8;

/**
 *
 * @author Tom
 */
public interface Data2D {
    
    public Object getCellValue(int row, int column);
    public void setCellValue(Object value, int row,int column);
    public boolean isCellEditable(int column);
    public String getColumnName(int column);
    public int getRowCount();
    public int getColumnCount();
}
