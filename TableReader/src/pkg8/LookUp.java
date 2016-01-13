/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8;

import java.util.Vector;
import javax.swing.AbstractListModel;

/**
 *
 * @author Tom
 */
public class LookUp extends AbstractListModel {
    private Vector<Integer> keyv = new Vector();
    private Vector<String> Omschrijving = new Vector();
    private static LookUp instance=null;  
    
       public static LookUp getInstance()
       {
           if(instance==null)
               instance = new LookUp();
           return instance;
       }
       
        protected LookUp() 
       {
           
    keyv.addElement(1);
    keyv.addElement(2);
    keyv.addElement(3);
    Omschrijving.addElement("peuter");
    Omschrijving.addElement("kleuter");
    Omschrijving.addElement("scholier");
       }


public String findbyKey(int key)
{
for(int i=0;i<key;i++)
{
    if( ((int)keyv.elementAt(i)) == key) {
        return Omschrijving.elementAt(i);}
}
return null;
}

public int findbyValue(String value)
{
for(int i=0;i<Omschrijving.size();i++)
{
    if( ((String)Omschrijving.elementAt(i)) == value) {
        return keyv.elementAt(i);}
}
return 0;
}

    @Override
    public int getSize() {
      return keyv.size();
    }

    @Override
    public Object getElementAt(int index) {
      return Omschrijving.elementAt(index);
    }

   
}
