/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Outros;
  
import java.awt.Graphics;  
import java.awt.Graphics2D;
import java.awt.Image;  
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;  
import javax.swing.JOptionPane;  
  
/** 
/**
 *
 * @author Maxwel
 */
public class background extends JDesktopPane {    
    
        @Override
        public void paintComponent(Graphics g){
            Image img = new ImageIcon(this.getClass().getResource("logo1.jpg")).getImage();
             try{
                 
                 Graphics2D g2d = (Graphics2D)g;
                 double x = img.getWidth(null);
                 double y = img.getWidth(null);
                 g2d.scale(getWidth()/x,getWidth()/y);
                 g2d.drawImage(img, 0,0,this);
             
             }catch(Exception e){
             
                 JOptionPane.showMessageDialog(null , e);
             
             }
        
        
        }
    
    
    }    

