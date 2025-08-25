import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Komentari } from '../models/komentari';
import { Korisnik } from '../models/korisnik';
import { Narudzbina } from '../models/narudzbina';
import { NarudzbinaSadrzi } from '../models/narudzbinaSadrzi';
import { ServisService } from '../servis.service';

@Component({
  selector: 'app-prodavac',
  standalone: true,
  imports: [],
  templateUrl: './prodavac.component.html',
  styleUrl: './prodavac.component.css'
})
export class ProdavacComponent implements OnInit {
   private router = inject(Router);
   private servis = inject(ServisService);
   prodavacObj: Korisnik = new Korisnik();
   narudzbine: Narudzbina[] = [];
   proizvodiNarudzbina: NarudzbinaSadrzi[] = [];
   kupac: Korisnik = new Korisnik();
   komentari: Komentari[]=[]
   korisnik = new Korisnik();
  

   ngOnInit(): void {
     // Ovdje možete dodati logiku koja će se izvršiti prilikom inicijalizacije komponente
     let prodavac= localStorage.getItem("ulogovan");
     if(prodavac){
      this.prodavacObj= JSON.parse(prodavac);
      this.servis.dohvatiNarudzbine().subscribe(data => {
        this.narudzbine = data;
        this.servis.dohvatiProizvodeNarudzbine().subscribe(proizvodi => {
          this.proizvodiNarudzbina = proizvodi;
        })
        this.servis.dohvatiNajvernijegKupca().subscribe(kupac => {
          this.kupac = kupac;
        });
      });
      this.servis.dohvatiKomentare().subscribe(data=>{
        this.komentari=data;
        for(let k of this.komentari){
          this.servis.dohvatiKorisnika(k.korisnik).subscribe(data=>{
            this.korisnik= data;
            k.ime= this.korisnik.ime;
            k.prezime= this.korisnik.prezime;
          })
        }

      });
    }
  }


    odjaviSe(){
      localStorage.removeItem("ulogovan");
      this.router.navigate(['']);
    }

    prihvati(k: Komentari){
      this.servis.promeniStatusKomentara(k).subscribe(data => {
        alert("Komentar prihvaćen!");
        window.location.reload();
      });

    }
    obrisi(k: Komentari){
      this.servis.obrisiKomentar(k).subscribe(data=>{
        alert("Komentar je obrisan!");
        window.location.reload();
      })

    }

}
