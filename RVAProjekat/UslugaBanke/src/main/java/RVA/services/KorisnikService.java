package RVA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import RVA.Models.Korisnik;
import RVA.repository.KorisnikRepository;

@Service
public class KorisnikService {
	
	@Autowired
	private KorisnikRepository korisnikRepositoy;
	
	public List<Korisnik> getAllKorisnik(){
		return korisnikRepositoy.findAll();
	}
	
	public Optional<Korisnik> getKorisnikByID(int id){
		return korisnikRepositoy.findById(id);
	}

	public List<Korisnik> getKorisnikByImeKorisnika(String imeKorisnika) {
		return korisnikRepositoy.findByImeKorisnikaContainingIgnoreCase(imeKorisnika);
	}

	public List<Korisnik> getKorisnikByPocetnoSlovo(String pocetak) {
		return korisnikRepositoy.getByPocetak(pocetak.toLowerCase());
	}
	
	public boolean existesById(int id) {
		return getKorisnikByID(id).isPresent();
	}
	
	public Korisnik addKorisnik(Korisnik korisnik){
		return korisnikRepositoy.save(korisnik);
	}
	
	public void deleteKorisnik(int id)
	{
		korisnikRepositoy.deleteById(id);
	}

}
