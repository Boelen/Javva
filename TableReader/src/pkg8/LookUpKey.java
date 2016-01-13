/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8;

/**
 *
 * @author Tom
 */
public class LookUpKey {
    int waarde;
    LookUpKey(int sleutel)
    {
        waarde= sleutel;
    }
    int getKey()
    {
        return(waarde);
    }
    
    public String getOmschrijving ()
    {
        LookUp lkp = LookUp.getInstance();
        return lkp.findbyKey(waarde);
    }
    
    public  LookUp getOmschrijvingen ()
    {
        LookUp lkp = LookUp.getInstance();
        return lkp;
    }
    
}
