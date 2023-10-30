package RVA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import RVA.Models.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
	
	List<Korisnik> findByImeKorisnikaContainingIgnoreCase(String imeKorsnika);
	
	@Query(value="select * from Korisnik where lower(ime_korisnika) like :pocetak%", nativeQuery=true)
	List<Korisnik> getByPocetak(@Param("pocetak") String pocetak);
}
