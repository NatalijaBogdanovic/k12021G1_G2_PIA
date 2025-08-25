import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Komentari } from '../models/komentari';
import { Proizvod } from '../models/proizvodi';
import { ServisService } from '../servis.service';

@Component({
  selector: 'app-detalji',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './detalji.component.html',
  styleUrl: './detalji.component.css'
})
export class DetaljiComponent implements OnInit{

  proizvod: Proizvod = new Proizvod();
  private servis= inject(ServisService)
  komentari: Komentari[]=[]
  komentar: Komentari = new Komentari();
  //izabraniProizvod=""

  ngOnInit(): void {
      let izabraniProizvodJSON= localStorage.getItem("izabraniProizvod");
      if(izabraniProizvodJSON){
        this.proizvod = JSON.parse(izabraniProizvodJSON);
        this.servis.dohvatiKomentare().subscribe(data=>{
          this.komentari=data
          for(let k of this.komentari){
              this.servis.dohvatiKorisnika(k.korisnik).subscribe(data=>{
                k.ime= data.ime;
                k.prezime= data.prezime;
              })
          }

        })

      }
  }

  dodajKomentar(){

    this.komentar.proizvod=this.proizvod.naziv
    this.komentar.korisnik = JSON.parse(localStorage.getItem("ulogovan")!).kor_ime;
    this.komentar.status="Neobradjeno"
    this.komentar.datum= new Date()
    this.servis.dodajKomentar(this.komentar).subscribe(data=>{
      //this.komentari.push(data);//ovde dodajem komentar u listu komentara ali to nije potrebno jer se komentari već učitavaju u ngOnInit
      this.komentar = new Komentari();// resetujem polje komentar da bih mogao da unesem novi komentar
      // resetujem polje proizvod za koji pisem komentar
      alert("Komentar dodat!");
      window.location.reload();
    })
  }

}
