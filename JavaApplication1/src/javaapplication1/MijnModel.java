/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.Vector;
import javax.swing.AbstractListModel;

/**
 *
 * @author MichielAdmin
 */
public class MijnModel extends AbstractListModel{
Vector data = new Vector();
    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public Object getElementAt(int index) {
        return data.elementAt(index);
    }
    
    public void addElement(Object waarde)
    {
        data.addElement(waarde);
    }
    
    public void removeElement(int index){
        data.removeElementAt(index);
    }
    
}
