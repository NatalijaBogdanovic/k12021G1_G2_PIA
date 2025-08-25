import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Korisnik } from './models/korisnik';
import { Proizvod } from './models/proizvodi';
import { NarudzbinaSadrzi } from './models/narudzbinaSadrzi';
import { Narudzbina } from './models/narudzbina';
import { Komentari } from './models/komentari';

@Injectable({
  providedIn: 'root'
})
export class ServisService {

  constructor() { }

  private backendUrl= "http://localhost:8080";
  private http= inject(HttpClient);

  login(k:Korisnik){
    return this.http.post<Korisnik>(`${this.backendUrl}/login`, k);

  }
  dohvatiProizvode() {
    return this.http.get<Proizvod[]>(`${this.backendUrl}/proizvodi`);
  }

  dodajNarudzbinu(sadrzaj: NarudzbinaSadrzi[], kupac: string, datum: Date) {

    return this.http.post(`${this.backendUrl}/narudzbina`, { sadrzaj, kupac, datum });

  }
  dohvatiNarudzbine(){
    return this.http.get<Narudzbina[]>(`${this.backendUrl}/dohvatiNarudzbine`);
  }
  dohvatiProizvodeNarudzbine(){
    return this.http.get<NarudzbinaSadrzi[]>(`${this.backendUrl}/dohvatiProizvodeNarudzbine`);
  }

  dohvatiNajvernijegKupca(){
    return this.http.get<Korisnik>(`${this.backendUrl}/najvernijiKupac`);
  }


  dohvatiKomentare(){
    return this.http.get<Komentari[]>(`${this.backendUrl}/komentari`);
  }

  dodajKomentar(komentar: Komentari) {
    return this.http.post(`${this.backendUrl}/dodajKomentar`, komentar);
  }

  dohvatiKorisnika(korIme: string) {
    return this.http.post<Korisnik>(`${this.backendUrl}/nadjiKorisnika`, korIme);
  }

  promeniStatusKomentara(komentar: Komentari) {
    return this.http.post(`${this.backendUrl}/promeniStatusKomentara`, komentar);
  }

   obrisiKomentar(komentar: Komentari) {
    return this.http.post(`${this.backendUrl}/obrisiKomentar`, komentar);
  }

  


}
