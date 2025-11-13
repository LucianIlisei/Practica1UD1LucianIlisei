package com.lucianilisei.lecturamvc.gui;

import com.lucianilisei.lecturamvc.base.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

public class LecturasControlador implements ActionListener, WindowListener, ListSelectionListener{
    private Ventana vista;
    private LecturasModelo modelo;
    private File ultimaRutaExportada;

    public LecturasControlador(Ventana vista, LecturasModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        addActionListener(this);
        addWindowListener(this);
        addListSelectionListener(this);
    }

    public void addActionListener(ActionListener listener) {
        vista.botonNuevo.addActionListener(listener);
        vista.botonExportar.addActionListener(listener);
        vista.botonImportar.addActionListener(listener);
        vista.radioLibro.addActionListener(listener);
        vista.radioComic.addActionListener(listener);
        vista.radioManga.addActionListener(listener);
        vista.radioPoesia.addActionListener(listener);
        vista.radioRevista.addActionListener(listener);
    }

    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

    private void addListSelectionListener(ListSelectionListener listener) {
        vista.list1.addListSelectionListener(listener);
    }

    private void refrescar() {
        vista.dlmLectura.clear();
        for (Lectura lectura: modelo.obtenerLecturas()) {
            vista.dlmLectura.addElement(lectura);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()) {
            Lectura lecturaSeleccionada = (Lectura) vista.list1.getSelectedValue();
            vista.campoNombre.setText(lecturaSeleccionada.getNombre());
            vista.campoAutor.setText(lecturaSeleccionada.getAutor());
            vista.SpinnerNumPag.setValue(lecturaSeleccionada.getNumPaginas());
            vista.datePicker.setDate(lecturaSeleccionada.getFechaLanzamiento());
            vista.campoEditorial.setText(lecturaSeleccionada.getEditorial());
            vista.comboIdiomas.setSelectedItem(lecturaSeleccionada.getIdioma());
            vista.spinnerNumEdicion.setValue(lecturaSeleccionada.getNumEdicion());
            if (lecturaSeleccionada.getDisponible().equals("Si")) {
                vista.siDisponibleRadioButton.setSelected(true);
            } else {
                vista.noDisponibleRadioButton.setSelected(true);
            }
            if (lecturaSeleccionada instanceof Libro) {
                vista.radioLibro.doClick();
                vista.labelGeneral.setText("Género");
                vista.campoGeneral.setText(((Libro) lecturaSeleccionada).getGenero());
            } else if (lecturaSeleccionada instanceof Comic) {
                vista.radioComic.doClick();
                vista.labelGeneral.setText("Ilustrador");
                vista.campoGeneral.setText(((Comic) lecturaSeleccionada).getIlustrador());
            } else if (lecturaSeleccionada instanceof Manga) {
                vista.radioManga.doClick();
                vista.labelGeneral.setText("Pais de origen");
                vista.campoGeneral.setText(((Manga) lecturaSeleccionada).getPaisOrigen());
            } else if (lecturaSeleccionada instanceof Poesia) {
                vista.radioPoesia.doClick();
                vista.labelGeneral.setText("Estilo");
                vista.campoGeneral.setText(((Poesia) lecturaSeleccionada).getEstilo());
            } else if (lecturaSeleccionada instanceof Revista) {
                vista.radioRevista.doClick();
                vista.labelGeneral.setText("Tema principal");
                vista.campoGeneral.setText(((Revista) lecturaSeleccionada).getTemaPrincipal());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionComand = e.getActionCommand();
        System.out.println("Botón pulsado: " + actionComand);
        switch (actionComand) {
            case "Nuevo":
                String disponible = vista.siDisponibleRadioButton.isSelected() ? "Si" : "No";
                if (vista.radioLibro.isSelected()) {
                    modelo.altaLibro(vista.campoNombre.getText(), vista.campoAutor.getText(), (int) (vista.SpinnerNumPag.getValue()), vista.datePicker.getDate(), vista.campoEditorial.getText(),
                            vista.comboIdiomas.getSelectedItem().toString(), (int) vista.spinnerNumEdicion.getValue(), disponible, vista.campoGeneral.getText());
                } else if (vista.radioComic.isSelected()) {
                    modelo.altaComic(vista.campoNombre.getText(), vista.campoAutor.getText(), (int) (vista.SpinnerNumPag.getValue()), vista.datePicker.getDate(), vista.campoEditorial.getText(),
                            vista.comboIdiomas.getSelectedItem().toString(), (int) vista.spinnerNumEdicion.getValue(), disponible, vista.campoGeneral.getText());
                } else if (vista.radioManga.isSelected()) {
                    modelo.altaManga(vista.campoNombre.getText(), vista.campoAutor.getText(), (int) (vista.SpinnerNumPag.getValue()), vista.datePicker.getDate(), vista.campoEditorial.getText(),
                            vista.comboIdiomas.getSelectedItem().toString(), (int) vista.spinnerNumEdicion.getValue(), disponible, vista.campoGeneral.getText());
                } else if (vista.radioPoesia.isSelected()) {
                    modelo.altaPoesia(vista.campoNombre.getText(), vista.campoAutor.getText(), (int) (vista.SpinnerNumPag.getValue()), vista.datePicker.getDate(), vista.campoEditorial.getText(),
                            vista.comboIdiomas.getSelectedItem().toString(), (int) vista.spinnerNumEdicion.getValue(), disponible, vista.campoGeneral.getText());
                } else if (vista.radioRevista.isSelected()) {
                    modelo.altaRevista(vista.campoNombre.getText(), vista.campoAutor.getText(), (int) (vista.SpinnerNumPag.getValue()), vista.datePicker.getDate(), vista.campoEditorial.getText(),
                            vista.comboIdiomas.getSelectedItem().toString(), (int) vista.spinnerNumEdicion.getValue(), disponible, vista.campoGeneral.getText());
                }

                refrescar();
            case "Libro":
                vista.labelGeneral.setText("Género");
                vista.campoGeneral.setName("LIBRO");
                break;
            case "Comic":
                vista.labelGeneral.setText("Ilustradór");
                vista.campoGeneral.setName("ILUSTRADOR");
                break;
            case "Manga":
                vista.labelGeneral.setText("Pais de origen");
                vista.campoGeneral.setName("PAIS DE ORIGEN");
            case "Poesia":
                vista.labelGeneral.setText("Estilo");
                vista.campoGeneral.setName("ESTILO");
            case "Revista":
                vista.labelGeneral.setText("Tema principal");
                vista.campoGeneral.setName("TEMA PRINCIPAL");


        }

    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
