package com.lucianilisei.lecturamvc.gui;

import com.lucianilisei.lecturamvc.base.*;
import com.lucianilisei.lecturamvc.util.Utilidades;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.time.LocalDate;
import java.util.Properties;

public class LecturasControlador implements ActionListener, WindowListener, ListSelectionListener{
    private Ventana vista;
    private LecturasModelo modelo;
    private Utilidades utilidades;
    private File ultimaRutaExportada;
    private Lectura lecturaEditando;

    public LecturasControlador(Ventana vista, LecturasModelo modelo, Utilidades utilidades) {
        this.vista = vista;
        this.modelo = modelo;
        this.utilidades = utilidades;

        addActionListener(this);
        addWindowListener(this);
        addListSelectionListener(this);
    }

    public void addActionListener(ActionListener listener) {
        vista.botonNuevo.addActionListener(listener);
        vista.botonExportar.addActionListener(listener);
        vista.botonImportar.addActionListener(listener);
        vista.botonBuscar.addActionListener(listener);
        vista.botonEditar.addActionListener(listener);
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

    private void guardarConfiguracion() throws IOException {
        if (ultimaRutaExportada == null) {
            return;
        }
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());
        configuracion.store(new PrintWriter("lecturas.conf"), "Datos configuración lecturas");
    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("lecturas.conf"));
        ultimaRutaExportada = new File(configuracion.getProperty("ultimaRutaExportada"));
    }

    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada = ultimaRutaExportada;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()) {
            Lectura lecturaSeleccionada = (Lectura) vista.list1.getSelectedValue();
            lecturaEditando = lecturaSeleccionada;

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
                break;
            case "Poesia":
                vista.labelGeneral.setText("Estilo");
                vista.campoGeneral.setName("ESTILO");
                break;
            case "Revista":
                vista.labelGeneral.setText("Tema principal");
                vista.campoGeneral.setName("TEMA PRINCIPAL");
                break;
            case "Nuevo":
                if (utilidades.campoVacio(vista.campoNombre)) {
                    utilidades.alertaCampoVacio(vista.campoNombre);
                    break;
                } else if (utilidades.campoVacio(vista.campoAutor)) {
                    utilidades.alertaCampoVacio(vista.campoAutor);
                    break;
                } else if (utilidades.fechaVacia(vista.datePicker)) {
                    utilidades.alertaFechaVacia(vista.datePicker);
                    break;
                } else if (utilidades.campoVacio(vista.campoEditorial)) {
                    utilidades.alertaCampoVacio(vista.campoEditorial);
                    break;
                } else {
                    String disponible = vista.siDisponibleRadioButton.isSelected() ? "Si" : "No";
                    if (vista.radioLibro.isSelected()) {
                        if (utilidades.campoVacio(vista.campoGeneral)) {
                            utilidades.alertaCampoVacio(vista.campoGeneral);
                            break;
                        } else if (modelo.existeNombreLectura(vista.campoNombre.getText())) {
                            JOptionPane.showMessageDialog(null,
                                    "El nombre utilizado ya existe, intentelo con otro.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        modelo.altaLibro(vista.campoNombre.getText(), vista.campoAutor.getText(), (int) (vista.SpinnerNumPag.getValue()), vista.datePicker.getDate(), vista.campoEditorial.getText(),
                                vista.comboIdiomas.getSelectedItem().toString(), (int) vista.spinnerNumEdicion.getValue(), disponible, vista.campoGeneral.getText());


                    } else if (vista.radioComic.isSelected()) {
                        if (utilidades.campoVacio(vista.campoGeneral)) {
                            utilidades.alertaCampoVacio(vista.campoGeneral);
                            break;
                        } else if (modelo.existeNombreLectura(vista.campoNombre.getText())) {
                            JOptionPane.showMessageDialog(null,
                                    "El nombre utilizado ya existe, intentelo con otro.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        modelo.altaComic(vista.campoNombre.getText(), vista.campoAutor.getText(), (int) (vista.SpinnerNumPag.getValue()), vista.datePicker.getDate(), vista.campoEditorial.getText(),
                                vista.comboIdiomas.getSelectedItem().toString(), (int) vista.spinnerNumEdicion.getValue(), disponible, vista.campoGeneral.getText());


                    } else if (vista.radioManga.isSelected()) {
                        if (utilidades.campoVacio(vista.campoGeneral)) {
                            utilidades.alertaCampoVacio(vista.campoGeneral);
                            break;
                        } else if (modelo.existeNombreLectura(vista.campoNombre.getText())) {
                            JOptionPane.showMessageDialog(null,
                                    "El nombre utilizado ya existe, intentelo con otro.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        modelo.altaManga(vista.campoNombre.getText(), vista.campoAutor.getText(), (int) (vista.SpinnerNumPag.getValue()), vista.datePicker.getDate(), vista.campoEditorial.getText(),
                                vista.comboIdiomas.getSelectedItem().toString(), (int) vista.spinnerNumEdicion.getValue(), disponible, vista.campoGeneral.getText());


                    } else if (vista.radioPoesia.isSelected()) {
                        if (utilidades.campoVacio(vista.campoGeneral)) {
                            utilidades.alertaCampoVacio(vista.campoGeneral);
                            break;
                        } else if (modelo.existeNombreLectura(vista.campoNombre.getText())) {
                            JOptionPane.showMessageDialog(null,
                                    "Ya existe el nombre, cambielo.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        modelo.altaPoesia(vista.campoNombre.getText(), vista.campoAutor.getText(), (int) (vista.SpinnerNumPag.getValue()), vista.datePicker.getDate(), vista.campoEditorial.getText(),
                                vista.comboIdiomas.getSelectedItem().toString(), (int) vista.spinnerNumEdicion.getValue(), disponible, vista.campoGeneral.getText());


                    } else if (vista.radioRevista.isSelected()) {
                        if (utilidades.campoVacio(vista.campoGeneral)) {
                            utilidades.alertaCampoVacio(vista.campoGeneral);
                            break;
                        } else if (modelo.existeNombreLectura(vista.campoNombre.getText())) {
                            JOptionPane.showMessageDialog(null,
                                    "Ya existe el nombre, cambielo.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        modelo.altaRevista(vista.campoNombre.getText(), vista.campoAutor.getText(), (int) (vista.SpinnerNumPag.getValue()), vista.datePicker.getDate(), vista.campoEditorial.getText(),
                                vista.comboIdiomas.getSelectedItem().toString(), (int) vista.spinnerNumEdicion.getValue(), disponible, vista.campoGeneral.getText());


                    }
                    refrescar();
                    limpiarCampos();
                    break;
                }
            case "Exportar":
                JFileChooser selectorFichero2 = Utilidades.crearSelectorFichero(ultimaRutaExportada, "Archivo XML", "xml");
                int opt2 = selectorFichero2.showOpenDialog(null);
                if (opt2 == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.ExportarXML(selectorFichero2.getSelectedFile());
                        actualizarDatosConfiguracion(selectorFichero2.getSelectedFile());
                        limpiarCampos();
                        break;
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                        break;
                    } catch (TransformerException ex) {
                        ex.printStackTrace();
                        break;
                    }
                }
                limpiarCampos();
                break;
            case "Importar":
                JFileChooser selectorFichero = Utilidades.crearSelectorFichero(ultimaRutaExportada, "Archivo XML", "xml");
                int opt = selectorFichero.showOpenDialog(null);
                if (opt == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                        refrescar();
                        limpiarCampos();
                        break;
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                        break;
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        break;
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            case "Buscar":
                modelo.buscarLectura(vista.campoBuscar.getText());
                break;
            case "Editar":
                if (lecturaEditando == null) {
                    JOptionPane.showMessageDialog(null, "No hay ninguna lectura seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                if (utilidades.campoVacio(vista.campoNombre)) {
                    utilidades.alertaCampoVacio(vista.campoNombre);
                    break;
                } else if (utilidades.campoVacio(vista.campoAutor)) {
                    utilidades.alertaCampoVacio(vista.campoAutor);
                    break;
                } else if (utilidades.fechaVacia(vista.datePicker)) {
                    utilidades.alertaFechaVacia(vista.datePicker);
                    break;
                } else if (utilidades.campoVacio(vista.campoEditorial)) {
                    utilidades.alertaCampoVacio(vista.campoEditorial);
                    break;
                } else if (utilidades.campoVacio(vista.campoGeneral)) {
                    utilidades.alertaCampoVacio(vista.campoGeneral);
                }   else if (modelo.existeNombreLectura(vista.campoNombre.getText())) {
                    JOptionPane.showMessageDialog(null,
                            "Ya existe el nombre, cambielo.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    String nombre = vista.campoNombre.getText();
                    String autor = vista.campoAutor.getText();
                    int numPag = (int) vista.SpinnerNumPag.getValue();
                    LocalDate fecha = vista.datePicker.getDate();
                    String editorial = vista.campoEditorial.getText();
                    String idioma = vista.comboIdiomas.getSelectedItem().toString();
                    int edicion = (int) vista.spinnerNumEdicion.getValue();
                    String disponible = vista.siDisponibleRadioButton.isSelected() ? "Si" : "No";
                    String campoGeneral = vista.campoGeneral.getText();

                    modelo.editarLectura(lecturaEditando, nombre, autor, numPag, fecha, editorial, idioma, edicion, disponible, campoGeneral);

                    refrescar();
                    limpiarCampos();
                    lecturaEditando = null;
                    break;
                }

        }

    }



    private void refrescar() {
        vista.dlmLectura.clear();
        for (Lectura lectura: modelo.obtenerLecturas()) {
            vista.dlmLectura.addElement(lectura);
        }
    }

    private void limpiarCampos() {
        vista.campoNombre.setText("");
        vista.campoAutor.setText("");
        vista.SpinnerNumPag.setValue(1);
        vista.datePicker.setDate(null);
        vista.campoEditorial.setText("");
        vista.comboIdiomas.setSelectedIndex(0);
        vista.spinnerNumEdicion.setValue(1);
        vista.siDisponibleRadioButton.setSelected(true);
        vista.campoGeneral.setText("");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        int resp = utilidades.mensajeConfirmacion("¿Quiere cerrar la ventana?", "Exit");
        if (resp == JOptionPane.OK_OPTION) {
            try {
                guardarConfiguracion();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

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
