package pt.arnaldocanelas.projetoapi.services;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
import pt.arnaldocanelas.projetoapi.entities.Account;
import pt.arnaldocanelas.projetoapi.repositories.AccountRepository;

@Service
public class AccountService<T> {

	@Autowired
	private AccountRepository repository;
	
	@Transactional(readOnly = true)
	public AccountDTO findById(Long id) {
		Optional<Account> result = repository.findById(id);
		Account entity = result.orElseThrow(
				()-> new ResourceNotFoundException("Recurso não encontrado"));
		
		
		return new AccountDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public Page<AccountDTO> findAll(Pageable pageable) {
		
		Page<Account> result = repository.findAll(pageable);
		
		//with lambda expression
		return result.map(x -> new AccountDTO(x));
	}
	
	
	@Transactional
	public AccountDTO insert(AccountDTO dto) {
		
		Account entity = new Account(); 
		
		entity.setHolder(dto.getHolder());
		entity.setNumber(dto.getNumber());
		
		ZoneId fusoHorario = ZoneId.systemDefault(); // ou por exemplo: ZoneId.of("Europe/Lisbon")
        LocalDate dataAtual = Instant.now().atZone(fusoHorario).toLocalDate();
		
		entity.setCreationDate(dataAtual);
		
		entity = repository.save(entity);
			
		return new AccountDTO(entity);
	}
	
	@Transactional
	public AccountDTO update(Long id, AccountDTO dto) {
		try 
		{
			//does not go to the database; object monitored by JPA
			Account entity = repository.getReferenceById(id); 
			
			entity.setHolder(dto.getHolder());
			entity.setNumber(dto.getNumber());
			entity.setCreationDate(dto.getCreationDate());
					
			entity = repository.save(entity);
			
			return new AccountDTO(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
	
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