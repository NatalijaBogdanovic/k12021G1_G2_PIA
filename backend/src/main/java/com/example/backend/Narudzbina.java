package com.example.backend;

import java.time.LocalDate;


public class Narudzbina {
    private int idN;
    private String kupac;
    private LocalDate datum;

    public Narudzbina(int idN, String kupac, LocalDate datum) {
        this.idN = idN;
        this.kupac = kupac;
        this.datum = datum;
    }

    public int getIdN() {
        return idN;
    }
    public void setIdN(int idN) {
        this.idN = idN;
    }
    public String getKupac() {
        return kupac;
    }

    public LocalDate getDatum() {
        return datum;
    }
    public void setKupac(String kupac) {
        this.kupac = kupac;
    }
    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}
