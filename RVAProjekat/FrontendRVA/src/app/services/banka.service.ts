import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BANKA_URL } from '../app.constants';
import { Banka } from '../Models/banka';

@Injectable({
  providedIn: 'root'
})
export class BankaService {
 
  constructor(private httpClient: HttpClient) {}

    
    public getAllBanks(): Observable<any>{
      return this.httpClient.get(BANKA_URL);
    }

    public addBank(banka:Banka): Observable<any>{
      return this.httpClient.post(BANKA_URL,banka);
    }

    public updateBank(banka:Banka): Observable<any>{
      return this.httpClient.put(BANKA_URL+'/'+banka.idBanke,banka);
    }

    public deleteBank(idBanke:number): Observable<any>{
      return this.httpClient.delete(BANKA_URL+'/'+idBanke);
    }
  
}
