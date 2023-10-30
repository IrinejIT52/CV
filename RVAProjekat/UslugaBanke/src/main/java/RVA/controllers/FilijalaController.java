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
import RVA.Models.Filijala;
import RVA.Models.Korisnik;
import RVA.Models.Usluga;
import RVA.services.BankaService;
import RVA.services.FilijalaService;

@CrossOrigin
@RestController
public class FilijalaController {

	@Autowired
	private FilijalaService filijalaService;
	
	@Autowired
	private BankaService bankaService;
	
	@GetMapping("filijala")
	public ResponseEntity<?> getAllFilijala(){
		List<Filijala> filijala = filijalaService.getAllFilijala();
		if(filijala.isEmpty())
			return new ResponseEntity<>("Filijala - not found",
					HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(filijala,HttpStatus.OK);
		
	}
	
	@GetMapping("filijala/{idFilijale}")
	public ResponseEntity<?> getFilijalaByID(@PathVariable("idFilijale") int idFilijale){
		
		if(filijalaService.existesById(idFilijale))
		{
			Optional<Filijala> filijala = filijalaService.getFilijalaByID(idFilijale);
			return new ResponseEntity<>(filijala,HttpStatus.OK);
		}
		return new ResponseEntity<>("Filijala with requested id - not found",
				HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("filijalaAdresa/{adresa}")
	public ResponseEntity<?> getFilijalaByAdresaFilijale(@PathVariable("adresa") String adresaFilijale){
		List<Filijala> filijala = filijalaService.getFilijalaByAdresaFilijale(adresaFilijale);
		if(filijala.isEmpty())
			return new ResponseEntity<>("Filijala with naziv - not found",
					HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(filijala);
	}
	
	@GetMapping("filijalaPocetnoSlovo/{pocetakAdresa}")
	public ResponseEntity<?> getFilijalaByPocetnoSlovoAdrese(@PathVariable("pocetakAdresa") String pocetak){
		List<Filijala> filijala = filijalaService.getFilijalaByPocetnoSlovoAdrese(pocetak);
		if(filijala.isEmpty())
			return new ResponseEntity<>("Filijala with pocetak - not found",
					HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(filijala);
	}
	
	
	@PostMapping("filijala")
	public ResponseEntity<?> addFilijala(@RequestBody Filijala filijala)
	{
		if(filijalaService.existesById(filijala.getIdFilijale()))
		{
			return new ResponseEntity<>("Filijale with that ID alredy exists",
					HttpStatus.CONFLICT);
		}
		Filijala savedFilijala =  filijalaService.addFilijala(filijala);
		return ResponseEntity.status(HttpStatus.OK).body(savedFilijala);
	}
	
	@PutMapping("filijala/{idFilijale}")
	public ResponseEntity<?> updateFilijala(@RequestBody Filijala filijala,@PathVariable("idFilijale") int idFilijale )
	{
		filijala.setIdFilijale(idFilijale);
		if(!filijalaService.existesById(filijala.getIdFilijale()))
		{
			return new ResponseEntity<>("Filijala with that ID does not exist",
					HttpStatus.CONFLICT);
		}
		Filijala savedFilijala =  filijalaService.addFilijala(filijala);
		return ResponseEntity.status(HttpStatus.OK).body(savedFilijala);
	}
	
	@DeleteMapping("filijala/{idFilijale}")
	public ResponseEntity<String> deleteFilijala(@PathVariable("idFilijale") int idFilijale)
	{
		if(!filijalaService.existesById(idFilijale))
		{
			return new ResponseEntity<>("Filijala with that ID does not exist",
					HttpStatus.CONFLICT);
		}
		filijalaService.deleteFilijala(idFilijale);
		return ResponseEntity.status(HttpStatus.OK).body("Filijala has been deleted");
	}
	
	
	@GetMapping("filijalaPoBanka/{id}")
    public ResponseEntity<?> getAllfilijalaPoBanka(@PathVariable("id") int id) {
        Optional<Banka> bankaOtp = bankaService.getBankByID(id);
        if (bankaOtp.isPresent()) {
            List<Filijala> filijala = filijalaService.findByBanka(bankaOtp.get());          
            if(filijala.isEmpty()) {
            	return new ResponseEntity<>("Banka za filijalu nije pronadjen",
            			HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(filijalaService, HttpStatus.OK);
        }
        return new ResponseEntity<>("Filijala nije pronadjena", HttpStatus.NOT_FOUND);
    }
}
