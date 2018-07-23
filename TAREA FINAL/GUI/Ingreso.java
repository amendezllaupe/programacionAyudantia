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

public class Ingreso extends JFrame implements ActionListener{
    
    private Batalla batalla;
    private JButton generarEscuadroButton;
    private JTextField numeroLuchadoresTF;
    private JLabel instruccionLabel;
    private JTextArea statsLuchadoresTA;
            
    public Ingreso(String title){
        super(title);
        
        initComponents();
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        elementsSize();
        
        addWindow();
        
        this.generarEscuadroButton.addActionListener(this);
    }
    
    private void initComponents(){
        this.generarEscuadroButton = new JButton("GENERAR ESCUADRON");
        
        this.numeroLuchadoresTF = new JTextField();
        
        this.instruccionLabel = new JLabel("Ingresa el número de Luchadores a Pelear (1 a 6)");
        
        this.statsLuchadoresTA = new JTextArea("");
        
        this.batalla = new Batalla();
        
    }
    
    private void elementsSize(){
        this.setSize(800,600);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        this.generarEscuadroButton.setBounds(75, 100, 150, 30);
        
        this.instruccionLabel.setBounds(25, 25, 300, 30);
        
        this.numeroLuchadoresTF.setBounds(150,50,20,20);
        
        this.statsLuchadoresTA.setBounds(380,100,400,400);
    }
    
    private void addWindow(){
        this.add(this.generarEscuadroButton);
        this.add(this.numeroLuchadoresTF);
        this.add(this.instruccionLabel);
        this.add(this.statsLuchadoresTA);
        this.add(new JLabel());
    }
    
    private int verificarNumero(String text){
        int num = 0;
        //Si el texto ingresado no es un número entero se gatilla la excepción
        //Cubre un error de capa 8 (en caso de que el usuario no ingrese un número) evitando que el programa genere un error.
        //Se utiliza para casos en los que se introducen caracteres no numericos
        try{
            num = Integer.parseInt(text);
            if (num>6 || num<1){
                System.out.println("La cantida de Luchadores no es válida");
                System.exit(0);
            }
        }catch(NumberFormatException e){
            System.out.println("Sorry, it isn't a number");
            System.exit(0);
        }
        
        return num;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == this.generarEscuadroButton){
            this.batalla.generarEscuadron(verificarNumero(this.numeroLuchadoresTF.getText()));
            this.statsLuchadoresTA.setText(batalla.mostrarHPTodos());
            this.batalla.pelear(verificarNumero(this.numeroLuchadoresTF.getText()));
        }
    }
}
