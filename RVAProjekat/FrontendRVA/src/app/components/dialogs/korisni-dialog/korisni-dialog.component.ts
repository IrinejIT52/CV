import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Banka } from 'src/app/Models/banka';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { KorisnikComponent } from '../../korisnik/korisnik.component';
import { Korisnik } from 'src/app/Models/korisnik';

@Component({
  selector: 'app-korisni-dialog',
  templateUrl: './korisni-dialog.component.html',
  styleUrls: ['./korisni-dialog.component.css']
})
export class KorisniDialogComponent {
  public flagArtDialog!:number;
 
  constructor(public snackBar: MatSnackBar,
    public korisnikService: KorisnikService,
    @Inject(MAT_DIALOG_DATA) public dataKorisnik: Korisnik,
    public dialogRef: MatDialogRef<KorisniDialogComponent>) { }

  public add(): void {
    console.log("ID je " + this.dataKorisnik.idKorisnika + this.dataKorisnik.imeKorisnika);
    this.korisnikService.addKorisnik(this.dataKorisnik).subscribe(() => {
      this.snackBar.open('Uspesno dodat korisnik: ' + this.dataKorisnik.imeKorisnika, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom dodavanja novog korisnika. ', 'Zatvori', {
          duration: 2500
        })
      };
  }


  public update(): void {
    this.korisnikService.updateKorisnik(this.dataKorisnik).subscribe(() => {
      this.snackBar.open('Uspesno izmenjen korisnika: ' + this.dataKorisnik.imeKorisnika, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom izmene korisnika. ', 'Zatvori', {
          duration: 2500
        })
      };

  }

  public delete(): void {
    this.korisnikService.deleteKorisnik(this.dataKorisnik.idKorisnika).subscribe(() => {
      this.snackBar.open('Uspesno obrisan korisnik: ' + this.dataKorisnik.imeKorisnika, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom brisanja korisnika. ', 'Zatvori', {
          duration: 2500
        })
      };
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste od izmene. ', 'Zatvori', {
      duration: 1000
    })
  }

}
