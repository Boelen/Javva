/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Tom
 */
public class Foto {
    Image foto;
    Image scaledFoto;
    String path;
    Blob file;
    ImageIcon ic;
    
    
    public Foto()
    {
        
    }
    
    public Foto(String pad)
    {
     read (pad);   
    }
    
    public Foto(Blob file) throws SQLException {
        this.file = file;
        if (file.length() > 0) {
            ic = new ImageIcon(file.getBytes(1, (int) file.length()));
            int hoogte = ic.getIconHeight();
            int breedte = ic.getIconWidth();
            float ratio = ((float) 50 / hoogte);
            int nieuwebreedte = (int) (breedte * ratio);
            ic = new ImageIcon(ic.getImage().getScaledInstance(50, nieuwebreedte, Image.SCALE_SMOOTH));
        }
    }
    
    
    public void read(String path)
        {
            this.path = path;
        
        try{
            foto=ImageIO.read((new File(path)));
        }
        catch (Exception ex)
        {
            foto=scaledFoto=null;
        }
        ImageIcon ic = new ImageIcon(foto);
        int height = ic.getIconHeight();
        int width= ic.getIconWidth();
        
        if (height>50)
        {
            double ratio = ((double)50)/height;
            scaledFoto = ic.getImage().getScaledInstance( (int) ((int)width*ratio),100,Image.SCALE_SMOOTH);
        }
        else scaledFoto = foto;
        }
    

    
    public Image getScaledFoto()
    {
        return scaledFoto;
    }
    
    public Image getScaledFoto(int hoogte)
    {
        ImageIcon ic = new ImageIcon(foto);
    int height = ic.getIconHeight();
        int width= ic.getIconWidth();
        
        if (height>hoogte)
        {
            double ratio = ((double)50)/height;
            scaledFoto = ic.getImage().getScaledInstance( (int) ((int)width*ratio),100,Image.SCALE_SMOOTH);
        }
        else scaledFoto = foto;
        return scaledFoto;
    }
   
    }
//    string getPath()
//    {
//        return path;
//    }
    

