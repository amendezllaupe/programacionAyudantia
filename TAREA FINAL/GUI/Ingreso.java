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
    private Observacion observacion;
    private JButton generarEscuadroButton;
    private JTextField numeroLuchadoresTF;
    private JLabel instruccionLabel;
    private JTextArea statsLuchadoresTA;
    private JLabel mensajeError;
            
    public Ingreso(String title){
        super(title);
        
        initComponents();
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        elementsSize();
        
        addWindow();
        
        this.statsLuchadoresTA.setEditable(false);
        
        this.generarEscuadroButton.addActionListener(this);
    }
    
    private void initComponents(){
        this.generarEscuadroButton = new JButton("PELEAR");
        
        this.numeroLuchadoresTF = new JTextField();
        
        this.instruccionLabel = new JLabel("Ingresa el número de Luchadores a Pelear (1 a 6)");
        this.mensajeError = new JLabel("");
        
        this.statsLuchadoresTA = new JTextArea("");
        
       // this.batalla = new Batalla();
        
    }
    
    private void elementsSize(){
        this.setSize(800,600);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        this.generarEscuadroButton.setBounds(75, 100, 150, 30);
        
        this.instruccionLabel.setBounds(25, 25, 300, 30);
        this.mensajeError.setBounds(75, 75, 150, 30);
        
        this.numeroLuchadoresTF.setBounds(150,50,20,20);
        
        this.statsLuchadoresTA.setBounds(380,100,400,400);
    }
    
    private void addWindow(){
        this.add(this.generarEscuadroButton);
        this.add(this.numeroLuchadoresTF);
        this.add(this.instruccionLabel);
        this.add(this.statsLuchadoresTA);
        this.add(this.mensajeError);
        this.add(new JLabel());
    }
    
    private boolean verificarNumero(String text){
        boolean esNumero = true;
        int num = 0;
        //Si el texto ingresado no es un número entero se gatilla la excepción
        //Cubre un error de capa 8 (en caso de que el usuario no ingrese un número) evitando que el programa genere un error.
        //Se utiliza para casos en los que se introducen caracteres no numericos
        try{
            num = Integer.parseInt(text);
            if (num>6 || num<1){
                esNumero = false;
                this.numeroLuchadoresTF.setText("0");
                this.mensajeError.setText("Está fuera del rango");
            }
        }catch(NumberFormatException e){
            esNumero = false;
            this.mensajeError.setText("Sorry, it isn't a number");
        }
        
        return esNumero;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == this.generarEscuadroButton){
            if(verificarNumero(this.numeroLuchadoresTF.getText())){
                this.batalla = new Batalla();
                //this.batalla.getPeleadores().getListaLuchadores().clear();
                this.batalla.generarEscuadron(Integer.parseInt(this.numeroLuchadoresTF.getText()));
                this.statsLuchadoresTA.setText(batalla.mostrarHPTodos());
                this.batalla.pelear(Integer.parseInt(this.numeroLuchadoresTF.getText()));
                this.observacion = new Observacion("OBSERVACION",batalla);
                this.batalla.setResultados("");
                observacion.setVisible(true);
            }
        }
    }
}
