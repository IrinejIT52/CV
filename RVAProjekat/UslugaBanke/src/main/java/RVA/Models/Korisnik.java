package RVA.Models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
public class Korisnik implements Serializable {
	
	private static final long serializable=1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="KORISNIK_ID_GENERATOR")
	@SequenceGenerator(name="KORISNIK_ID_GENERATOR",sequenceName="KORISNIK_SEQ", allocationSize = 1)
	private int id_korisnik;
	
	private String imeKorisnika;
	private String prezimKorisnika;
	private String maticniBroj;
	
	@JsonIgnore
	@OneToMany(mappedBy="korisnik")
	private List<Usluga> usluga;
	
	public int getIdKorisnika() {
		return id_korisnik;
	}
	public void setIdKorisnika(int id_korisnik) {
		this.id_korisnik = id_korisnik;
	}
	public String getImeKorisnika() {
		return imeKorisnika;
	}
	public void setImeKorisnika(String imeKorisnika) {
		this.imeKorisnika = imeKorisnika;
	}
	public String getPrezimKorisnika() {
		return prezimKorisnika;
	}
	public void setPrezimKorisnika(String prezimKorisnika) {
		this.prezimKorisnika = prezimKorisnika;
	}
	public String getMaticniBroj() {
		return maticniBroj;
	}
	public void setMaticniBroj(String maticniBroj) {
		this.maticniBroj = maticniBroj;
	}
}
