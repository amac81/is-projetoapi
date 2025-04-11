package pt.arnaldocanelas.projetoapi.services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.arnaldocanelas.projetoapi.dto.UserDTO;
import pt.arnaldocanelas.projetoapi.entities.User;
import pt.arnaldocanelas.projetoapi.exceptions.ResourceNotFoundException;
import pt.arnaldocanelas.projetoapi.repositories.UserRepository;

@Service
public class UserService<T> {

	@Autowired
	private UserRepository repository;
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> result = repository.findById(id);
		User entity = result.orElseThrow(
				()-> new ResourceNotFoundException("Recurso não encontrado"));
		
		
		return new UserDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public Page<UserDTO> findAll(Pageable pageable) {
		
		Page<User> result = repository.findAll(pageable);
		
		//with lambda expression
		return result.map(x -> new UserDTO(x));
	}
	
	
		
}