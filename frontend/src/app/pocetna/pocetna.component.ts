import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Korisnik } from '../models/korisnik';
import { ServisService } from '../servis.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pocetna',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './pocetna.component.html',
  styleUrls: ['./pocetna.component.css']
})
export class PocetnaComponent {
  username=""
  password=""
  tip=""
  poruka=""
  private servis = inject(ServisService);
  private router = inject(Router);

  login(){

    if(this.username.length == 0 || this.password.length == 0 || this.tip.length == 0){
      this.poruka = "Molimo unesite sve podatke!"
    }
    else{
      this.poruka=""
      let k= new Korisnik()
      k.kor_ime = this.username
      k.lozinka = this.password
      k.tip = this.tip
      this.servis.login(k).subscribe(data=>{
        if(data==null){
          this.poruka = "Pogresno korisnicko ime ili lozinka!"
        }
        else{
          localStorage.setItem("ulogovan", JSON.stringify(data));
          if(data.tip == "kupac"){
            this.router.navigate(['kupac']);
          }
          else {
            this.router.navigate(['prodavac']);
          }
        }
      })
    }

  }

}
