package com.lucianilisei.lecturamvc.base;

import java.time.LocalDate;

public class Comic extends Lectura{
    private String ilustrador;

    public Comic(String nombre, String autor, int numPaginas, LocalDate fechaLanzamiento, String editorial, String ilustrador) {
        super(nombre, autor, numPaginas, fechaLanzamiento, editorial);
        this.ilustrador = ilustrador;
    }

    public String getIlustrador() {
        return ilustrador;
    }

    public void setIlustrador(String ilustrador) {
        this.ilustrador = ilustrador;
    }
}
