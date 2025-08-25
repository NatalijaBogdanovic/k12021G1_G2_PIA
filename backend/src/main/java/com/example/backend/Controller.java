package com.example.backend;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;





@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200/")
public class Controller {


    @PostMapping("/login")
    public Korisnik login(@RequestBody Korisnik korisnik) {
      return new Repo().login(korisnik);
    }

    @GetMapping("/proizvodi")
    public List<Proizvod> getProizvodi() {
        return new Repo().getProizvodi();
    }

    @PostMapping("/narudzbina")
    public void dodajNarudzbinu(@RequestBody NarudzbinaDTO dto) {
        new Repo().dodajNarudzbinu(dto.getSadrzaj(), dto.getKupac(), dto.getDatum());
    }

    @GetMapping("/dohvatiNarudzbine")
    public List<Narudzbina> dohvatiNarudzbine() {
        return new Repo().dohvatiNarudzbine();
    }

    @GetMapping("/dohvatiProizvodeNarudzbine")
    public List<NarudzbinaSadrzi> dohvatiProizvodeNarudzbine() {
        return new Repo().dohvatiProizvodeNarudzbine();
    }

    @GetMapping("/najvernijiKupac")
    public Korisnik getNajvernijiKupac() {
        return new Repo().getNajvernijiKupac();
    }

    @GetMapping("/komentari")
    public List<Komentari> dohvatiKomentare() {
        return new Repo().dohvatiKomentare();
    }
    
    @PostMapping("/dodajKomentar")
    public void dodajKomentar(@RequestBody Komentari komentar) {
        new Repo().dodajKomentar(komentar);
    }

    @PostMapping("/nadjiKorisnika")
    public Korisnik dohvatiKorisnika(@RequestBody String korIme){
      return new Repo().dohvatiKorisnika(korIme);
    }

    @PostMapping("/promeniStatusKomentara")
    public void promeniStatusKomentara(@RequestBody Komentari komentar) {
        new Repo().promeniStatusKomentara(komentar);
    }

    @PostMapping("/obrisiKomentar")
    public void obrisiKomentar(@RequestBody Komentari komentar){

      new Repo().obrisiKomentar(komentar);

    }


}
