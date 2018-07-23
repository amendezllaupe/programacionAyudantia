/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Desarrollo.Batalla;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author hypoc
 */
public class Observacion extends JFrame{
    
    private TextArea showText;
    
    public Observacion(String title, Batalla batalla){
        super(title);
        
        this.showText = new TextArea(batalla.getResultados());
        
        elementsSize();
        
        addWindow();
        
        this.showText.setEditable(false);      
        
    }
    
    private void addWindow(){
        this.add(this.showText);
        this.add(new JLabel());
    }
    
    private void elementsSize(){
        this.setSize(600,600);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        this.showText.setBounds(10,10,490,490);
    }
}
