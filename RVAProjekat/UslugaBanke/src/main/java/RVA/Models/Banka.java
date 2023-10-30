package RVA.Models;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Persistent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
public class Banka implements Serializable {
	
	private static final long serializable=1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BANKA_ID_GENERATOR")
	@SequenceGenerator(name="BANKA_ID_GENERATOR",sequenceName="BANKA_SEQ", allocationSize = 1)
	private int idBanke;
	
	private String nazivBanke;
	
	private String kontaktBanke;
	
	private int PIB;
	
	@JsonIgnore
	@OneToMany(mappedBy="banka")
	private List<Filijala> filijala;
	

	public int getIdBanke() {
		return idBanke;
	}

	public void setIdBanke(int idBanke) {
		this.idBanke = idBanke;
	}

	public String getNazivBanke() {
		return nazivBanke;
	}

	public void setNazivBanke(String nazivBanke) {
		this.nazivBanke = nazivBanke;
	}

	public String getKontaktBanke() {
		return kontaktBanke;
	}

	public void setKontaktBanke(String kontaktBanke) {
		this.kontaktBanke = kontaktBanke;
	}

	public int getPIB() {
		return PIB;
	}

	public void setPIB(int pIB) {
		PIB = pIB;
	}
	
	
	
	
}
