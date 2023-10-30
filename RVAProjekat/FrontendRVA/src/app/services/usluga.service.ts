import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { USLUGA_PO_KORISNIKU_URL, USLUGA_URL } from '../app.constants';
import { Usluga } from '../Models/usluga';


@Injectable({
  providedIn: 'root'
})
export class UslugaService {
 
  constructor(private httpClient: HttpClient) {}

    
    public getuslugaPoKorisniku(korid:number): Observable<any>{
      return this.httpClient.get(USLUGA_PO_KORISNIKU_URL+'/'+korid);
    }

    public addUsluga(usluga:Usluga): Observable<any>{
      return this.httpClient.post(USLUGA_URL,usluga);
    }

    public updateUsluga(usluga:Usluga): Observable<any>{
      return this.httpClient.put(USLUGA_URL+'/'+usluga.idUsluge,usluga);
    }

    public deleteUsluga(idUsluge:number): Observable<any>{
      return this.httpClient.delete(USLUGA_URL+'/'+idUsluge);
    }
  
}
