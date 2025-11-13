package com.lucianilisei.lecturamvc.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.lucianilisei.lecturamvc.base.Comic;
import com.lucianilisei.lecturamvc.base.Lectura;
import com.lucianilisei.lecturamvc.base.Libro;

import javax.swing.*;
import java.time.LocalDate;

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
    public JList list1;
    public JTextField campoGeneral;
    public JLabel labelGeneral;


    public JFrame frame;
    public DefaultListModel<Lectura> dlmLectura;

    public Ventana() {
        frame = new JFrame("LecturaMVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        dlmLectura = new DefaultListModel<Lectura>();
        list1.setModel(dlmLectura);

        radioLibro.setSelected(true);
        siDisponibleRadioButton.setSelected(true);

        SpinnerNumPag.setModel(new SpinnerNumberModel(1, 1, 10000, 1));
        spinnerNumEdicion.setModel(new SpinnerNumberModel(1, 1, 10000, 1));

        String [] idiomas = {"Español", "Inglés", "Francés"};
        for (String idioma: idiomas) {
            comboIdiomas.addItem(idioma);
        }
    }
}
