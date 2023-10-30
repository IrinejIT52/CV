package RVA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import RVA.Models.Filijala;
import RVA.Models.Korisnik;
import RVA.Models.Usluga;

public interface UslugaRepository extends JpaRepository<Usluga, Integer> {
	
	List<Usluga> findByNazivUslugeContainingIgnoreCase(String nazivUsluge);
	List<Usluga> findByKorisnik(Korisnik korisnik);
	List<Usluga> findByFilijala(Filijala filijala);
	
	List<Usluga> findByProvizija(double provizija);
	
	@Query(value="select * from Usluga where lower(naziv_usluge) like :pocetak%", nativeQuery=true)
	List<Usluga> getByPocetak(@Param("pocetak") String pocetak);
	
	
}
