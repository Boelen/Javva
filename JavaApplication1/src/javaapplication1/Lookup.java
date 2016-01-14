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
public class Lookup extends AbstractListModel {
    private Vector<String> Omschrijving = new Vector();
    private Vector<Integer> keyv = new Vector();
    public static Lookup instance = null;
    
    public static Lookup getInstance(){
        if (instance == null)
            instance = new Lookup();
            return instance;     
    }
    
    protected Lookup()
    {
        keyv.addElement(1);
        keyv.addElement(2);
        keyv.addElement(3);
        keyv.addElement(4);
        Omschrijving.addElement("peuter");
        Omschrijving.addElement("kleuter");
        Omschrijving.addElement("scholier");
        Omschrijving.addElement("student");
    }
    
    public String findbyKey(int key)
    {
        for(int i = 0; i< key; i++)
        {
            if(((int)keyv.elementAt(i))==key) {
                return Omschrijving.elementAt(i);
            }
        }
        return null;
    }

    @Override
    public int getSize() {
        return keyv.size();
    }

    @Override
    public Object getElementAt(int index) {
        return Omschrijving.elementAt(index);
    }
    
    public int findbyValue(String value)
    {
        for(int i = 0; i< Omschrijving.size(); i++)
        {
            if(((String)Omschrijving.elementAt(i))==value) {
                return keyv.elementAt(i);
            }
        }
        return 0;
    }
}
