package com.lucianilisei.lecturamvc.base;

import java.time.LocalDate;

public class Lectura {
    private String nombre;
    private String autor;
    private int numPaginas;
    private LocalDate fechaLanzamiento;
    private String editorial;

    public Lectura(String nombre, String autor, int numPaginas, LocalDate fechaLanzamiento, String editorial) {
        this.nombre = nombre;
        this.autor = autor;
        this.numPaginas = numPaginas;
        this.fechaLanzamiento = fechaLanzamiento;
        this.editorial = editorial;
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
}
