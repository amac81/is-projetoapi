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
import pt.arnaldocanelas.projetoapi.dto.DepositDTO;
import pt.arnaldocanelas.projetoapi.entities.Deposit;
import pt.arnaldocanelas.projetoapi.repositories.DepositRepository;
	
@Service
public class DepositService<T> {

	@Autowired
	private DepositRepository repository;

	
	@Transactional(readOnly = true)
	public DepositDTO findById(Long id) {
		Optional<Deposit> result = repository.findById(id);
		Deposit entity = result.orElseThrow(
				()-> new ResourceNotFoundException("Resource " + id + " not found."));
		
		
		return new DepositDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public Page<DepositDTO> findAll(Pageable pageable) {
		
		Page<Deposit> result = repository.findAll(pageable);
		
		//with lambda expression
		return result.map(x -> new DepositDTO(x));
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
			throw new ResourceNotFoundException("Resource " + id + " not found.");
		}
	
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void deleteById(Long id) {
		if(!repository.existsById(id)) 
		{
			throw new ResourceNotFoundException("Resource " + id + " not found.");
		}
		try 
		{
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) 
		{
        	throw new DatabaseException("Referential integrity failure.");
		}	
	}
	
	private void copyDtoToEntity(DepositDTO dto, Deposit entity) {
	    entity.setMoment(dto.getMoment());
	    entity.setAmount(dto.getAmount());
	    entity.setDescription(dto.getDescription());
	    entity.setOriginAccountNumber(dto.getOriginAccountNumber());
	    entity.setDestinationAccountNumber(dto.getDestinationAccountNumber());
	    entity.setDestinationBank(dto.getDestinationBank());
	    entity.setDestinationAccountHolderName(dto.getDestinationAccountHolderName());
	    entity.setDestinationAccountHolderNif(dto.getDestinationAccountHolderNif());
	}

		
}