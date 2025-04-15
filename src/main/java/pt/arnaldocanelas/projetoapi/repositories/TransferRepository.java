package pt.arnaldocanelas.projetoapi.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import pt.arnaldocanelas.projetoapi.entities.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
	
	
}