package com.mycompany.calculadoramvc.controlador;

import com.mycompany.calculadoramvc.modelo.Modelo;
import com.mycompany.calculadoramvc.vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Controlador implements ActionListener {
    private Vista vista;
    private Modelo modelo;
    
    public Controlador(Vista vista, Modelo modelo){
        this.vista = vista;
        this.modelo = modelo;
        this.vista.agregarListener(this);
    }

    // Extraer los operandos y el operador de la expresión
    private String[] extraerOperandosYOperadores(String expresion){
        expresion = expresion.replaceAll("\\s", "");
        String operador = "";
        int indice = -1;

        for(int i = 0; i < expresion.length(); i++){
            char ch = expresion.charAt(i);
            if(ch == '+' || ch == '-' || ch == '*' || ch == '/'){
                operador = String.valueOf(ch);
                indice = i;
                break;
            }
        }

        if(operador.isEmpty()){
            return null;
        }

        String num1 = expresion.substring(0, indice);
        String num2 = expresion.substring(indice + 1);

        return new String[]{num1, operador, num2};
    }

        @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        String expresion = vista.jTextField2.getText();  // Captura el texto actual del JTextField

        // Si el comando es un número o un punto decimal, lo agregamos a la expresión
        if (comando.matches("[0-9]") || comando.equals(".")) {
            // Concatenamos el número u operador a la expresión existente en el JTextField
            vista.jTextField2.setText(expresion + comando);  
        }

        // Si el comando es un operador
        else if (comando.matches("[\\+\\-\\*/%\\^]")) {
            // Concatenamos el operador a la expresión existente
            vista.jTextField2.setText(expresion + " " + comando + " ");
        }

        // Si el comando es "C" (limpiar), limpiamos el JTextField y la expresión
        else if (comando.equals("C")) {
            vista.jTextField2.setText("");  // Limpiamos el JTextField
        }

        // Si el comando es "=" (evaluar)
        else if (comando.equals("=")) {
            String[] partes = extraerOperandosYOperadores(expresion);
            if (partes != null) {
                String valorNum1 = partes[0];
                String operador = partes[1];
                String valorNum2 = partes[2];

                modelo.setNum1(valorNum1);
                modelo.setNum2(valorNum2);

                switch (operador) {
                    case "+":
                        modelo.suma();
                        break;
                    case "-":
                        modelo.resta();
                        break;
                    case "*":
                        modelo.multiplicacion();
                        break;
                    case "/":
                        modelo.division();
                        break;
                    default:
                        vista.jTextField2.setText("Error: Operador no reconocido");
                }

                // Mostrar el resultado
                vista.jTextField2.setText(modelo.getResultado());
                System.out.println(modelo.getResultado());
            }
        }

        // Si el comando es "Primo", calculamos si el número es primo
        else if (comando.equals("Primo")) {
            modelo.setNum1(vista.jTextField2.getText());
            modelo.esPrimo();
            vista.jTextField2.setText(modelo.getResultado());
        }

        // Si el comando es "Binario", convertimos el número a binario
        else if (comando.equals("Binario")) {
            modelo.setNum1(vista.jTextField2.getText());
            modelo.aBinario();
            vista.jTextField2.setText(modelo.getResultado());
        }

        // Si el comando es "M+", agregamos a la memoria
        else if (comando.equals("M+")) {
            modelo.setNum1(vista.jTextField2.getText());
            modelo.agregarMemoria();
        }

        // Si el comando es "AVG", calculamos el promedio
        else if (comando.equals("Avg")) {
            modelo.calcularPromedio();
            vista.jTextField2.setText(modelo.getResultado());
        }
        
        else if(comando.equals("Data")){
            modelo.guardarHistorial();
            String contenido = modelo.leerHistorial();
            JOptionPane.showMessageDialog(vista, contenido, "Historial de calculos", JOptionPane.INFORMATION_MESSAGE);
        }
        
       
    }

}
