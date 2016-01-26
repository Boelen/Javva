package javaapplication1;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

/**
 *
 * @author MichielAdmin
 */
public class AddRowFrame extends javax.swing.JPanel implements ActionListener {
    
    private JFrame f;
    private JdbcSQLServerConnection db;
    private Vector<String> vectorCollumnTypes;
    private Vector<Object> GridValues;
    private JButton btnCancel;
    private JButton btnAdd;
    private JButton btnFoto;
    private JButton btnClear;
    private JTextField textFieldFoto;
    private File file;
    
    public AddRowFrame(Vector<String> vectorCollumnTypes) {
        this.vectorCollumnTypes = vectorCollumnTypes;
        createPanelView();
    }
    
    private void createPanelView() {
        int i = 0;
        Vector<String> vectorCollumnNames = new Vector<String>();
        Vector<Vector<Object>> vectorRowData = new Vector<Vector<Object>>();
        GridValues = new Vector<Object>();
                
        btnAdd = new JButton("Add");
        btnCancel = new JButton("Cancel");
        btnClear = new JButton("Clear");
       // btnFoto = new JButton("Browse");
        btnCancel.addActionListener(this);
        btnAdd.addActionListener(this);
       // btnFoto.addActionListener(this);
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
                    GridValues.add(textFieldInteger);
                    lbl.setLabelFor(textFieldInteger);
                    p.add(textFieldInteger);
                    break;
                case "class java.lang.String" :
                    JTextField textFieldString = new JTextField(10);
                    GridValues.add(textFieldString);
                    lbl.setLabelFor(textFieldString);
                    p.add(textFieldString);
                    break;
                case "class java.lang.Boolean" :
                    JCheckBox checkBoxBoolean = new JCheckBox();
                    GridValues.add(checkBoxBoolean);
                    p.add(checkBoxBoolean);
                    break;
                case "class javaapplication1.Foto" :
                    JButton btnFoto = new JButton("Browse");
                    btnFoto.setActionCommand((Actions.SHOW.name()));
                    btnFoto.addActionListener(this);
                    GridValues.add(btnFoto);
                    p.add(btnFoto);
                    break;       
            }
        }
      
        //p.add(btnFoto);
        JLabel lbl = new JLabel("Clear this", JLabel.TRAILING);
        p.add(lbl);
        p.add(btnClear);
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
            new NewJFrame().setVisible(true);
        } else if(evt.getSource() == btnAdd) {           
            db.InsertData(GridValues, file);
            f.dispose();
            new NewJFrame().setVisible(true);
        } else if(evt.getActionCommand().equals(Actions.SHOW.name())) {
            final JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new ImageFilter());
            int returnVal = fc.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                //File file = fc.getSelectedFile();
                
            for(int i = 0; i < GridValues.size(); i++) {
            Object value = GridValues.elementAt(i);
             if (value instanceof JButton) {
                file = fc.getSelectedFile();
            }
            }     
            }
        }
    }
    
    private enum Actions
    {
        SHOW
    }
}