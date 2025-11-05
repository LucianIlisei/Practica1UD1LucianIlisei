package com.lucianilisei.lecturamvc.base;

import java.time.LocalDate;

public class Manga extends Lectura{
    private String paisOrigen;

    public Manga() {
        super();
    }

    public Manga(String nombre, String autor, int numPaginas, LocalDate fechaLanzamiento, String editorial, String paisOrigen) {
        super(nombre, autor, numPaginas, fechaLanzamiento, editorial);
        this.paisOrigen = paisOrigen;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }
}
