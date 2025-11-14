package com.lucianilisei.lecturamvc.gui;

import com.lucianilisei.lecturamvc.base.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class LecturasModelo {
    private ArrayList<Lectura> listaLectura;

    public LecturasModelo() {
        listaLectura =  new ArrayList<Lectura>();
    }

    public ArrayList<Lectura> obtenerLecturas() {
        return listaLectura;
    }

    public void altaLibro(String nombre, String autor, int numPaginas, LocalDate fechaLanzamiento, String editorial, String idioma, int numEdicion, String disponible, String precio) {
        Libro libro = new Libro(nombre, autor, numPaginas, fechaLanzamiento, editorial, idioma, numEdicion, disponible, precio);
        listaLectura.add(libro);
    }

    public void altaComic(String nombre, String autor, int numPaginas, LocalDate fechaLanzamiento, String editorial, String idioma, int numEdicion, String disponible, String ilustrador) {
        Comic comic = new Comic(nombre, autor, numPaginas, fechaLanzamiento, editorial, idioma, numEdicion, disponible, ilustrador);
        listaLectura.add(comic);
    }

    public void altaManga(String nombre, String autor, int numPaginas, LocalDate fechaLanzamiento, String editorial, String idioma, int numEdicion, String disponible, String paisOrigen) {
        Manga manga = new Manga(nombre, autor, numPaginas, fechaLanzamiento, editorial, idioma, numEdicion,disponible, paisOrigen);
        listaLectura.add(manga);
    }

    public void altaPoesia(String nombre, String autor, int numPaginas, LocalDate fechaLanzamiento, String editorial, String idioma, int numEdicion, String disponible, String estilo) {
        Poesia poesia = new Poesia(nombre, autor, numPaginas, fechaLanzamiento, editorial, idioma, numEdicion, disponible, estilo);
        listaLectura.add(poesia);
    }

    public void altaRevista(String nombre, String autor, int numPaginas, LocalDate fechaLanzamiento, String editorial, String idioma, int numEdicion, String disponible, String temaPrincipal) {
        Revista revista = new Revista(nombre, autor, numPaginas, fechaLanzamiento, editorial, idioma, numEdicion, disponible, temaPrincipal);
        listaLectura.add(revista);
    }

    public boolean existeNombreLectura(String nombreLectura) {
        for (Lectura unVehiculo : listaLectura) {
            if (unVehiculo.getNombre().equals(nombreLectura)) {
                return true;
            }
        }
        return false;
    }


    public void ExportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);

        Element raiz = documento.createElement("Lecturas");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoLectura = null;
        Element nodoDatos = null;
        Text texto = null;

        for (Lectura lectura : listaLectura) {
            if (lectura instanceof Libro) {
                nodoLectura = documento.createElement("Libro");
            } else if (lectura instanceof Comic) {
                nodoLectura = documento.createElement("Comic");
            } else if (lectura instanceof Manga) {
                nodoLectura = documento.createElement("Manga");
            } else if (lectura instanceof Poesia) {
                nodoLectura = documento.createElement("Poesia");
            } else {
                nodoLectura = documento.createElement("Revista");
            }
            raiz.appendChild(nodoLectura);

            // Nombre
            nodoDatos = documento.createElement("nombre");
            nodoLectura.appendChild(nodoDatos);

            texto = documento.createTextNode(lectura.getNombre());
            nodoDatos.appendChild(texto);

            // Autor
            nodoDatos = documento.createElement("autor");
            nodoLectura.appendChild(nodoDatos);

            texto = documento.createTextNode(lectura.getAutor());
            nodoDatos.appendChild(texto);

            // Numero de paginas
            nodoDatos = documento.createElement("numPaginas");
            nodoLectura.appendChild(nodoDatos);

            texto = documento.createTextNode(String.valueOf(lectura.getNumPaginas()));
            nodoDatos.appendChild(texto);

            // Fecha de lanzamientos
            nodoDatos = documento.createElement("fechaLanzamiento");
            nodoLectura.appendChild(nodoDatos);

            texto = documento.createTextNode(lectura.getFechaLanzamiento().toString());
            nodoDatos.appendChild(texto);

            // Editorial
            nodoDatos = documento.createElement("editorial");
            nodoLectura.appendChild(nodoDatos);

            texto = documento.createTextNode(lectura.getEditorial());
            nodoDatos.appendChild(texto);

            // Idioma
            nodoDatos = documento.createElement("idioma");
            nodoLectura.appendChild(nodoDatos);

            texto = documento.createTextNode(lectura.getIdioma());
            nodoDatos.appendChild(texto);

            // Numero de edicion
            nodoDatos = documento.createElement("numEdicion");
            nodoLectura.appendChild(nodoDatos);

            texto = documento.createTextNode(String.valueOf(lectura.getNumEdicion()));
            nodoDatos.appendChild(texto);

            // Disponible
            nodoDatos = documento.createElement("disponible");
            nodoLectura.appendChild(nodoDatos);

            texto = documento.createTextNode(String.valueOf(lectura.getDisponible()));
            nodoDatos.appendChild(texto);

            if (lectura instanceof Libro) {
                nodoDatos = documento.createElement("precio");
                nodoLectura.appendChild(nodoDatos);

                texto = documento.createTextNode(String.valueOf(((Libro) lectura).getGenero()));
                nodoDatos.appendChild(texto);
            } else if (lectura instanceof Comic) {
                nodoDatos = documento.createElement("ilustrador");
                nodoLectura.appendChild(nodoDatos);

                texto = documento.createTextNode(((Comic) lectura).getIlustrador());
                nodoDatos.appendChild(texto);
            } else if (lectura instanceof Manga) {
                nodoDatos = documento.createElement("paisOrigen");
                nodoLectura.appendChild(nodoDatos);

                texto = documento.createTextNode(((Manga) lectura).getPaisOrigen());
                nodoDatos.appendChild(texto);
            } else if (lectura instanceof Poesia) {
                nodoDatos = documento.createElement("estilo");
                nodoLectura.appendChild(nodoDatos);

                texto = documento.createTextNode(((Poesia) lectura).getEstilo());
                nodoDatos.appendChild(texto);
            } else if (lectura instanceof Revista){
                nodoDatos = documento.createElement("temaPrincipal");
                nodoLectura.appendChild(nodoDatos);

                texto = documento.createTextNode(((Revista) lectura).getTemaPrincipal());
                nodoDatos.appendChild(texto);
            }
        }

        Source source = new DOMSource(documento);
        Result resultado =  new StreamResult(fichero);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, resultado);

    }

    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaLectura = new ArrayList<Lectura>();
        Libro nuevoLibro = null;
        Comic nuevoComic = null;
        Manga nuevoManga = null;
        Poesia nuevaPoesia = null;
        Revista nuevaRevista = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);

        NodeList listaElementos = documento.getElementsByTagName("*");

        for(int i = 0; i < listaElementos.getLength(); i++) {
            Element nodoLectura = (Element) listaElementos.item(i);

            if (nodoLectura.getTagName().equals("Libro")) {
                nuevoLibro = new Libro();
                nuevoLibro.setNombre(nodoLectura.getChildNodes().item(0).getTextContent());
                nuevoLibro.setAutor(nodoLectura.getChildNodes().item(1).getTextContent());
                nuevoLibro.setNumPaginas(Integer.parseInt(nodoLectura.getChildNodes().item(2).getTextContent()));
                nuevoLibro.setFechaLanzamiento(LocalDate.parse(nodoLectura.getChildNodes().item(3).getTextContent()));
                nuevoLibro.setEditorial(nodoLectura.getChildNodes().item(4).getTextContent());
                nuevoLibro.setIdioma(nodoLectura.getChildNodes().item(5).getTextContent());
                nuevoLibro.setNumEdicion(Integer.parseInt(nodoLectura.getChildNodes().item(6).getTextContent()));
                nuevoLibro.setDisponible(nodoLectura.getChildNodes().item(7).getTextContent());
                nuevoLibro.setGenero(nodoLectura.getChildNodes().item(8).getTextContent());
                listaLectura.add(nuevoLibro);
            } else if (nodoLectura.getTagName().equals("Comic")) {
                nuevoComic = new Comic();
                nuevoComic.setNombre(nodoLectura.getChildNodes().item(0).getTextContent());
                nuevoComic.setAutor(nodoLectura.getChildNodes().item(1).getTextContent());
                nuevoComic.setNumPaginas(Integer.parseInt(nodoLectura.getChildNodes().item(2).getTextContent()));
                nuevoComic.setFechaLanzamiento(LocalDate.parse(nodoLectura.getChildNodes().item(3).getTextContent()));
                nuevoComic.setEditorial(nodoLectura.getChildNodes().item(4).getTextContent());
                nuevoComic.setIdioma(nodoLectura.getChildNodes().item(5).getTextContent());
                nuevoComic.setNumEdicion(Integer.parseInt(nodoLectura.getChildNodes().item(6).getTextContent()));
                nuevoComic.setDisponible(nodoLectura.getChildNodes().item(7).getTextContent());
                nuevoComic.setIlustrador(nodoLectura.getChildNodes().item(8).getTextContent());
                listaLectura.add(nuevoComic);
            } else if (nodoLectura.getTagName().equals("Manga")) {
                nuevoManga = new Manga();
                nuevoManga.setNombre(nodoLectura.getChildNodes().item(0).getTextContent());
                nuevoManga.setAutor(nodoLectura.getChildNodes().item(1).getTextContent());
                nuevoManga.setNumPaginas(Integer.parseInt(nodoLectura.getChildNodes().item(2).getTextContent()));
                nuevoManga.setFechaLanzamiento(LocalDate.parse(nodoLectura.getChildNodes().item(3).getTextContent()));
                nuevoManga.setEditorial(nodoLectura.getChildNodes().item(4).getTextContent());
                nuevoManga.setIdioma(nodoLectura.getChildNodes().item(5).getTextContent());
                nuevoManga.setNumEdicion(Integer.parseInt(nodoLectura.getChildNodes().item(6).getTextContent()));
                nuevoManga.setDisponible(nodoLectura.getChildNodes().item(7).getTextContent());
                nuevoManga.setPaisOrigen(nodoLectura.getChildNodes().item(8).getTextContent());
                listaLectura.add(nuevoManga);
            } else if (nodoLectura.getTagName().equals("Poesia")) {
                nuevaPoesia = new Poesia();
                nuevaPoesia.setNombre(nodoLectura.getChildNodes().item(0).getTextContent());
                nuevaPoesia.setAutor(nodoLectura.getChildNodes().item(1).getTextContent());
                nuevaPoesia.setNumPaginas(Integer.parseInt(nodoLectura.getChildNodes().item(2).getTextContent()));
                nuevaPoesia.setFechaLanzamiento(LocalDate.parse(nodoLectura.getChildNodes().item(3).getTextContent()));
                nuevaPoesia.setEditorial(nodoLectura.getChildNodes().item(4).getTextContent());
                nuevaPoesia.setIdioma(nodoLectura.getChildNodes().item(5).getTextContent());
                nuevaPoesia.setNumEdicion(Integer.parseInt(nodoLectura.getChildNodes().item(6).getTextContent()));
                nuevaPoesia.setDisponible(nodoLectura.getChildNodes().item(7).getTextContent());
                nuevaPoesia.setEstilo(nodoLectura.getChildNodes().item(8).getTextContent());
                listaLectura.add(nuevaPoesia);
            } else if (nodoLectura.getTagName().equals("Revista")) {
                nuevaRevista = new Revista();
                nuevaRevista.setNombre(nodoLectura.getChildNodes().item(0).getTextContent());
                nuevaRevista.setAutor(nodoLectura.getChildNodes().item(1).getTextContent());
                nuevaRevista.setNumPaginas(Integer.parseInt(nodoLectura.getChildNodes().item(2).getTextContent()));
                nuevaRevista.setFechaLanzamiento(LocalDate.parse(nodoLectura.getChildNodes().item(3).getTextContent()));
                nuevaRevista.setEditorial(nodoLectura.getChildNodes().item(4).getTextContent());
                nuevaRevista.setIdioma(nodoLectura.getChildNodes().item(5).getTextContent());
                nuevaRevista.setNumEdicion(Integer.parseInt(nodoLectura.getChildNodes().item(6).getTextContent()));
                nuevaRevista.setDisponible(nodoLectura.getChildNodes().item(7).getTextContent());
                nuevaRevista.setTemaPrincipal(nodoLectura.getChildNodes().item(8).getTextContent());
                listaLectura.add(nuevaRevista);
            }
        }
    }

    public void buscarLectura(String nombreLectura) {
        for (Lectura lectura : listaLectura) {
            if (lectura.getNombre().equals(nombreLectura)) {
                String mensaje =
                        "Nombre: " + lectura.getNombre() +
                                "\nAutor: " + lectura.getAutor() +
                                "\nNúmero de páginas: " + lectura.getNumPaginas() +
                                "\nFecha de lanzamiento: " + lectura.getFechaLanzamiento() +
                                "\nEditorial: " + lectura.getEditorial() +
                                "\nIdioma: " + lectura.getIdioma() +
                                "\nNúmero de edición: " + lectura.getNumEdicion() +
                                "\nDisponible: " + lectura.getDisponible();
                if (lectura instanceof Libro) {
                    mensaje += "\nGénero: " + ((Libro) lectura).getGenero();
                } else if (lectura instanceof Comic) {
                    mensaje += "\nIlustrador: " + ((Comic) lectura).getIlustrador();
                } else if (lectura instanceof Manga) {
                    mensaje += "\nPaís de origen: " + ((Manga) lectura).getPaisOrigen();
                } else if (lectura instanceof Poesia) {
                    mensaje += "\nEstilo: " + ((Poesia) lectura).getEstilo();
                } else if (lectura instanceof Revista) {
                    mensaje += "\nTema principal: " + ((Revista) lectura).getTemaPrincipal();
                }
                JOptionPane.showMessageDialog(null, mensaje, "Lectura encontrada", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null,
                "No se encontró ninguna lectura con ese nombre.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public void editarLectura(Lectura lecturaEditando, String nombre, String autor, int numPaginas, LocalDate fecha, String editorial, String idioma, int numEdicion, String disponible, String general) {
        lecturaEditando.setNombre(nombre);
        lecturaEditando.setAutor(autor);
        lecturaEditando.setNumPaginas(numPaginas);
        lecturaEditando.setFechaLanzamiento(fecha);
        lecturaEditando.setEditorial(editorial);
        lecturaEditando.setIdioma(idioma);
        lecturaEditando.setNumEdicion(numEdicion);
        lecturaEditando.setDisponible(disponible);
        if (lecturaEditando instanceof Libro) {
            ((Libro) lecturaEditando).setGenero(general);
        } else if (lecturaEditando instanceof Comic) {
            ((Comic) lecturaEditando).setIlustrador(general);
        } else if (lecturaEditando instanceof Manga) {
            ((Manga) lecturaEditando).setPaisOrigen(general);
        } else if (lecturaEditando instanceof Poesia) {
            ((Poesia) lecturaEditando).setEstilo(general);
        } else if (lecturaEditando instanceof Revista) {
            ((Revista) lecturaEditando).setTemaPrincipal(general);
        }
    }



}
