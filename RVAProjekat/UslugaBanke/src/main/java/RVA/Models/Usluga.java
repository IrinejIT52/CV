package RVA.Models;

import java.io.Serializable;
import java.sql.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
public class Usluga implements Serializable {
	
	private static final long serializable=1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USLUGA_ID_GENERATOR")
	@SequenceGenerator(name="USLUGA_ID_GENERATOR",sequenceName="USLUGA_SEQ", allocationSize = 1)
	private int idUsluge;
	
	private String nazivUsluge;
	private String opisUsluge;
	private Date datumUgovora;
	private int provizija;
	
	@ManyToOne
	@OnDelete(action= OnDeleteAction.CASCADE)
	@JoinColumn(name="filijala")
	private Filijala filijala;
	
	@ManyToOne
	@OnDelete(action= OnDeleteAction.CASCADE)
	@JoinColumn(name="korisnik")
	private Korisnik korisnik;
	
	public int getIdUsluge() {
		return idUsluge;
	}

	public void setIdUsluge(int idUsluge) {
		this.idUsluge = idUsluge;
	}

	public String getNazivUsluge() {
		return nazivUsluge;
	}

	public void setNazivUsluge(String nazivUsluge) {
		this.nazivUsluge = nazivUsluge;
	}

	public String getOpisUsluge() {
		return opisUsluge;
	}

	public void setOpisUsluge(String opisUsluge) {
		this.opisUsluge = opisUsluge;
	}

	public Date getDatumUgovora() {
		return datumUgovora;
	}

	public void setDatumUgovora(Date datumUgovora) {
		this.datumUgovora = datumUgovora;
	}

	public double getProvizija() {
		return provizija;
	}

	public void setProvizija(int provizija) {
		this.provizija = provizija;
	}

	public Filijala getFilijala() {
		return filijala;
	}

	public void setFilijala(Filijala filijala) {
		this.filijala = filijala;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	
}
