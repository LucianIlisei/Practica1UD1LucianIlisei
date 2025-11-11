package com.lucianilisei.lecturamvc.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.lucianilisei.lecturamvc.base.Lectura;

import javax.swing.*;

public class Ventana {
    private JPanel panel1;
    public JRadioButton radioLibro;
    public JRadioButton radioComic;
    public JRadioButton radioManga;
    public JRadioButton radioPoesia;
    public JRadioButton radioRevista;
    public JTextField campoNombre;
    public JTextField campoAutor;
    public JTextField campoEditorial;
    public DatePicker datePicker;
    public JComboBox comboIdiomas;
    public JRadioButton siDisponibleRadioButton;
    public JRadioButton noDisponibleRadioButton;
    public JButton botonNuevo;
    public JButton botonExportar;
    public JButton botonImportar;
    public JSpinner spinnerNumEdicion;
    public JSpinner SpinnerNumPag;
    public JSpinner spinnerPrecio;
    public JList list1;


    public JFrame frame;
    public DefaultListModel<Lectura> dlmLectura;

    public Ventana() {
        frame = new JFrame("LecturaMVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        dlmLectura = new DefaultListModel<Lectura>();
        list1.setModel(dlmLectura);
    }
}
