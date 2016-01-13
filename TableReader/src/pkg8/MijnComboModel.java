/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8;

import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Tom
 */
public class MijnComboModel extends DefaultComboBoxModel{
    public int getSize()
    { return (5);}
    
    public Object getElemantAt(int index)
    {
        return index;
    }
}
