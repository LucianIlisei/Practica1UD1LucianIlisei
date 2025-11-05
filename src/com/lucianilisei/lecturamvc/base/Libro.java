package com.lucianilisei.lecturamvc.base;

import java.time.LocalDate;

public class Libro extends Lectura{
    private double precio;

    public Libro() {
        super();
    }

    public Libro(String nombre, String autor, int numPaginas, LocalDate fechaLanzamiento, String editorial, String idioma, int numEdicion, boolean disponible, double precio) {
        super(nombre, autor, numPaginas, fechaLanzamiento, editorial, idioma, numEdicion, disponible);
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
