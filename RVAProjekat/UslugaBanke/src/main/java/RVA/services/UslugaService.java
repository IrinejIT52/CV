package RVA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import RVA.Models.Filijala;
import RVA.Models.Korisnik;
import RVA.Models.Usluga;
import RVA.repository.UslugaRepository;

@Service
public class UslugaService {

	@Autowired
	private UslugaRepository uslugaRepository;
	
	public List<Usluga> getAllUsluga(){
		return uslugaRepository.findAll();
	}
	
	public Optional<Usluga> getUslugaByID(int id){
		return uslugaRepository.findById(id);
	}

	public List<Usluga> getUslugaByNazivUsluge(String nazivBanke) {
		return uslugaRepository.findByNazivUslugeContainingIgnoreCase(nazivBanke);
	}

	public List<Usluga> getUslugaByPocetnoSlovo(String pocetak) {
		return uslugaRepository.getByPocetak(pocetak.toLowerCase());
	}
	
	public boolean existesById(int id) {
		return getUslugaByID(id).isPresent();
	}
	
	public Usluga addUsluga(Usluga usluga){
		return uslugaRepository.save(usluga);
	}
	
	public void deleteUsluga(int id)
	{
		uslugaRepository.deleteById(id);
	}
	
	public List<Usluga> findByProvizija(double provizija) {
		return uslugaRepository.findByProvizija(provizija); }

	public List<Usluga> findByKorisnik(Korisnik korisnik) {
		return uslugaRepository.findByKorisnik(korisnik);
	}

	public List<Usluga> findByFilijala(Filijala filijala) {
		return uslugaRepository.findByFilijala(filijala);
	}

}
