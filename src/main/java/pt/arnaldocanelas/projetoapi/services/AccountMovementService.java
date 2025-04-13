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
import pt.arnaldocanelas.projetoapi.dto.AccountMovementReportDTO;
import pt.arnaldocanelas.projetoapi.entities.AccountMovement;
import pt.arnaldocanelas.projetoapi.repositories.AccountMovementRepository;

@Service
public class AccountMovementService<T> {

	@Autowired
	private AccountMovementRepository accountMovementRepository;
	
	@Transactional(readOnly = true)
	public AccountMovementDTO findById(Long id) {
		Optional<AccountMovement> result = accountMovementRepository.findById(id);
		AccountMovement entity = result.orElseThrow(
				()-> new ResourceNotFoundException("Recurso não encontrado"));

		return new AccountMovementDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public Page<AccountMovementDTO> findAll(Pageable pageable) {
		
		Page<AccountMovement> result = accountMovementRepository.findAll(pageable);
		
		//with lambda expression
		return result.map(x -> new AccountMovementDTO(x));
	}
	
	
	@Transactional
	public AccountMovementReportDTO insert(AccountMovementDTO dto) {
		
		//Testar se é possível
		
		//TODO Identificar tipo de movimento
		//Registar movimentos das duas contas
		//e atualizar os saldos

		AccountMovement originAccountMovement = new AccountMovement(); 
		AccountMovement destinationAccountMovement = new AccountMovement();
		
		copyDtoToEntity(dto, originAccountMovement);
		copyDtoToEntity(dto, destinationAccountMovement);
			
		originAccountMovement.setMoment(Instant.now());
		destinationAccountMovement.setMoment(Instant.now());
		
		originAccountMovement = accountMovementRepository.save(originAccountMovement);
		destinationAccountMovement = accountMovementRepository.save(destinationAccountMovement);
			
		return new AccountMovementReportDTO(originAccountMovement, destinationAccountMovement);
	}
	
	@Transactional
	public AccountMovementDTO update(Long id, AccountMovementDTO dto) {
		try 
		{
			//does not go to the database; object monitored by JPA
			AccountMovement entity = accountMovementRepository.getReferenceById(id); 
			
			copyDtoToEntity(dto, entity);
					
			entity = accountMovementRepository.save(entity);
			
			return new AccountMovementDTO(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}

	}
	
	private void copyDtoToEntity(AccountMovementDTO dto, AccountMovement entity) {
		entity.setId(dto.getId());
		
		entity.setOriginAccountId(dto.getOriginAccountId());	
		entity.setDestinationAccountId(dto.getDestinationAccountId());
		
		entity.setAmount(dto.getAmount());
		entity.setType(dto.getType());
		entity.setMoment(dto.getMoment());
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void deleteById(Long id) {
		if(!accountMovementRepository.existsById(id)) 
		{
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try 
		{
			accountMovementRepository.deleteById(id);
		}	
		catch (DataIntegrityViolationException e) 
		{
        	throw new DatabaseException("Falha de integridade referencial");
		}	
	}
	
		
}