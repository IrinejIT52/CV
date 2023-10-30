import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Korisnik } from 'src/app/Models/korisnik';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { KorisniDialogComponent } from '../dialogs/korisni-dialog/korisni-dialog.component';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-korisnik',
  templateUrl: './korisnik.component.html',
  styleUrls: ['./korisnik.component.css']
})
export class KorisnikComponent {
  
  subscription!: Subscription;
  displayedColumns = ['idKorisnika', 'imeKorisnika', 'prezimKorisnika','maticniBroj', 'actions'];
  dataSource!: MatTableDataSource<Korisnik>;
  selektovaniKorsnik1!: Korisnik;
  @ViewChild(MatPaginator, {static: false}) paginator!: MatPaginator;
  @ViewChild(MatSort, {static: false}) sort!: MatSort;

  constructor(private korisnikService: KorisnikService, private dialog:MatDialog){ }
  

  ngOnInit(): void{
    this.loadData();
  }
  loadData():void {
    this.subscription = this.korisnikService.getAllKorisnik().subscribe(
      data => {
        //console.log(data);
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      error => {
        console.log(error.name + ' ' + error.message);
      }
    );
  }

  public openDialog(flag:number,korisnik?:Korisnik):void {
    const dialogRef = this.dialog.open(KorisniDialogComponent, {data: (korisnik? korisnik: new Korisnik())});
    dialogRef.componentInstance.flagArtDialog = flag;
    dialogRef.afterClosed().subscribe(res => {if(res == 1) this.loadData()});
  }

  ngOnDestroy(){
    this.subscription.unsubscribe();
  }

  selectRow(row: any) {
    this.selektovaniKorsnik1 = row;
  }

  ngOnChanges(){
    this.loadData();
  }

  applyFilter(filterValue: any) {
    filterValue = filterValue.target.value
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue; //    JaBuKa    --> JaBuKa --> jabuka
  }
}
