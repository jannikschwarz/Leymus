package com.example.leymus.Models;

import java.util.Date;

public class Event {
    private String info;
    private Date dato;
    private String emne;
    private char type;

    public void Event(){
        this.info = "";
        this.emne = "";
    }

    public Date getDato() {
        return dato;
    }

    public char getType() {
        return type;
    }

    public String getEmne() {
        return emne;
    }

    public String getInfo() {
        return info;
    }

    public void setDato(Date dato) {
        this.dato = dato;
    }

    public void setType(char type) {
        this.type = type;
    }

    public void setEmne(String emne) {
        this.emne = emne;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
