import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Korisnik } from 'src/app/Models/korisnik';
import { Usluga } from 'src/app/Models/usluga';
import { UslugaService } from 'src/app/services/usluga.service';
import { UslugaDialogComponent } from '../dialogs/usluga-dialog/usluga-dialog.component';

@Component({
  selector: 'app-usluga',
  templateUrl: './usluga.component.html',
  styleUrls: ['./usluga.component.css']
})
export class UslugaComponent implements OnInit, OnDestroy {
  displayedColumns = ['idUsluge', 'nazivUsluge', 'opisUsluge', 'datumUgovora', 'provizija', 'filijala', 'korisnik', 'actions'];
  dataSource!: MatTableDataSource<Usluga>;
  subscription!: Subscription;
  @Input() selektovaniKorisnik!: Korisnik;

  constructor(private uslugaService: UslugaService,
    private dialog: MatDialog,
    public snackBar: MatSnackBar) { }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  ngOnChanges(): void {
    if (this.selektovaniKorisnik.idKorisnika) {
      this.loadData();
    }
  }

  loadData() {
    this.subscription = this.uslugaService.getuslugaPoKorisniku(this.selektovaniKorisnik.idKorisnika)
      .subscribe({
        next: (data) => this.dataSource = data,
        error: (error) => {
          this.snackBar.open('Korisnik nema usluga', 'Zatvori', {
            duration: 2500
          }); this.dataSource = new MatTableDataSource<Usluga>
        },
        complete: () => console.info(this.dataSource)
      })
  }
  public openDialog(flag: number, usluga?: Usluga) {
    const dialogRef = this.dialog.open(UslugaDialogComponent, { data: (usluga ? usluga : new Korisnik()) });
    dialogRef.componentInstance.flagArtDialog = flag;
    if (flag === 1) {
      dialogRef.componentInstance.dataUsluge.korisnik = this.selektovaniKorisnik;
    }
    dialogRef.afterClosed()
      .subscribe(result => {
        if (result === 1) {
          this.loadData();
        }
      })
  }
}
