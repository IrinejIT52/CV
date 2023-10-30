import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Banka } from 'src/app/Models/banka';
import { BankaService } from 'src/app/services/banka.service';

@Component({
  selector: 'app-banka-dialog',
  templateUrl: './banka-dialog.component.html',
  styleUrls: ['./banka-dialog.component.css']
})
export class BankaDialogComponent {
  public flagArtDialog!:number;
 
  constructor(public snackBar: MatSnackBar,
    public bankaService: BankaService,
    @Inject(MAT_DIALOG_DATA) public dataBanka: Banka,
    public dialogRef: MatDialogRef<BankaDialogComponent>) { }

  public add(): void {
    console.log("ID je " + this.dataBanka.idBanke + this.dataBanka.nazivBanke);
    this.bankaService.addBank(this.dataBanka).subscribe(() => {
      this.snackBar.open('Uspesno dodat banka: ' + this.dataBanka.nazivBanke, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom dodavanja novog banke. ', 'Zatvori', {
          duration: 2500
        })
      };
  }


  public update(): void {
    this.bankaService.updateBank(this.dataBanka).subscribe(() => {
      this.snackBar.open('Uspesno izmenjen banka: ' + this.dataBanka.nazivBanke, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom izmene banke. ', 'Zatvori', {
          duration: 2500
        })
      };

  }

  public delete(): void {
    this.bankaService.deleteBank(this.dataBanka.idBanke).subscribe(() => {
      this.snackBar.open('Uspesno obrisan banka: ' + this.dataBanka.nazivBanke, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom brisanja banke. ', 'Zatvori', {
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
