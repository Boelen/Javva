/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Tom
 */
public class FotoEditor extends AbstractCellEditor
                        implements TableCellEditor,
                        ActionListener {
    JButton button;
    JFileChooser fileChooser;
    Foto huidigeFoto;
    protected static final String EDIT = "edit";
    
    
    
    public FotoEditor(){
    
    button = new JButton();
    button.setActionCommand(EDIT);
    button.addActionListener(this);
    button.setBorderPainted(false);
    
    fileChooser = new JFileChooser();
    }
    
    
    public void actionPerformed(ActionEvent e)
    {
        if(EDIT.equals(e.getActionCommand())) {
            button.setIcon(new ImageIcon(huidigeFoto.scaledFoto));
            fileChooser.setFileFilter( new ImageFilter());
            
            int returnVal = fileChooser.showOpenDialog(null);
            
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File file = fileChooser.getSelectedFile();
                
                huidigeFoto.read(file.getPath());
            }
            
            fireEditingStopped();
        }
    }
    
    public Object getCellEditorValue() {
        return huidigeFoto;
    }
    
      public Component getTableCellEditorComponent(JTable table,
                                                 Object value,
                                                 boolean isSelected,
                                                 int row,
                                                 int column) {
        huidigeFoto = (Foto)value;
        return button;
    }
    
}
