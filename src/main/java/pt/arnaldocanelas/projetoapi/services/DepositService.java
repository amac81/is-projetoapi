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
import pt.arnaldocanelas.projetoapi.dto.DepositDTO;
import pt.arnaldocanelas.projetoapi.dto.DepositMinDTO;
import pt.arnaldocanelas.projetoapi.entities.Account;
import pt.arnaldocanelas.projetoapi.entities.Deposit;
import pt.arnaldocanelas.projetoapi.repositories.DepositRepository;

@Service
public class DepositService<T> {

	@Autowired
	private DepositRepository repository;

	
	@Transactional(readOnly = true)
	public DepositMinDTO findById(Long id) {
		Optional<Deposit> result = repository.findById(id);
		Deposit entity = result.orElseThrow(
				()-> new ResourceNotFoundException("Recurso não encontrado"));
		
		
		return new DepositMinDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public Page<DepositMinDTO> findAll(Pageable pageable) {
		
		Page<Deposit> result = repository.findAll(pageable);
		
		//with lambda expression
		return result.map(x -> new DepositMinDTO(x));
	}
	
	@Transactional
	public DepositDTO insert(DepositDTO dto) {
		
		Deposit entity = new Deposit(); 
		
		copyDtoToEntity(dto, entity);
		
		entity = repository.save(entity);
		
		return new DepositDTO(entity);
	}
	
	@Transactional
	public DepositDTO update(Long id, DepositDTO dto) {
		try 
		{
			//does not go to the database; object monitored by JPA
			Deposit entity = repository.getReferenceById(id); 
			
			copyDtoToEntity(dto, entity);
					
			entity = repository.save(entity);
			
			return new DepositDTO(entity);
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
	
	private void copyDtoToEntity(DepositDTO dto, Deposit entity) {
		entity.setName(dto.getName());
		entity.setNif(dto.getNif());
		entity.setAge(dto.getAge());
		
		for(AccountDTO accountDto : dto.getAccounts()) {		
			Account account = accountRepository.getReferenceById(accountDto.getId());
			entity.getAccounts().add(account);
		};
	}
		
}