package com.lucianilisei.lecturamvc.util;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Utilidades {
    public boolean campoVacio(JTextField campo) {
        return campo.getText().isBlank();
    }

    public boolean fechaVacia(DatePicker campo) {
        return campo.getDate() == null;
    }

    public void alertaCampoVacio (JTextField campo) {
        JOptionPane.showMessageDialog(null, "El campo " + campo.getName() + " no puede estar vacio.");
    }

    public void alertaFechaVacia (DatePicker campo) {
        JOptionPane.showMessageDialog(null, "El campo " + campo.getName() + " no puede estar vacio.");
    }


    public static JFileChooser crearSelectorFichero(File rutaDefecto, String tipoArchivo, String extension) {
        JFileChooser selectorFichero = new JFileChooser();
        if (rutaDefecto != null) {
            selectorFichero.setCurrentDirectory(rutaDefecto);
        }
        if (extension != null) {
            FileNameExtensionFilter filtro = new FileNameExtensionFilter(tipoArchivo, extension);
            selectorFichero.setFileFilter(filtro);
        }
        return selectorFichero;
    }

    public static int mensajeConfirmacion(String mensaje,String titulo) {
        return JOptionPane.showConfirmDialog(null,mensaje,titulo,JOptionPane.YES_NO_OPTION);
    }
}
