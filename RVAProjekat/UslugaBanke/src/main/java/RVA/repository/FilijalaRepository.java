package RVA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import RVA.Models.Banka;
import RVA.Models.Filijala;
import RVA.Models.Korisnik;
import RVA.Models.Usluga;

public interface FilijalaRepository extends JpaRepository<Filijala, Integer> {

	List<Filijala> findByAdresaFilijaleContainingIgnoreCase(String adresaFilijale);
	List<Filijala> findByBanka(Banka banka);
	
	@Query(value="select * from Filijala where lower(adresa_filijale) like :pocetak%", nativeQuery=true)
	List<Filijala> getByPocetak(@Param("pocetak") String pocetak);

	
}
