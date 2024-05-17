package com.tarea7.examen;

public class ticket {
    private int id, entradas,precio, total;
    private String partido;

    public ticket(int id, int entradas, int precio, int total, String partido) {
        this.id = id;
        this.entradas = entradas;
        this.precio = precio;
        this.total = total;
        this.partido = partido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEntradas() {
        return entradas;
    }

    public void setEntradas(int entradas) {
        this.entradas = entradas;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }
}
