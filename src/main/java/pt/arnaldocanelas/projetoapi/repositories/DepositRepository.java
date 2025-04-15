package pt.arnaldocanelas.projetoapi.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import pt.arnaldocanelas.projetoapi.entities.Deposit;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
	
	
}