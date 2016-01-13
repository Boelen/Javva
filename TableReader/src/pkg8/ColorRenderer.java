/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Tom
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
        
        
        if (object instanceof Foto)
        {
            //ImageIcon ic = new ImageIcon(((Foto)object).getScaledFoto(table.getRowHeight()));
            this.setIcon(((Foto) object).ic);
            //ImageIcon ic = new ImageIcon(((Foto)object).getScaledFoto());
            //this.setIcon(ic);
            //setToolTipText(((Foto)object).getPath());
        }
        
        if (object instanceof LookUpKey)
        {
            this.setText(((LookUpKey)object).getOmschrijving());
            this.setHorizontalAlignment(SwingConstants.CENTER);
            //this.setHorizontalTextPosition(SwingConstants.CENTER);
        }
        
         if (isBordered) {
            if (isSelected) {
                
                //selectedBorder is a solid border in the color
                //table.getSelectionBackground().
               if(selectedBorder==null)
               {selectedBorder = BorderFactory.createMatteBorder(2,5,2,5,table.getSelectionBackground());          
                }
               setBorder(selectedBorder);
               
            } else {

                //unselectedBorder is a solid border in the color
                //table.getBackground().
                if(unselectedBorder == null)
                {
                unselectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5, table.getBackground());
               
                }
                 setBorder(unselectedBorder);
            }
        }
        
       
        
       // setToolTipText(newColor.toString()); //Discussed in the following section
        return this;
    }
    
}
