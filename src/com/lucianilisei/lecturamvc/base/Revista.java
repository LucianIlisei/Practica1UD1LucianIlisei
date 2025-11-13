package com.lucianilisei.lecturamvc.base;

import java.time.LocalDate;

public class Revista extends Lectura{
    private String temaPrincipal;

    public Revista() {
        super();
    }

    public Revista(String nombre, String autor, int numPaginas, LocalDate fechaLanzamiento, String editorial, String idioma, int numEdicion, String disponible, String temaPrincipal) {
        super(nombre, autor, numPaginas, fechaLanzamiento, editorial, idioma, numEdicion, disponible);
        this.temaPrincipal = temaPrincipal;
    }

    public String getTemaPrincipal() {
        return temaPrincipal;
    }

    public void setTemaPrincipal(String temaPrincipal) {
        this.temaPrincipal = temaPrincipal;
    }
}
