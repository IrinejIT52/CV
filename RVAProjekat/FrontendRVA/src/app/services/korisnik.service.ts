import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { KORISNIK_URL } from '../app.constants';
import { Korisnik } from '../Models/korisnik';



@Injectable({
  providedIn: 'root'
})
export class KorisnikService {

  constructor(private httpClient: HttpClient) {}

    
    public getAllKorisnik(): Observable<any>{
      return this.httpClient.get(KORISNIK_URL);
    }

    public addKorisnik(korisnik:Korisnik): Observable<any>{
      return this.httpClient.post(KORISNIK_URL,korisnik);
    }

    public updateKorisnik(korisnik:Korisnik): Observable<any>{
      return this.httpClient.put(KORISNIK_URL+'/'+korisnik.idKorisnika,korisnik);
    }

    public deleteKorisnik(idKorisnika:number): Observable<any>{
      return this.httpClient.delete(KORISNIK_URL+'/'+idKorisnika);
    }
  
}