/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author MichielAdmin
 */
public class ImageFilter extends FileFilter {
    ImageFilter()
    {
        
    }
    
    @Override
    public boolean accept(File file)
    {
        if(file.isDirectory())
            return true;
        if(file.getName().endsWith(".jpg"))
            return true;
        if(file.getName().endsWith(".jpeg"))
            return true;
        if(file.getName().endsWith(".gif"))
            return true;
        if(file.getName().endsWith(".PNG"))
            return true;
        return false;
    }
    
    @Override
    public String getDescription() {
        return ("Fotobestanden");
    }
}
