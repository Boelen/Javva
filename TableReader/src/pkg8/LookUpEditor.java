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
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Tom
 */
public class LookUpEditor extends AbstractCellEditor
                         implements TableCellEditor,
                  ActionListener {
    LookUpKey currentItem;
    JButton button;
    JDialog dialog;
    protected static final String EDIT = "edit";

    public LookUpEditor() {
        //Set up the editor (from the table's point of view),
        //which is a button.
        //This button brings up the color chooser dialog,
        //which is the editor from the user's point of view.
        button = new JButton();
        button.setActionCommand(EDIT);
        button.addActionListener(this);
        button.setBorderPainted(false);

        //Set up the dialog that the button brings up.
        //dialog = new JDialog();
    }
    
    public Object getCellEditorValue() {
        return currentItem;
    }
    
      public Component getTableCellEditorComponent(JTable table,
                                                 Object value,
                                                 boolean isSelected,
                                                 int row,
                                                 int column) {
        Component jp = table.getParent();
        currentItem = (LookUpKey)value;
        return button;
    }
      
      public void actionPerformed(ActionEvent e) {
        if (EDIT.equals(e.getActionCommand())) {
            //The user has clicked the cell, so
            //bring up the dialog.
            dialog = new JDialog();
            dialog.setModal(true);
            
            button.setText(currentItem.getOmschrijving());
            LookUp lkp = currentItem.getOmschrijvingen();
            NewJPanel p = new NewJPanel(this);
            p.setModel(lkp);
            dialog.add(p);
            dialog.pack();
            p.setSelected(currentItem.getOmschrijving());
            dialog.setVisible(true);
            
            if (p.ok )
            {
                   System.out.println("ok");
                   String value = p.selectedValue;
                   currentItem.waarde=lkp.findbyValue(value);
            }
            else
            {
                 System.out.println("Cancel");
                 
            }

            //Make the renderer reappear.
            fireEditingStopped();

        } else { //User pressed dialog's "OK" button.
       dialog.dispose();
        }
    }
      
      public void Ok()
      {
          System.out.println("ok");
      }
      
      public void cancel()
      {
         System.out.println("Cancel");
         dialog.dispose();
         fireEditingCanceled();
      }
    
    
}

