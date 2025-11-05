package com.lucianilisei.lecturamvc.base;

import java.time.LocalDate;

public class Poesia extends Lectura{
    private String estilo;

    public Poesia() {
        super();
    }

    public Poesia(String nombre, String autor, int numPaginas, LocalDate fechaLanzamiento, String editorial, String estilo) {
        super(nombre, autor, numPaginas, fechaLanzamiento, editorial);
        this.estilo = estilo;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }
}
