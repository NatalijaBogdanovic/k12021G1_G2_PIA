package com.example.backend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import com.example.backend.db.DB;

public class Repo {
    public Korisnik login(Korisnik korisnik) {
         try(Connection conn = DB.source().getConnection();
            PreparedStatement stm = conn.prepareStatement("select * from korisnici where kor_ime=? and lozinka=? and tip=?")){

            stm.setString(1, korisnik.getKor_ime());
            stm.setString(2, korisnik.getLozinka());
            stm.setString(3, korisnik.getTip());
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return new Korisnik(rs.getString("kor_ime"), rs.getString("ime"), rs.getString("prezime"), rs.getString("mejl"), rs.getDate("datum_rodjenja"), rs.getString("tip"), rs.getString("lozinka"));
            }
            return null;
        }catch(SQLException e){
            e.printStackTrace();
            // Handle exception, maybe log it or rethrow it
        }
        return null;
    }

    public List<Proizvod> getProizvodi() {
        try(Connection conn = DB.source().getConnection();
            PreparedStatement stm = conn.prepareStatement("select * from proizvodi");
           ) {
            ResultSet rs = stm.executeQuery();

            List<Proizvod> proizvodi = new ArrayList<>();
            while(rs.next()){
                Proizvod proizvod = new Proizvod(rs.getString("naziv"), rs.getString("opis"), rs.getInt("cena"), rs.getInt("promocija"));
                proizvodi.add(proizvod);
            }
            return proizvodi;
        } catch(SQLException e) {
            e.printStackTrace();
            // Handle exception, maybe log it or rethrow it
        }
        return null;
    }

    public void dodajNarudzbinu(List<NarudzbinaSadrzi> odabrani, String kupac, LocalDate datum){
        try(Connection conn= DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("insert into narudzbine (kupac, datum) values (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, kupac);
            stm.setDate(2, java.sql.Date.valueOf(datum));
            stm.executeUpdate();

            int narudzbinaId = -1;
            try(ResultSet rs = stm.getGeneratedKeys()) {
                if(rs.next()) {
                    narudzbinaId = rs.getInt(1);
                }
            }
            try(
            PreparedStatement stm2 = conn.prepareStatement("insert into narudzbinasadrzi (kolicina, proizvod, narudzbina) values (?, ?, ?)")) {
                System.out.println("Broj odabranih: " + odabrani.size());
                for(NarudzbinaSadrzi ns : odabrani){
                    System.out.println("Dodajem: " + ns.getProizvod() + ", " + ns.getKolicina());
                    stm2.setInt(1, ns.getKolicina());
                    stm2.setString(2, ns.getProizvod());
                    stm2.setInt(3, narudzbinaId);
                    stm2.executeUpdate();
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
            // Handle exception, maybe log it or rethrow it
        }
    }

    public List<Narudzbina> dohvatiNarudzbine() {
        try(Connection conn = DB.source().getConnection();
            PreparedStatement stm = conn.prepareStatement("select * from narudzbine");
            ResultSet rs = stm.executeQuery()) {

            List<Narudzbina> narudzbine = new ArrayList<>();
            while(rs.next()){
                Narudzbina narudzbina = new Narudzbina(rs.getInt("idN"), rs.getString("kupac"), rs.getDate("datum").toLocalDate());
                narudzbine.add(narudzbina);
            }
            return narudzbine;
        } catch(SQLException e) {
            e.printStackTrace();
            // Handle exception, maybe log it or rethrow it
        }
        return null;
    }

    public List<NarudzbinaSadrzi> dohvatiProizvodeNarudzbine() {
        try(Connection conn = DB.source().getConnection();
            PreparedStatement stm = conn.prepareStatement("select * from narudzbine");
            ResultSet rs = stm.executeQuery()) {

            List<NarudzbinaSadrzi> sadrzaj = new ArrayList<>();
            while(rs.next()){
                Narudzbina narudzbina = new Narudzbina(rs.getInt("idN"), rs.getString("kupac"), rs.getDate("datum").toLocalDate());
                try(PreparedStatement stm2 = conn.prepareStatement("select * from narudzbinasadrzi where narudzbina=?")) {
                    stm2.setInt(1, narudzbina.getIdN());
                    ResultSet rs2 = stm2.executeQuery();
                    while(rs2.next()){
                        NarudzbinaSadrzi ns = new NarudzbinaSadrzi(rs2.getString("proizvod"), rs2.getInt("kolicina"), rs2.getInt("narudzbina"));
                        sadrzaj.add(ns);
                    }
                }
            }
            return sadrzaj;
        } catch(SQLException e) {
            e.printStackTrace();
            // Handle exception, maybe log it or rethrow it
        }
        return null;
    }

    public Korisnik getNajvernijiKupac() {
        try(Connection conn = DB.source().getConnection();
            PreparedStatement stm = conn.prepareStatement("select kupac, count(*) as brojNarudzbina from narudzbine group by kupac order by brojNarudzbina desc limit 1");
            ResultSet rs = stm.executeQuery()) {

            if(rs.next()){
                String kupacKorIme = rs.getString("kupac");
                try(PreparedStatement stm2 = conn.prepareStatement("select * from korisnici where kor_ime=?")) {
                    stm2.setString(1, kupacKorIme);
                    ResultSet rs2 = stm2.executeQuery();
                    if(rs2.next()){
                        return new Korisnik(rs2.getString("kor_ime"), rs2.getString("ime"), rs2.getString("prezime"), rs2.getString("mejl"), rs2.getDate("datum_rodjenja"), rs2.getString("tip"), rs2.getString("lozinka"));
                    }
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
            // Handle exception, maybe log it or rethrow it
        }
        return null;
    }

    public List<Komentari> dohvatiKomentare(){
         try(Connection conn = DB.source().getConnection();
            PreparedStatement stm = conn.prepareStatement("select * from komentari");
            ResultSet rs = stm.executeQuery()) {

            List<Komentari> komentari = new ArrayList<>();
            while(rs.next()){
                Komentari k = new Komentari(rs.getInt("idK"), rs.getString("proizvod"), rs.getString("tekst"), rs.getDate("datum").toLocalDate(), rs.getString("korisnik"), rs.getString("status"));
                komentari.add(k);
            }
            return komentari;
        } catch(SQLException e) {
            e.printStackTrace();
            // Handle exception, maybe log it or rethrow it
        }
        return null;
    }

    public void dodajKomentar(Komentari komentar){
          try(Connection conn= DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("insert into komentari (proizvod, tekst, datum, korisnik, status) values (?, ?, ?, ?, ?)")) {
            stm.setString(1, komentar.getProizvod());
            stm.setString(2, komentar.getTekst());
            stm.setDate(3, java.sql.Date.valueOf(komentar.getDatum()));
            stm.setString(4, komentar.getKorisnik());
            stm.setString(5, komentar.getStatus());
            stm.executeUpdate();

         } catch(SQLException e) {
            e.printStackTrace();
            // Handle exception, maybe log it or rethrow it
        }

    }

    public Korisnik dohvatiKorisnika(String korIme){
        try(Connection conn=DB.source().getConnection();
        PreparedStatement stm= conn.prepareStatement("select * from korisnici where kor_ime=?")){
            stm.setString(1, korIme);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return new Korisnik(rs.getString("kor_ime"), rs.getString("ime"), rs.getString("prezime"), rs.getString("mejl"), rs.getDate("datum_rodjenja"), rs.getString("tip"), rs.getString("lozinka"));
            }
        }catch(SQLException e) {
            e.printStackTrace();
            // Handle exception, maybe log it or rethrow it
        }
        return null;
    }

    public void promeniStatusKomentara(Komentari komentar) {
        try(Connection conn = DB.source().getConnection();
            PreparedStatement stm = conn.prepareStatement("update komentari set status=? where idK=?")) {
            stm.setString(1, "Prihvaceno");
            stm.setInt(2, komentar.getIdK());
            stm.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            // Handle exception, maybe log it or rethrow it
        }
    }

    public void obrisiKomentar(Komentari komentar){
        try(Connection conn = DB.source().getConnection();
            PreparedStatement stm = conn.prepareStatement("delete from komentari where idK=?")) {
            stm.setInt(1, komentar.getIdK());
            stm.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            // Handle exception, maybe log it or rethrow it
        }

    }

}

