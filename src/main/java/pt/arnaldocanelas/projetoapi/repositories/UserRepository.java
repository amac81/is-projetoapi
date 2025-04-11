package pt.arnaldocanelas.projetoapi.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import pt.arnaldocanelas.projetoapi.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}