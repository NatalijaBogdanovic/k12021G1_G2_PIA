package com.example.backend;

public class NarudzbinaSadrzi {
    private String proizvod;
    private int kolicina;
    private int narudzbina;

    public NarudzbinaSadrzi(String proizvod, int kolicina, int narudzbina) {
        this.proizvod = proizvod;
        this.kolicina = kolicina;
        this.narudzbina = narudzbina;
    }
    

    public String getProizvod() {
        return proizvod;
    }

    public int getKolicina() {
        return kolicina;
    }
    public int getNarudzbina() {
        return narudzbina;
    }
}
