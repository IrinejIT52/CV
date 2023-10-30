package RVA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import RVA.Models.Banka;
import RVA.repository.BankaRepository;

@Service
public class BankaService {
	
	@Autowired
	private BankaRepository bankaRepository;
	
	public List<Banka> getAllBanks(){
		return bankaRepository.findAll();
	}
	
	public Optional<Banka> getBankByID(int id){
		return bankaRepository.findById(id);
	}

	public List<Banka> getBankByNazivBanke(String nazivBanke) {
		return bankaRepository.findByNazivBankeContainingIgnoreCase(nazivBanke);
	}

	public List<Banka> getBankByPocetnoSlovo(String pocetak) {
		return bankaRepository.getByPocetak(pocetak.toLowerCase());
	}
	
	public boolean existesById(int id) {
		return getBankByID(id).isPresent();
	}
	
	public Banka addBank(Banka banka){
		return bankaRepository.save(banka);
	}
	
	public void deleteBank(int id)
	{
		bankaRepository.deleteById(id);
	}
}
