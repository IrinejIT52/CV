package RVA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import RVA.Models.Banka;

public interface BankaRepository extends JpaRepository<Banka, Integer> {
	
	List<Banka> findByNazivBankeContainingIgnoreCase(String nazivBanke);
	
	/*1.nacin
	 * @Query(value="select * from banka where lower(nazivBanke) like ?1% ",
	 * nativeQuery=true) List<Banka> getByPocetak(String pocetak);
	 */
	
	//2.nacin
	@Query(value="select * from Banka where lower(naziv_banke) like :pocetak%", nativeQuery=true)
	List<Banka> getByPocetak(@Param("pocetak") String pocetak);
}
