package pt.arnaldocanelas.projetoapi.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.arnaldocanelas.projetoapi.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional <User> findByUsername(String username);
	
}