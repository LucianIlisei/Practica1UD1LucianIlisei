package com.lucianilisei.lecturamvc.base;

import java.time.LocalDate;

public class Lectura {
    private String nombre;
    private String autor;
    private int numPaginas;
    private LocalDate fechaLanzamiento;
    private String editorial;
    private String idioma;
    private int numEdicion;
    private boolean disponible;

    public Lectura() {

    }

    public Lectura(String nombre, String autor, int numPaginas, LocalDate fechaLanzamiento, String editorial, String idioma, int numEdicion, boolean disponible) {
        this.nombre = nombre;
        this.autor = autor;
        this.numPaginas = numPaginas;
        this.fechaLanzamiento = fechaLanzamiento;
        this.editorial = editorial;
        this.idioma = idioma;
        this.numEdicion = numEdicion;
        this.disponible = disponible;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getNumEdicion() {
        return numEdicion;
    }

    public void setNumEdicion(int numEdicion) {
        this.numEdicion = numEdicion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
