package com.example.backend;

import java.time.LocalDate;
import java.util.List;

public class NarudzbinaDTO {
    private String kupac;
    private LocalDate datum;
    private List<NarudzbinaSadrzi> sadrzaj;

    public NarudzbinaDTO(String kupac, LocalDate datum, List<NarudzbinaSadrzi> sadrzaj) {
        this.kupac = kupac;
        this.datum = datum;
        this.sadrzaj = sadrzaj;
    }

    public String getKupac() {
        return kupac;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public List<NarudzbinaSadrzi> getSadrzaj() {
        return sadrzaj;
    }
}
