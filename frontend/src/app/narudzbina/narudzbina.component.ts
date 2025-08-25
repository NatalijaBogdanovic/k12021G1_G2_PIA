import { Component, inject, OnInit } from '@angular/core';
import { Proizvod } from '../models/proizvodi';
import { ServisService } from '../servis.service';
import { NarudzbinaSadrzi } from '../models/narudzbinaSadrzi';
import { Korisnik } from '../models/korisnik';

@Component({
  selector: 'app-narudzbina',
  standalone: true,
  imports: [],
  templateUrl: './narudzbina.component.html',
  styleUrl: './narudzbina.component.css'
})
export class NarudzbinaComponent implements OnInit {
  odabrani: Proizvod[] = [];
  private servis= inject(ServisService);
  sadrzajNarudzbine: NarudzbinaSadrzi[] = [];
  datum: Date = new Date();

  ngOnInit(): void {
    let odabraniJson = localStorage.getItem("izabrani");
    if (odabraniJson) {
      this.odabrani = JSON.parse(odabraniJson);
      //sada hocu da popunim sadrzajNarudzbine
      for(let p of this.odabrani) {
        let ns = new NarudzbinaSadrzi();
        ns.proizvod = p.naziv;
        ns.kolicina = p.kolicina;
        this.sadrzajNarudzbine.push(ns);
      }
    }
  }

  ukloniProizvod(proizvod: Proizvod) {
    this.odabrani = this.odabrani.filter(p => p !== proizvod);
    localStorage.setItem("izabrani", JSON.stringify(this.odabrani));
  }

  potvrdiNarudzbinu(){
    let k= localStorage.getItem("ulogovan");
    let kupac= new Korisnik();

    if(k){
      kupac= JSON.parse(k);
    }
    if(this.odabrani.length == 0) {
      alert("Nema proizvoda u narudžbini!");
    }
    else{
      this.servis.dodajNarudzbinu(this.sadrzajNarudzbine, kupac.kor_ime, this.datum).subscribe({ next: () => {
          alert("Narudžbina uspešno potvrđena!");
          localStorage.removeItem("izabrani");
          this.odabrani = [];
        },
        error: (err) => {
          console.error("Greška prilikom potvrde narudžbine:", err);
          alert("Došlo je do greške prilikom potvrde narudžbine.");
        }
      });

    }

  }


}
