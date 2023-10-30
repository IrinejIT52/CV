package RVA.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import RVA.Models.Banka;
import RVA.services.BankaService;

@CrossOrigin
@RestController
public class BankaController {

	@Autowired
	private BankaService bankaService;
	
	@GetMapping("banka")
	public ResponseEntity<?> getAllBanks(){
		List<Banka> banke = bankaService.getAllBanks();
		if(banke.isEmpty())
			return new ResponseEntity<>("Banke - not found",
					HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(banke,HttpStatus.OK);
		
	}
	
	@GetMapping("banka/{idBanke}")
	public ResponseEntity<?> getBankByID(@PathVariable("idBanke") int idBanke){
		
		if(bankaService.existesById(idBanke))
		{
			Optional<Banka> banka = bankaService.getBankByID(idBanke);
			return new ResponseEntity<>(banka,HttpStatus.OK);
		}
		return new ResponseEntity<>("Banke with requested id - not found",
				HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("bankaNaziv/{naziv}")
	public ResponseEntity<?> getBankByNazivBanke(@PathVariable("naziv") String nazivBanke){
		List<Banka> banke = bankaService.getBankByNazivBanke(nazivBanke);
		if(banke.isEmpty())
			return new ResponseEntity<>("Banke with naziv - not found",
					HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(banke);
	}
	
	@GetMapping("bankaPocetnoSlovo/{pocetakNaziva}")
	public ResponseEntity<?> getBankByPocetnoSlovo(@PathVariable("pocetakNaziva") String pocetak){
		List<Banka> banke = bankaService.getBankByPocetnoSlovo(pocetak);
		if(banke.isEmpty())
			return new ResponseEntity<>("Banke with pocetak - not found",
					HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(banke);
	}
	
	
	@PostMapping("banka")
	public ResponseEntity<?> addBank(@RequestBody Banka banka)
	{
		if(bankaService.existesById(banka.getIdBanke()))
		{
			return new ResponseEntity<>("Banke with that ID alredy exists",
					HttpStatus.CONFLICT);
		}
		Banka savedBank =  bankaService.addBank(banka);
		return ResponseEntity.status(HttpStatus.OK).body(savedBank);
	}
	
	@PutMapping("banka/{idBanke}")
	public ResponseEntity<?> updateBank(@RequestBody Banka banka,@PathVariable("idBanke") int idBanke )
	{
		banka.setIdBanke(idBanke);
		if(!bankaService.existesById(banka.getIdBanke()))
		{
			return new ResponseEntity<>("Banke with that ID does not exist",
					HttpStatus.CONFLICT);
		}
		Banka savedBank =  bankaService.addBank(banka);
		return ResponseEntity.status(HttpStatus.OK).body(savedBank);
	}
	
	@DeleteMapping("banka/{idBanke}")
	public ResponseEntity<String> deleteBank(@PathVariable("idBanke") int idBanke)
	{
		if(!bankaService.existesById(idBanke))
		{
			return new ResponseEntity<>("Banke with that ID does not exist",
					HttpStatus.CONFLICT);
		}
		bankaService.deleteBank(idBanke);
		return ResponseEntity.status(HttpStatus.OK).body("Banka has been deleted");
	}
}
