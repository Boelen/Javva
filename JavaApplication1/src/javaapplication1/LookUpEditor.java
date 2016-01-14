/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

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
 * @author MichielAdmin
 */
public 
 class LookUpEditor extends AbstractCellEditor
                         implements TableCellEditor,
                  ActionListener {
    LookupKey currentItem;
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
    }

    /**
     * Handles events from the editor button and from
     * the dialog's OK button.
     */
    public void actionPerformed(ActionEvent e) {
        if (EDIT.equals(e.getActionCommand())) {
            //The user has clicked the cell, so
            //bring up the dialog.
            dialog = new JDialog();
            dialog.setModal(true);
            button.setText(currentItem.getOmschrijving());
            Lookup lkp = currentItem.getOmschrijvingen();
            NewJPanel p = new NewJPanel(this);
            p.setModel(lkp);
            dialog.add(p);
            dialog.pack();
            p.setSelected(currentItem.getOmschrijving());
            dialog.setVisible(true);
            if (p.ok)
            {
                System.out.println("OK");
                String value = p.selectedValue;
                currentItem.waarde = lkp.findbyValue(value);
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

    //Implement the one CellEditor method that AbstractCellEditor doesn't.
    public Object getCellEditorValue() {
        return currentItem;
    }

    //Implement the one method defined by TableCellEditor.
    public Component getTableCellEditorComponent(JTable table,
                                                 Object value,
                                                 boolean isSelected,
                                                 int row,
                                                 int column) {
        Component jp = table.getParent();
        currentItem = (LookupKey)value;
        return button;
    }
    
    public void Ok() 
    {
        System.out.println("OK");
    }
    
    public void Cancel()
    {
        System.out.println("Cancel");
        dialog.dispose();
        fireEditingCanceled();
    }
}
