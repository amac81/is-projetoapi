package pt.arnaldocanelas.projetoapi.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import pt.arnaldocanelas.projetoapi.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}