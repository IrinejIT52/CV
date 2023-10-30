package RVA.Models;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
public class Filijala implements Serializable {
	
	private static final long serializable=1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FILIJALA_ID_GENERATOR")
	@SequenceGenerator(name="FILIJALA_ID_GENERATOR",sequenceName="FILIJALA_SEQ", allocationSize = 1)
	private int idFilijale;
	
	private String adresaFilijale;
	
	private int brojPultova;
	
	private boolean posjedujeSefa;
	
	@ManyToOne
	@OnDelete(action= OnDeleteAction.CASCADE)
	@JoinColumn(name="banka")
	private Banka banka;
	
	@JsonIgnore
	@OneToMany(mappedBy="filijala")
	private List<Usluga> usluga;

	public int getIdFilijale() {
		return idFilijale;
	}

	public void setIdFilijale(int idFilijale) {
		this.idFilijale = idFilijale;
	}

	public String getAdresaFilijale() {
		return adresaFilijale;
	}

	public void setAdresaFilijale(String adresaFilijale) {
		this.adresaFilijale = adresaFilijale;
	}

	public int getBrojPultova() {
		return brojPultova;
	}

	public void setBrojPultova(int brojPultova) {
		this.brojPultova = brojPultova;
	}

	public boolean isPosjedujeSefa() {
		return posjedujeSefa;
	}

	public void setPosjedujeSefa(boolean posjedujeSefa) {
		this.posjedujeSefa = posjedujeSefa;
	}

	public Banka getBanka() {
		return banka;
	}

	public void setBanka(Banka banka) {
		this.banka = banka;
	}
	
	

	
	
	
}
