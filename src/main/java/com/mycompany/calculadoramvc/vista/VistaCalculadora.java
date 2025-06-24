package com.mycompany.calculadoramvc.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaCalculadora extends JFrame {

    private JTextField displayField;
    private JButton[] numButtons;
    private JButton addButton, subButton, mulButton, divButton, eqButton, clearButton;

    public VistaCalculadora() {
        setTitle("Calculadora MVC");
        setLayout(new BorderLayout());

        // Crear campo de texto para mostrar los resultados
        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setFont(new Font("Arial", Font.PLAIN, 24));
        add(displayField, BorderLayout.NORTH);

        // Crear panel para los botones
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 5));

        // Botones numéricos
        numButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
            panel.add(numButtons[i]);
        }

        // Botones de operaciones
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        eqButton = new JButton("=");
        clearButton = new JButton("C");

        // Añadir los botones al panel
        panel.add(addButton);
        panel.add(subButton);
        panel.add(mulButton);
        panel.add(divButton);
        panel.add(eqButton);
        panel.add(clearButton);

        add(panel, BorderLayout.CENTER);
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public String getDisplayText() {
        return displayField.getText();
    }

    public void setDisplayText(String text) {
        displayField.setText(text);
    }

    // Añadir los ActionListener a los botones
    public void addNumberListener(ActionListener listener) {
        for (JButton button : numButtons) {
            button.addActionListener(listener);
        }
    }

    public void addOperationListener(ActionListener listener) {
        addButton.addActionListener(listener);
        subButton.addActionListener(listener);
        mulButton.addActionListener(listener);
        divButton.addActionListener(listener);
        eqButton.addActionListener(listener);
        clearButton.addActionListener(listener);
    }
}

