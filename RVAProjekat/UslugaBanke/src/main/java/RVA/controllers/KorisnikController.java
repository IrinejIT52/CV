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

import RVA.Models.Korisnik;
import RVA.services.KorisnikService;

@CrossOrigin
@RestController
public class KorisnikController {
	
	@Autowired
	private KorisnikService korisnikService;
	
	@GetMapping("korisnik")
	public ResponseEntity<?> getAllKorisnik(){
		List<Korisnik> korisnik = korisnikService.getAllKorisnik();
		if(korisnik.isEmpty())
			return new ResponseEntity<>("Korisnik - not found",
					HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(korisnik,HttpStatus.OK);
		
	}
	
	@GetMapping("korisnik/{idKorisnika}")
	public ResponseEntity<?> getKorisnikByID(@PathVariable("idKorisnika") int idKorisnika){
		
		if(korisnikService.existesById(idKorisnika))
		{
			Optional<Korisnik> korisnik = korisnikService.getKorisnikByID(idKorisnika);
			return new ResponseEntity<>(korisnik,HttpStatus.OK);
		}
		return new ResponseEntity<>("Korisnik with requested id - not found",
				HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("imeKorisnika/{ime}")
	public ResponseEntity<?> getKorisnikByImeKorisnika(@PathVariable("ime") String imeKorisnika){
		List<Korisnik> korisnik = korisnikService.getKorisnikByImeKorisnika(imeKorisnika);
		if(korisnik.isEmpty())
			return new ResponseEntity<>("Korisnik with naziv - not found",
					HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(korisnik);
	}
	
	@GetMapping("korisnikPocetnoSlovo/{pocetakImena}")
	public ResponseEntity<?> getKorisnikByPocetnoSlovo(@PathVariable("pocetakImena") String pocetak){
		List<Korisnik> korisnik = korisnikService.getKorisnikByPocetnoSlovo(pocetak);
		if(korisnik.isEmpty())
			return new ResponseEntity<>("Korisnik with pocetak - not found",
					HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(korisnik);
	}
	
	
	@PostMapping("korisnik")
	public ResponseEntity<?> addKorisnik(@RequestBody Korisnik korisnik)
	{
		if(korisnikService.existesById(korisnik.getIdKorisnika()))
		{
			return new ResponseEntity<>("Korisnik with that ID alredy exists",
					HttpStatus.CONFLICT);
		}
		Korisnik savedKorisnik =  korisnikService.addKorisnik(korisnik);
		return ResponseEntity.status(HttpStatus.OK).body(savedKorisnik);
	}
	
	@PutMapping("korisnik/{idKorisnika}")
	public ResponseEntity<?> updateKorisnik(@RequestBody Korisnik korisnik,@PathVariable("idKorisnika") int idKorisnika )
	{
		korisnik.setIdKorisnika(idKorisnika);
		if(!korisnikService.existesById(korisnik.getIdKorisnika()))
		{
			return new ResponseEntity<>("Korisnik with that ID does not exist",
					HttpStatus.CONFLICT);
		}
		Korisnik savedKorisnik =  korisnikService.addKorisnik(korisnik);
		return ResponseEntity.status(HttpStatus.OK).body(savedKorisnik);
	}
	
	@DeleteMapping("korisnik/{idKorisnika}")
	public ResponseEntity<String> deleteKorisnik(@PathVariable("idKorisnika") int idKorisnika)
	{
		if(!korisnikService.existesById(idKorisnika))
		{
			return new ResponseEntity<>("Korisnik with that ID does not exist",
					HttpStatus.CONFLICT);
		}
		korisnikService.deleteKorisnik(idKorisnika);
		return ResponseEntity.status(HttpStatus.OK).body("Korisnik has been deleted");
	}
}
