import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BankaComponent } from './components/banka/banka.component';
import { UslugaComponent } from './components/usluga/usluga.component';
import { FilijalaComponent } from './components/filijala/filijala.component';
import { KorisnikComponent } from './components/korisnik/korisnik.component';
import  {MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatExpansionModule } from '@angular/material/expansion';
import { HttpClientModule } from '@angular/common/http';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { BankaDialogComponent } from './components/dialogs/banka-dialog/banka-dialog.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { KorisniDialogComponent } from './components/dialogs/korisni-dialog/korisni-dialog.component';
import { FilijalaDialogComponent } from './components/dialogs/filijala-dialog/filijala-dialog.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatSelectModule } from '@angular/material/select';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import { UslugaDialogComponent } from './components/dialogs/usluga-dialog/usluga-dialog.component';




@NgModule({
  declarations: [
    AppComponent,
    BankaComponent,
    UslugaComponent,
    FilijalaComponent,
    KorisnikComponent,
    BankaDialogComponent,
    KorisniDialogComponent,
    FilijalaDialogComponent,
    UslugaDialogComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatSidenavModule, 
    MatListModule,
    MatGridListModule,
    MatExpansionModule,
    HttpClientModule,
    MatTableModule,
    MatToolbarModule,
    MatDialogModule,
    MatSnackBarModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule,
    MatDatepickerModule,
    MatCheckboxModule,
    MatSelectModule,
    MatNativeDateModule,
    MatSortModule,
    MatPaginatorModule
  ],

 

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
