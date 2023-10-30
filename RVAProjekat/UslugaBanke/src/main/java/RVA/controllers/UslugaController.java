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

import RVA.Models.Filijala;
import RVA.Models.Korisnik;
import RVA.Models.Usluga;
import RVA.services.FilijalaService;
import RVA.services.KorisnikService;
import RVA.services.UslugaService;

@CrossOrigin
@RestController
public class UslugaController {

	@Autowired
	private UslugaService uslugaService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private FilijalaService filijalaService;
	
	@GetMapping("usluga")
	public ResponseEntity<?> getAllUsluga(){
		List<Usluga> usluga = uslugaService.getAllUsluga();
		if(usluga.isEmpty())
			return new ResponseEntity<>("Usluga - not found",
					HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(usluga,HttpStatus.OK);
		
	}
	
	@GetMapping("usluga/{idUsluge}")
	public ResponseEntity<?> getUslugaByID(@PathVariable("idUsluge") int idUsluge){
		
		if(uslugaService.existesById(idUsluge))
		{
			Optional<Usluga> usluga = uslugaService.getUslugaByID(idUsluge);
			return new ResponseEntity<>(usluga,HttpStatus.OK);
		}
		return new ResponseEntity<>("Usluga with requested id - not found",
				HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("uslugaNaziv/{naziv}")
	public ResponseEntity<?> getUslugeByNazivUsluge(@PathVariable("naziv") String nazivUsluge){
		List<Usluga> usluga = uslugaService.getUslugaByNazivUsluge(nazivUsluge);
		if(usluga.isEmpty())
			return new ResponseEntity<>("Usluga with naziv - not found",
					HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(usluga);
	}
	
	@GetMapping("uslugaPocetnoSlovo/{pocetakNaziva}")
	public ResponseEntity<?> getUslugaByPocetnoSlovo(@PathVariable("pocetakNaziva") String pocetak){
		List<Usluga> usluga = uslugaService.getUslugaByPocetnoSlovo(pocetak);
		if(usluga.isEmpty())
			return new ResponseEntity<>("Usluga with pocetak - not found",
					HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(usluga);
	}
	
	
	@PostMapping("usluga")
	public ResponseEntity<?> addUsluga(@RequestBody Usluga usluga)
	{
		if(uslugaService.existesById(usluga.getIdUsluge()))
		{
			return new ResponseEntity<>("Usluga with that ID alredy exists",
					HttpStatus.CONFLICT);
		}
		Usluga savedUsluga =  uslugaService.addUsluga(usluga);
		return ResponseEntity.status(HttpStatus.OK).body(savedUsluga);
	}
	
	@PutMapping("usluga/{idUsluga}")
	public ResponseEntity<?> updateUsluga(@RequestBody Usluga usluga,@PathVariable("idUsluga") int idUsluga )
	{
		usluga.setIdUsluge(idUsluga);
		if(!uslugaService.existesById(usluga.getIdUsluge()))
		{
			return new ResponseEntity<>("Usluga with that ID does not exist",
					HttpStatus.CONFLICT);
		}
		Usluga savedUsluga =  uslugaService.addUsluga(usluga);
		return ResponseEntity.status(HttpStatus.OK).body(savedUsluga);
	}
	
	@DeleteMapping("usluga/{idUsluge}")
	public ResponseEntity<String> deleteUsluga(@PathVariable("idUsluge") int idUsluge)
	{
		if(!uslugaService.existesById(idUsluge))
		{
			return new ResponseEntity<>("Usluga with that ID does not exist",
					HttpStatus.CONFLICT);
		}
		uslugaService.deleteUsluga(idUsluge);
		return ResponseEntity.status(HttpStatus.OK).body("Usluga has been deleted");
	}
	
	//provizija
	@GetMapping(value = "uslugaPoProvizijiManjoj/{provizija}")
    public ResponseEntity<List<Usluga>> uslugaPoProvizija(@PathVariable("provizija") double provizija) {
        List<Usluga> usluga = uslugaService.findByProvizija(provizija);
        return new ResponseEntity<>(usluga, HttpStatus.OK);

    }
	
	
	//strani kljuc
	@GetMapping("uslugaPoKorisniku/{id}")
    public ResponseEntity<?> getuslugaPoKorisniku(@PathVariable("id") int id) {
        Optional<Korisnik> korisnikOtp = korisnikService.getKorisnikByID(id);
        if (korisnikOtp.isPresent()) {
            List<Usluga> usluge = uslugaService.findByKorisnik(korisnikOtp.get());          
            if(usluge.isEmpty()) {
            	return new ResponseEntity<>("Korisnik za uslugu nije pronadjen",
            			HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(usluge, HttpStatus.OK);
        }
        return new ResponseEntity<>("Usluga nije pronadjena", HttpStatus.NOT_FOUND);
    }
	
	@GetMapping("uslugaPoFilijali/{id}")
    public ResponseEntity<?> getAlluslugaPoFilijali(@PathVariable("id") int id) {
        Optional<Filijala> filijalaOtp = filijalaService.getFilijalaByID(id);
        if (filijalaOtp.isPresent()) {
            List<Usluga> usluge = uslugaService.findByFilijala(filijalaOtp.get());          
            if(usluge.isEmpty()) {
            	return new ResponseEntity<>("Filijala za uslugu nije pronadjen",
            			HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(uslugaService, HttpStatus.OK);
        }
        return new ResponseEntity<>("Usluga nije pronadjena", HttpStatus.NOT_FOUND);
    }
	
	
	
	
}
