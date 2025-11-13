package com.lucianilisei.lecturamvc.base;

import java.time.LocalDate;

public class Libro extends Lectura{
    private String genero;

    public Libro() {
        super();
    }

    public Libro(String nombre, String autor, int numPaginas, LocalDate fechaLanzamiento, String editorial, String idioma, int numEdicion, String disponible, String genero) {
        super(nombre, autor, numPaginas, fechaLanzamiento, editorial, idioma, numEdicion, disponible);
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
