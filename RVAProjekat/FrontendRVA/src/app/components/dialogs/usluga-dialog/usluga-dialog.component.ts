import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { Filijala } from 'src/app/Models/filijala';
import { Korisnik } from 'src/app/Models/korisnik';
import { Usluga } from 'src/app/Models/usluga';
import { FilijalaService } from 'src/app/services/filijala.service';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { UslugaService } from 'src/app/services/usluga.service';

@Component({
  selector: 'app-usluga-dialog',
  templateUrl: './usluga-dialog.component.html',
  styleUrls: ['./usluga-dialog.component.css']
})
export class UslugaDialogComponent {
  public flagArtDialog!:number;
  public filijale!: Filijala[];
  public korisnika!: Korisnik[];
  filijalaSubscription!: Subscription;
  korisnikSubscription!: Subscription;

  constructor(public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<UslugaDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public dataUsluge: Usluga,
    public uslugaService: UslugaService,
    public filijaleService: FilijalaService,
    public koricnikService: KorisnikService
    ) { }

    ngOnDestroy(): void {
      this.korisnikSubscription.unsubscribe();
      this.filijalaSubscription.unsubscribe();
    }

    ngOnInit(): void {
      this.filijalaSubscription = this.filijaleService.getAllFilijala()
        .subscribe(filijala => {
          this.filijale = filijala;
        }),
        (error: Error) => {
          console.log(error.name + ' ' + error.message);
        }
        this.korisnikSubscription = this.koricnikService.getAllKorisnik()
        .subscribe(korinik => {
          this.korisnika = korinik;
        }),
        (error: Error) => {
          console.log(error.name + ' ' + error.message);
        }
    }

  compareTo(a: any, b: any) {
    return a.id === b.id;
  }

  public add(): void {
    this.uslugaService.addUsluga(this.dataUsluge).subscribe(() => {
      this.snackBar.open('Uspesno dodata usluga: ' + this.dataUsluge.nazivUsluge, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom dodavanja nove usluga. ', 'Zatvori', {
          duration: 2500
        })
      };
  }


  public update(): void {
    this.uslugaService.updateUsluga(this.dataUsluge).subscribe(() => {
      this.snackBar.open('Uspesno izmenjena usluga: ' + this.dataUsluge.nazivUsluge, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom izmene usluge. ', 'Zatvori', {
          duration: 2500
        })
      };

  }

  public delete(): void {
    this.uslugaService.deleteUsluga(this.dataUsluge.idUsluge).subscribe(() => {
      this.snackBar.open('Uspesno obrisana usluga: ' + this.dataUsluge.idUsluge, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom brisanja usluge. ', 'Zatvori', {
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
