package com.mycompany.calculadoramvc.main;
import com.mycompany.calculadoramvc.vista.*;
import com.mycompany.calculadoramvc.modelo.Modelo;
import com.mycompany.calculadoramvc.controlador.Controlador;

/**
 *
 * @author dylan
 */
public class App {
    
    public static void main(String[] args) {
        // Crear la vista, el modelo y el controlador
        
        Vista GUI = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(GUI, modelo);

        GUI.setVisible(true);
    }
}
