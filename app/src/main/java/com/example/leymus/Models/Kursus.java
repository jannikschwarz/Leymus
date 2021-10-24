package com.example.leymus.Models;

import java.util.Date;

public class Kursus {
    private String tema;
    private int antal;
    private int pris;
    private boolean egnetForBørn;
    private int ID;
    private Date dato;
    private String level;
    private String tid;

    public void Kurses(){
        this.tema = "";
        this.level = "";
    }

    //Setters - Getters
    public Date getDato() {
        return dato;
    }

    public int getAntal() {
        return antal;
    }

    public int getID() {
        return ID;
    }

    public int getPris() {
        return pris;
    }

    public String getLevel() {
        return level;
    }

    public String getTema() {
        return tema;
    }

    public boolean getEgnetForBørn(){ return egnetForBørn; }

    public String getTid() { return tid; }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public void setDato(Date dato) {
        this.dato = dato;
    }

    public void setEgnetForBørn(boolean egnetForBørn) {
        this.egnetForBørn = egnetForBørn;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
