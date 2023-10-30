package RVA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import RVA.Models.Banka;
import RVA.Models.Filijala;
import RVA.Models.Usluga;
import RVA.repository.FilijalaRepository;

@Service
public class FilijalaService {

	@Autowired
	private FilijalaRepository filijalaRepository;
	
	public List<Filijala> getAllFilijala(){
		return filijalaRepository.findAll();
	}
	
	public Optional<Filijala> getFilijalaByID(int id){
		return filijalaRepository.findById(id);
	}

	public List<Filijala> getFilijalaByAdresaFilijale(String adresaFilijale) {
		return filijalaRepository.findByAdresaFilijaleContainingIgnoreCase(adresaFilijale);
	}

	public List<Filijala> getFilijalaByPocetnoSlovoAdrese(String pocetak) {
		return filijalaRepository.getByPocetak(pocetak.toLowerCase());
	}
	
	public boolean existesById(int id) {
		return getFilijalaByID(id).isPresent();
	}
	
	public Filijala addFilijala(Filijala filijala){
		return filijalaRepository.save(filijala);
	}
	
	public void deleteFilijala(int id)
	{
		filijalaRepository.deleteById(id);
	}
	
	
	public List<Filijala> findByBanka(Banka banka) {
		return filijalaRepository.findByBanka(banka);
	}
}
