/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * @author Tom
 */
public class AddRowFrame extends javax.swing.JPanel implements ActionListener {
    
    private JFrame f;
    private JdbcSQLServerConnection db;
    private Vector<String> vectorCollumnTypes;
    private JButton btnCancel;
    private JButton btnAdd;
    private JButton btnFoto;
    private JButton btnClear;
    private JTextField textFieldFoto;
    
    public AddRowFrame(Vector<String> vectorCollumnTypes) {
        this.vectorCollumnTypes = vectorCollumnTypes;
        createPanelView();
    }
    
    private void createPanelView() {
        int i = 0;
        Vector<String> vectorCollumnNames = new Vector<String>();
        Vector<Vector<Object>> vectorRowData = new Vector<Vector<Object>>();
        
        btnAdd = new JButton("Add");
        btnCancel = new JButton("Cancel");
        btnClear = new JButton("Clear");
        btnFoto = new JButton("Browse");
        btnCancel.addActionListener(this);
        btnAdd.addActionListener(this);
        btnFoto.addActionListener(this);
        db = new JdbcSQLServerConnection();
        f = new JFrame("Add row");
        
        JPanel p = new javax.swing.JPanel(new SpringLayout());
        vectorCollumnNames = db.GetCollumnNameData();
        
        for(i = 0; i < db.GetCollumnCount(); i++) {
            JLabel lbl = new JLabel(vectorCollumnNames.elementAt(i), JLabel.TRAILING);
            p.add(lbl);
            
            switch(vectorCollumnTypes.elementAt(i)) {
                case "class java.lang.Integer" :
                    JTextField textFieldInteger = new JTextField(10);
                    lbl.setLabelFor(textFieldInteger);
                    p.add(textFieldInteger);
                    break;
                case "class java.lang.String" :
                    JTextField textFieldString = new JTextField(10);
                    lbl.setLabelFor(textFieldString);
                    p.add(textFieldString);
                    break;
                case "class java.lang.Boolean" :
                    JCheckBox checkBoxBoolean = new JCheckBox();
                    p.add(checkBoxBoolean);
                    break;
                case "class javaapplication1.Foto" :
                    textFieldFoto = new JTextField(10);
                    lbl.setLabelFor(textFieldFoto);
                    p.add(textFieldFoto);
                    break;
            }
        }
        p.add(btnClear);
        p.add(btnFoto);
        p.add(btnAdd);
        p.add(btnCancel);
        SpringUtilities.makeCompactGrid(p, 
                i+2, 2, //rows, cols
                6, 6,   //initX, initY
                6, 6);  //xPad, yPad
        f.add(p);
        f.pack();
        f.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent evt) {
        
        if(evt.getSource() == btnCancel){
            f.dispose();
        } else if(evt.getSource() == btnAdd) {
            String fotoPath = textFieldFoto.getText();
            db.InsertData();
        } else if(evt.getSource() == btnFoto) {
            final JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new ImageFilter());
            int returnVal = fc.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                textFieldFoto.setText(file.getPath());
            }
        }
    }
}

