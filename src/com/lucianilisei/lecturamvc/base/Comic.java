package com.lucianilisei.lecturamvc.base;

import java.time.LocalDate;

public class Comic extends Lectura{
    private String ilustrador;

    public Comic () {
        super();
    }

    public Comic(String nombre, String autor, int numPaginas, LocalDate fechaLanzamiento, String editorial, String idioma, int numEdicion, String disponible, String ilustrador) {
        super(nombre, autor, numPaginas, fechaLanzamiento, editorial, idioma, numEdicion, disponible);
        this.ilustrador = ilustrador;
    }

    public String getIlustrador() {
        return ilustrador;
    }

    public void setIlustrador(String ilustrador) {
        this.ilustrador = ilustrador;
    }
}
