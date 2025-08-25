package com.example.backend;

import java.sql.Date;

public class Korisnik {
    private String kor_ime;
    private String ime;
    private String prezime;
    private String mejl;
    private Date datum_rodjenja;
    private String tip;
    private String lozinka;

    

    public Korisnik(String kor_ime, String ime, String prezime, String mejl, Date datum_rodjenja, String tip,
            String lozinka) {
        this.kor_ime = kor_ime;
        this.ime = ime;
        this.prezime = prezime;
        this.mejl = mejl;
        this.datum_rodjenja = datum_rodjenja;
        this.tip = tip;
        this.lozinka = lozinka;
    }

    // Getters and Setters
    public String getKor_ime() {
        return kor_ime;
    }

    public void setKor_ime(String kor_ime) {
        this.kor_ime = kor_ime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getMejl() {
        return mejl;
    }

    public void setMejl(String mejl) {
        this.mejl = mejl;
    }

    public Date getDatum_rodjenja() {
        return datum_rodjenja;
    }

    public void setDatum_rodjenja(Date datum_rodjenja) {
        this.datum_rodjenja = datum_rodjenja;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
}
