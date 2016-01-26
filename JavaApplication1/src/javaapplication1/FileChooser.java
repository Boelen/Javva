/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author MichielAdmin
 */
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class FileChooser extends AbstractCellEditor
                         implements TableCellEditor,
                  ActionListener {
    Foto huidigeFoto;
    JButton button;
    JFileChooser fileChooser;
    JDialog dialog;
    protected static final String EDIT = "edit";

    public FileChooser() {
        //Set up the editor (from the table's point of view),
        //which is a button.
        //This button brings up the color chooser dialog,
        //which is the editor from the user's point of view.
        button = new JButton();
        button.setActionCommand(EDIT);
        button.addActionListener(this);
        button.setBorderPainted(false);

        //Set up the dialog that the button brings up.
        fileChooser = new JFileChooser();
    }

    /**
     * Handles events from the editor button and from
     * the dialog's OK button.
     */
    public void actionPerformed(ActionEvent e) {
        if (EDIT.equals(e.getActionCommand())) {
            //The user has clicked the cell, so
            //bring up the dialog.
//            button.setIcon(new ImageIcon(huidigeFoto.scaledFoto));
            fileChooser.setFileFilter( new ImageFilter());
            int returnVal = fileChooser.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                huidigeFoto.read(file.getPath());
            }
            //Make the renderer reappear.
              button.setIcon(new ImageIcon(huidigeFoto.scaledFoto));
            fireEditingStopped();

        } 
    }

    //Implement the one CellEditor method that AbstractCellEditor doesn't.
    public Object getCellEditorValue() {
        return huidigeFoto;
    }

    //Implement the one method defined by TableCellEditor.
    public Component getTableCellEditorComponent(JTable table,
                                                 Object value,
                                                 boolean isSelected,
                                                 int row,
                                                 int column) {
        huidigeFoto = (Foto)value;
        return button;
    }
  
}
