/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8;

import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * @author Tom
 */
public class AddRowFrame extends javax.swing.JPanel {
 
    
     private JFrame f;
     private JdbcSQLServerConnection db;
     private Vector<String> vectorCollumnTypes;
     
     public AddRowFrame(Vector<String> vectorCollumnTypes) {
        this.vectorCollumnTypes = vectorCollumnTypes;
         createPanelView();      
    }
    
    private void createPanelView()
    {
        int i = 0;
        Vector<String> vectorCollumnNames = new Vector<String>();
        Vector<Vector<Object>> vectorRowData = new Vector<Vector<Object>>();
        
        JButton buttons = new JButton("Toevoegen");
        JButton buttons2 = new JButton("Annuleer");
        db = new JdbcSQLServerConnection();

        f = new JFrame("Add Row");
        
               
        
        JPanel p= new javax.swing.JPanel(new SpringLayout());  
        vectorCollumnNames = db.GetCollumnNameData();     

        for(i = 0; i < db.GetCollumnCount();i++)
        {
            JLabel l = new JLabel(vectorCollumnNames.elementAt(i), JLabel.TRAILING);
            p.add(l);
        
            switch(vectorCollumnTypes.elementAt(i))
                        {
                            case "class java.lang.Integer" : 
                                JTextField textField = new JTextField(10);
                                l.setLabelFor(textField);
                                p.add(textField);
                                break;
                            case "class java.lang.String" : 
                                JTextField textField2 = new JTextField(10);
                                l.setLabelFor(textField2);
                                p.add(textField2);
                                break;
                             case "class java.lang.Boolean" : 
                                 JCheckBox checkBox = new JCheckBox();
                                 p.add(checkBox);
                                break;
                            case "class pkg8.Foto" : 
                                JButton button = new JButton();
                                p.add(button);
                                break;       
            }
       }
        
        
             p.add(buttons);
             p.add(buttons2);
             SpringUtilities.makeCompactGrid(p,
                                i+1, 2, //rows, cols
                                6, 6,        //initX, initY
                                6, 6);       //xPad, yPad
             f.add(p);
             f.pack();
             f.setVisible(true);
    
 }
    
    
    
}
