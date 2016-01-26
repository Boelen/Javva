/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author MichielAdmin
 */
public class ColorRenderer extends JLabel
                           implements TableCellRenderer {
    boolean isBordered;
    Border unselectedBorder = null;
    Border selectedBorder = null;
    public ColorRenderer(boolean isBordered) {
        this.isBordered = isBordered;
        setOpaque(true); //MUST do this for background to show up.
    }

    @Override
    public Component getTableCellRendererComponent(
                            JTable table, Object object,
                            boolean isSelected, boolean hasFocus,
                            int row, int column) {
       
        if (object instanceof Color) 
        {
        Color newColor = (Color)object;
        setBackground(newColor);
        setToolTipText(newColor.toString());
        }
        else if (object instanceof Foto)
        {
//             ImageIcon ic = new ImageIcon(((Foto)object).getScaledFoto(table.getRowHeight()));
//            //ImageIcon ic = new ImageIcon(((Foto)object).getScaledFoto());
//            this.setIcon(ic);
            //setToolTipText(((Foto)object).getPath());
            this.setIcon(((Foto) object).ic);
        }
        
        if (isBordered) {
            if (isSelected) {
                if (selectedBorder == null) 
                {
                    selectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5, table.getSelectionBackground());
                }
                //selectedBorder is a solid border in the color
                //table.getSelectionBackground().
                setBorder(selectedBorder);
            } else {
                if (unselectedBorder == null) 
                {
                    unselectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5, table.getBackground());
                }
                //unselectedBorder is a solid border in the color
                //table.getBackground().
                setBorder(unselectedBorder);
            }
        }
        
        //setToolTipText(newColor.toString()); //Discussed in the following section
        return this;
    }
}
