package pt.arnaldocanelas.projetoapi.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import pt.arnaldocanelas.projetoapi.entities.AccountMovement;

public interface AccountMovementRepository extends JpaRepository<AccountMovement, Long> {
	
	
}