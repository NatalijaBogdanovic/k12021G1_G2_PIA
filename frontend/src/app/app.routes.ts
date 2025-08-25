import { Routes } from '@angular/router';
import { KupacComponent } from './kupac/kupac.component';
import { ProdavacComponent } from './prodavac/prodavac.component';
import { PocetnaComponent } from './pocetna/pocetna.component';
import { NarudzbinaComponent } from './narudzbina/narudzbina.component';
import { DetaljiComponent } from './detalji/detalji.component';

export const routes: Routes = [
  { path:'', component:PocetnaComponent},
  { path:'kupac', component:KupacComponent},
  { path:'prodavac', component:ProdavacComponent},
  { path:'narudzbina', component:NarudzbinaComponent},
  { path:'detalji', component:DetaljiComponent}
];
