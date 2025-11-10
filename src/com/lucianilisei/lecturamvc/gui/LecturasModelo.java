package com.lucianilisei.lecturamvc.gui;

import com.lucianilisei.lecturamvc.base.*;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
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

    public void altaLibro(String nombre, String autor, int numPaginas, LocalDate fechaLanzamiento, String editorial, String idioma, int numEdicion, boolean disponible, double precio) {
        Libro libro = new Libro(nombre, autor, numPaginas, fechaLanzamiento, editorial, idioma, numEdicion, disponible, precio);
        listaLectura.add(libro);
    }

    public void altaComic(String nombre, String autor, int numPaginas, LocalDate fechaLanzamiento, String editorial, String idioma, int numEdicion, boolean disponible, String ilustrador) {
        Comic comic = new Comic(nombre, autor, numPaginas, fechaLanzamiento, editorial, idioma, numEdicion, disponible, ilustrador);
        listaLectura.add(comic);
    }

    public void altaManga(String nombre, String autor, int numPaginas, LocalDate fechaLanzamiento, String editorial, String idioma, int numEdicion, boolean disponible, String paisOrigen) {
        Manga manga = new Manga(nombre, autor, numPaginas, fechaLanzamiento, editorial, idioma, numEdicion,disponible, paisOrigen);
        listaLectura. add(manga);
    }

    public void altaPoesia(String nombre, String autor, int numPaginas, LocalDate fechaLanzamiento, String editorial, String idioma, int numEdicion, boolean disponible, String estilo) {
        Poesia poesia = new Poesia(nombre, autor, numPaginas, fechaLanzamiento, editorial, idioma, numEdicion, disponible, estilo);
        listaLectura.add(poesia);
    }

    public void altaRevista(String nombre, String autor, int numPaginas, LocalDate fechaLanzamiento, String editorial, String idioma, int numEdicion, boolean disponible, String temaPrincipal) {
        Revista revista = new Revista(nombre, autor, numPaginas, fechaLanzamiento, editorial, idioma, numEdicion, disponible, temaPrincipal);
        listaLectura.add(revista);
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

            texto = documento.createTextNode(String.valueOf(lectura.isDisponible()));
            nodoDatos.appendChild(texto);

            if (lectura instanceof Libro) {
                nodoDatos = documento.createElement("precio");
                nodoLectura.appendChild(nodoDatos);

                texto = documento.createTextNode(String.valueOf(((Libro) lectura).getPrecio()));
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
}
