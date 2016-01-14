/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.Vector;

/**
 *
 * @author MichielAdmin
 */
public class LookupKey {
    int waarde;
    LookupKey(int sleutel)
    {
        waarde = sleutel;
    }
    int getKey()
    {
        return (waarde);
    }
   public String getOmschrijving()
   {
       Lookup lkp = Lookup.getInstance();
       return lkp.findbyKey(waarde);
   }
   
   public Lookup getOmschrijvingen()
   {
       Lookup lkp = Lookup.getInstance();
       return lkp;
   }
}
