package com.example.backend;

public class Proizvod {
    private String naziv;
    private String opis;
    private int cena;
    private int promocija;
   

    public Proizvod(String naziv, String opis, int cena, int promocija) {
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.promocija = promocija;
        
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getPromocija() {
        return promocija;
    }

    public void setPromocija(int promocija) {
        this.promocija = promocija;
    }



    // Getteri i setteri
    
}