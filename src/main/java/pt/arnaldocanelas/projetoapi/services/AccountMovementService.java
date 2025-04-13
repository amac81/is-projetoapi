package pt.arnaldocanelas.projetoapi.services;
import java.time.Instant;
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
import pt.arnaldocanelas.projetoapi.dto.AccountMovementDTO;
import pt.arnaldocanelas.projetoapi.entities.AccountMovement;
import pt.arnaldocanelas.projetoapi.repositories.AccountMovementRepository;

@Service
public class AccountMovementService<T> {

	@Autowired
	private AccountMovementRepository repository;
	
	@Transactional(readOnly = true)
	public AccountMovementDTO findById(Long id) {
		Optional<AccountMovement> result = repository.findById(id);
		AccountMovement entity = result.orElseThrow(
				()-> new ResourceNotFoundException("Recurso não encontrado"));

		return new AccountMovementDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public Page<AccountMovementDTO> findAll(Pageable pageable) {
		
		Page<AccountMovement> result = repository.findAll(pageable);
		
		//with lambda expression
		return result.map(x -> new AccountMovementDTO(x));
	}
	
	
	@Transactional
	public AccountMovementDTO insert(AccountMovementDTO dto) {
		
		AccountMovement entity = new AccountMovement(); 
		
		copyDtoToEntity(dto, entity);
			
		entity.setMoment(Instant.now());
		
		System.out.println(dto);
		
		entity = repository.save(entity);
			
		return new AccountMovementDTO(entity);
	}
	
	@Transactional
	public AccountMovementDTO update(Long id, AccountMovementDTO dto) {
		try 
		{
			//does not go to the database; object monitored by JPA
			AccountMovement entity = repository.getReferenceById(id); 
			
			copyDtoToEntity(dto, entity);
					
			entity = repository.save(entity);
			
			return new AccountMovementDTO(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
	
	}
	
	private void copyDtoToEntity(AccountMovementDTO dto, AccountMovement entity) {
		entity.setId(dto.getId());
		entity.setOriginAccount(dto.getOriginAccount());
		entity.setDestinationAccount(dto.getDestinationAccount());
		entity.setAmount(dto.getAmount());
		entity.setType(dto.getType());
		entity.setMoment(dto.getMoment());
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void deleteById(Long id) {
		if(!repository.existsById(id)) 
		{
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try 
		{
			repository.deleteById(id);
		}	
		catch (DataIntegrityViolationException e) 
		{
        	throw new DatabaseException("Falha de integridade referencial");
		}	
	}
	
		
}