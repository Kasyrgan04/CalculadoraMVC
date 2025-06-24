
package com.mycompany.calculadoramvc.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Modelo{
    private List<String> historial;
    private Queue<String> memoria;
    private String num1,num2,resultado;

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    
    public Modelo(){
        historial = new ArrayList<>();
        this.setNum1("");
        this.setNum2("");
        this.setResultado("");
        memoria = new LinkedList<>();
    }
    
    public void agregarHistorial(String expresion, String resultado){
        historial.add(expresion + " = " + resultado);
    }
    
    public void guardarHistorial(){
        String ruta =System.getProperty("user.home") + File.separator + "historial.txt";
        try(PrintWriter writer = new PrintWriter(new FileWriter(ruta))){
          for(String linea : historial){
              writer.println(linea);
          }  
        }catch (IOException e){
            System.err.println("Error al agregar a historial: " + e.getMessage());
        }
    }
    
    public String leerHistorial(){
        String ruta = System.getProperty("user.home")+ File.separator + "Historial.txt";
        StringBuilder contenido = new StringBuilder();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(ruta))){
            String linea;
            while((linea = reader.readLine())!= null){
                contenido.append(linea).append("\n");
            }
        }catch(IOException e){
            e.printStackTrace();
            return "Error al leer archivo";
        }
        
        return contenido.toString();
    }
    
    
    
    
    public boolean esPrimo(double num){
        int num1 = (int)num;
        if(num1<=1) return false;
        for(int i = 2; i<= Math.sqrt(num1); i++){
            if(num1%i==0) return false;
        }
        return true;
    }
    public double calcularPromedio(){
        double suma = 0;
        int cantidad = memoria.size();
        
        if(cantidad>0){
            for(String num : memoria){
                try{
                    suma+=Double.parseDouble(num);
                } catch(NumberFormatException e){
                    
                }
            }
        }
        setResultado(String.valueOf(suma/cantidad));
        return suma/cantidad ;
    }
    
    public void lipiarHistorial(){
        historial.clear();
    }
    
    public String aBinario(double numeroDecimal){
        int numero = (int) numeroDecimal;
        return Integer.toBinaryString(numero);
    }
    
    public double suma(){
        double suma = 0;
        try{
            suma= Double.parseDouble(num1)+Double.parseDouble(num2);
            setResultado(String.valueOf(suma));
            agregarHistorial(num1+ " + " + num2, String.valueOf(suma));
        }catch(NumberFormatException e){
            setResultado("Error");
        }
        return suma;
    }
    
    public void resta(){
        try{
            double diferencia= Double.parseDouble(num1)-Double.parseDouble(num2);
            setResultado(String.valueOf(diferencia));
            agregarHistorial(num1+ " - " + num2, String.valueOf(diferencia));
        }catch(NumberFormatException e){
            setResultado("Error");
        }
    }
    
    public void multiplicacion(){
        try{
            double producto= Double.parseDouble(num1)*Double.parseDouble(num2);
            setResultado(String.valueOf(producto));
            agregarHistorial(num1+ " * " + num2, String.valueOf(producto));
        }catch(NumberFormatException e){
            setResultado("Error");
        }
    }
    public void division(){
        try{
            double cociente= Double.parseDouble(num1)/Double.parseDouble(num2);
            setResultado(String.valueOf(cociente));
            agregarHistorial(num1+ " / " + num2, String.valueOf(cociente));
        }catch(NumberFormatException e){
            setResultado("Error");
        }catch (ArithmeticException e){
            setResultado("Error: División por 0");
        }
    }
    
    public void esPrimo(){
        try{
            double num= Double.parseDouble(num1);
            if(num<=1){
                setResultado("No es primo");
                return;
            }
            
            boolean primo = true;
            for(int i =2; i<=Math.sqrt(num); i++){
                if(num%i==0){
                    primo=false;
                    break;
                }
            }
            if(primo){
                setResultado("Es primo");
                agregarHistorial("Primo " + num1, " True");
            }else{
                setResultado("No es primo");
                agregarHistorial("Primo " + num1, " False");
            }
        }catch(NumberFormatException e){
            setResultado("Error");
        }
    }
    
    public void aBinario(){
        try{
            double numero=Double.parseDouble(num1);
            
            int parteEntera = (int)numero;
            double parteDecimal = numero - parteEntera;
            
            String parteEnteraBinaria = Integer.toBinaryString(parteEntera);
            
            //Esto ayuda a convertir la parte decimal a binario
            
            StringBuilder parteDecimalBinaria = new StringBuilder(".");
            while(parteDecimal>0){
                parteDecimal*=2;
                int bit = (int)parteDecimal;
                parteDecimalBinaria.append(bit);
                parteDecimal-= bit;
                
                //Aca se limita la cantidad de digitos
                if(parteDecimalBinaria.length()>10){
                    break;
                }
            }
            
            setResultado(parteEnteraBinaria + parteDecimalBinaria.toString());
            agregarHistorial("Binario " + num1, resultado);
        }catch(NumberFormatException e){
            setResultado("Error");
        }
    }
    
    public void agregarMemoria(){
        if(memoria.size()==10){
            memoria.poll();
        }
        memoria.offer(num1);
        System.out.println("Numero guardado en memoria");
    }
    
    
}