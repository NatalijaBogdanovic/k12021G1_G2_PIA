import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Proizvod } from '../models/proizvodi';
import { ServisService } from '../servis.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-kupac',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './kupac.component.html',
  styleUrl: './kupac.component.css'
})
export class KupacComponent implements OnInit {
  ngOnInit(): void {
    this.servis.dohvatiProizvode().subscribe(data => {
      console.log('Proizvodi:', data); // Ovo je za proveru da li se podaci pravilno dobijaju
      this.proizvodi = data;
    });
  }

  private router = inject(Router);
  private servis = inject(ServisService);
  proizvodi: Proizvod[] = [];



  odjaviSe(){
    localStorage.removeItem("ulogovan");
    this.router.navigate(['']);
  }

  naruci(){
    let izabrani : Proizvod[] = [];
    izabrani = this.proizvodi.filter(p => p.izbor && p.kolicina > 0);// ova linija filtrira proizvode koji su izabrani i imaju količinu veću od 0
    if(izabrani.length == 0){
      alert("Niste izabrali nijedan proizvod!");
    }
    else{
      localStorage.setItem("izabrani", JSON.stringify(izabrani));
      this.router.navigate(['narudzbina']);
    }

  }

  detalji(proizvod: Proizvod) {
    localStorage.setItem("izabraniProizvod", JSON.stringify(proizvod));
    this.router.navigate(['detalji']);
  }

}
