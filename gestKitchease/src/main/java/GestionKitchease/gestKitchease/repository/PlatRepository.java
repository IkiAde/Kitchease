package GestionKitchease.gestKitchease.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import GestionKitchease.gestKitchease.entity.Plat;

public interface PlatRepository extends CrudRepository <GestionKitchease.gestKitchease.entity.Plat, Long> {

	

	@Query("SELECT p FROM Plat p WHERE p.nom = :nom")
    Optional<Plat> findbyNom(@Param("nom") String nom);
}
