import { Component, ViewChild } from '@angular/core';
import { Banka } from 'src/app/Models/banka';
import { Subscription } from 'rxjs';
import { MatTableDataSource } from '@angular/material/table';
import { BankaService } from 'src/app/services/banka.service';
import { MatDialog } from '@angular/material/dialog';
import { BankaDialogComponent } from '../dialogs/banka-dialog/banka-dialog.component';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-banka',
  templateUrl: './banka.component.html',
  styleUrls: ['./banka.component.css']
})
export class BankaComponent {
  

  subscription!: Subscription;
  displayedColumns = ['idBanke', 'nazivBanke', 'kontaktBanke','pib', 'actions'];
  dataSource!: MatTableDataSource<Banka>;
  @ViewChild(MatPaginator, {static: false}) paginator!: MatPaginator;
  @ViewChild(MatSort, {static: false}) sort!: MatSort;

  constructor(private bankaService: BankaService, private dialog:MatDialog){ }
  

  ngOnInit(): void{
    this.loadData();
  }
  loadData():void {
    this.subscription = this.bankaService.getAllBanks().subscribe(
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

  public openDialog(flag:number,banka?:Banka):void {
    const dialogRef = this.dialog.open(BankaDialogComponent, {data: (banka? banka: new Banka())});
    dialogRef.componentInstance.flagArtDialog = flag;
    dialogRef.afterClosed().subscribe(res => {if(res == 1) this.loadData()});
  }

  ngOnDestroy(){
    this.subscription.unsubscribe();
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
