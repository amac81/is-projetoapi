package pt.arnaldocanelas.projetoapi.services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import pt.arnaldocanelas.projetoapi.controllers.exceptions.DatabaseException;
import pt.arnaldocanelas.projetoapi.controllers.exceptions.ResourceNotFoundException;
import pt.arnaldocanelas.projetoapi.dto.AccountDTO;
import pt.arnaldocanelas.projetoapi.dto.UserDTO;
import pt.arnaldocanelas.projetoapi.entities.Account;
import pt.arnaldocanelas.projetoapi.entities.User;
import pt.arnaldocanelas.projetoapi.repositories.AccountRepository;
import pt.arnaldocanelas.projetoapi.repositories.UserRepository;

@Service
public class UserService<T> {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> result = userRepository.findById(id);
		User entity = result.orElseThrow(
				()-> new ResourceNotFoundException("Recurso não encontrado"));
		
		
		return new UserDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public Page<UserDTO> findAll(Pageable pageable) {
		
		Page<User> result = userRepository.findAll(pageable);
		
		//with lambda expression
		return result.map(x -> new UserDTO(x));
	}
	
	@Transactional
	public UserDTO insert(UserDTO dto) {
		
		User entity = new User(); 
		
		copyDtoToEntity(dto, entity);
		
		entity = userRepository.save(entity);
		
		return new UserDTO(entity);
	}
	
	@Transactional
	public UserDTO update(Long id, UserDTO dto) {
		try 
		{
			//does not go to the database; object monitored by JPA
			User entity = userRepository.getReferenceById(id); 
			
			copyDtoToEntity(dto, entity);
					
			entity = userRepository.save(entity);
			
			return new UserDTO(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
	
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void deleteById(Long id) {
		if(!userRepository.existsById(id)) 
		{
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try 
		{
			userRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) 
		{
        	throw new DatabaseException("Falha de integridade referencial");
		}	
	}
	
	private void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setNif(dto.getNif());
		entity.setAge(dto.getAge());
		
		for(AccountDTO accountDto : dto.getAccounts()) {		
			Account account = accountRepository.getReferenceById(accountDto.getId());
			entity.getAccounts().add(account);
		};
	}
		
}