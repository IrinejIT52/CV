import { Filijala } from "./filijala";
import { Korisnik } from "./korisnik";

export class Usluga {
    idUsluge!:number;
    nazivUsluge!:string;
    opisUsluge!:string;
    datumUgovora!:Date;
    provizija!:number;
    filijala!:Filijala;
    korisnik!:Korisnik;
}