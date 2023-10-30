import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Filijala } from 'src/app/Models/filijala';
import { FilijalaService } from 'src/app/services/filijala.service';
import { FilijalaDialogComponent } from '../dialogs/filijala-dialog/filijala-dialog.component';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Banka } from 'src/app/Models/banka';

@Component({
  selector: 'app-filijala',
  templateUrl: './filijala.component.html',
  styleUrls: ['./filijala.component.css']
})
export class FilijalaComponent {
  subscription!: Subscription;
  displayedColumns = ['idFilijale', 'adresaFilijale', 'brojPultova', 'posjedujeSefa', 'banka', 'actions'];
  dataSource!: MatTableDataSource<Filijala>;
  @ViewChild(MatPaginator, {static: false}) paginator!: MatPaginator;
  @ViewChild(MatSort, {static: false}) sort!: MatSort;

  constructor(private filijalaService: FilijalaService, private dialog: MatDialog) { }

  ngOnInit(): void { this.loadData(); }
  ngOnChanges(): void { this.loadData(); }

  public loadData() {
    this.subscription = this.filijalaService.getAllFilijala().subscribe(
      data => {
        this.dataSource = new MatTableDataSource(data);
        //sortiramo po ugnjezdenom obelezju
         this.dataSource.sortingDataAccessor = (row: Filijala, columnName: string): string => {
 
           console.log(row, columnName);
           if (columnName == "banka") return row.banka.nazivBanke.toLocaleLowerCase();
           var columnValue = row[columnName as keyof Filijala] as unknown as string;
           return columnValue;
 
         }
 
         this.dataSource.sort = this.sort;
         //filtriranje po ugnjezdenom obelezju
         this.dataSource.filterPredicate = (data, filter: string) => {
           const accumulator = (currentTerm: any, key: string) => {
             return key === 'banka' ? currentTerm + data.banka.nazivBanke : currentTerm + data[key as keyof Filijala];
           };
           const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
           const transformedFilter = filter.trim().toLowerCase();
           return dataStr.indexOf(transformedFilter) !== -1;
         };
 
         this.dataSource.paginator = this.paginator;
  })}

  //iz htmla prosledjujemo ove podatke dijalogu
  openDialog(flag: number, filijala?: Filijala): void {
    const dialogRef = this.dialog.open(FilijalaDialogComponent, { data: (filijala ? filijala : new Filijala()) });
    //otvara modalni dijalog odgovarajuće komponente
    //vracamo instancu keirane komponente dialoga
    dialogRef.componentInstance.flagArtDialog = flag;
    dialogRef.afterClosed().subscribe(res => {
      if (res === 1) //uspesno 
      {
        //ponovo učitaj podatke
        this.loadData();
      }
    })
  }

  applyFilter(filterValue: any) {
    filterValue = filterValue.target.value
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue; //    JaBuKa    --> JaBuKa --> jabuka
  }
}
