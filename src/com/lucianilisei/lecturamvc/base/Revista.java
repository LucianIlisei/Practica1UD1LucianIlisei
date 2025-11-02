package com.lucianilisei.lecturamvc.base;

import java.time.LocalDate;

public class Revista extends Lectura{
    private int numeroEdicion;

    public Revista(String nombre, String autor, int numPaginas, LocalDate fechaLanzamiento, String editorial, int numeroEdicion) {
        super(nombre, autor, numPaginas, fechaLanzamiento, editorial);
        this.numeroEdicion = numeroEdicion;
    }

    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    public void setNumeroEdicion(int numeroEdicion) {
        this.numeroEdicion = numeroEdicion;
    }
}
