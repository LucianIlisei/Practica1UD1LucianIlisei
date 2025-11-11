package com.lucianilisei.lecturamvc.gui;

import com.lucianilisei.lecturamvc.base.Lectura;

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
    public void actionPerformed(ActionEvent actionEvent) {

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

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {

    }
}
