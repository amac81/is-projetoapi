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
import pt.arnaldocanelas.projetoapi.controllers.exceptions.BussinessException;
import pt.arnaldocanelas.projetoapi.controllers.exceptions.DatabaseException;
import pt.arnaldocanelas.projetoapi.controllers.exceptions.ResourceNotFoundException;
import pt.arnaldocanelas.projetoapi.dto.AccountMovementDTO;
import pt.arnaldocanelas.projetoapi.dto.AccountMovementReportDTO;
import pt.arnaldocanelas.projetoapi.entities.Account;
import pt.arnaldocanelas.projetoapi.entities.AccountMovement;
import pt.arnaldocanelas.projetoapi.entities.enums.MovementType;
import pt.arnaldocanelas.projetoapi.repositories.AccountMovementRepository;
import pt.arnaldocanelas.projetoapi.repositories.AccountRepository;

@Service
public class AccountMovementService<T> {

	@Autowired
	private AccountMovementRepository accountMovementRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
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
		
		boolean success = transferValue(dto.getOriginAccountId(), dto.getDestinationAccountId(), dto.getAmount());
		
		if(!success) 
		{
			throw new BussinessException("Insufficient funds in the origin account!");
		}
		
		//sucess
		
		copyDtoToEntity(dto, originAccountMovement, MovementType.DEBIT);
		originAccountMovement = accountMovementRepository.save(originAccountMovement);
		
		copyDtoToEntity(dto, destinationAccountMovement, MovementType.CREDIT);
		destinationAccountMovement = accountMovementRepository.save(destinationAccountMovement);
			
		return new AccountMovementReportDTO(originAccountMovement, destinationAccountMovement);
	}
	

	
	/**
	 * Transfers a certain amount from an Origin account to a Destination account.
	 * If there is not enough balance, the amount will not be transferred.
	 *
	 * @param originAccountId  account that will have the amount deducted
	 * @param ammount value to be transferred
	 * @param destinAccountId account that will have the value increased
	 * @return true, if the transfer was successful.
	 */
	public boolean transferValue(Long originAccountId, Long destinAccountId, double ammount) {

		boolean success = false;
		
		Optional<Account> result = accountRepository.findById(originAccountId);
		Account originAccount = result.orElseThrow(
				()-> new ResourceNotFoundException("Recurso não encontrado"));
		
		result = accountRepository.findById(destinAccountId);
		Account destinAccount = result.orElseThrow(
				()-> new ResourceNotFoundException("Recurso não encontrado"));
		
		if (originAccount.getBalance() >= ammount) {
			destinAccount.setBalance(destinAccount.getBalance() + ammount);
			originAccount.setBalance(originAccount.getBalance() - ammount);
			success = true;
		}
		
		return success;
	}


	@Transactional
	public AccountMovementDTO update(Long id, AccountMovementDTO dto) {
		try 
		{
			//does not go to the database; object monitored by JPA
			AccountMovement entity = accountMovementRepository.getReferenceById(id); 
			
			copyDtoToEntity(dto, entity, null);
					
			entity = accountMovementRepository.save(entity);
			
			return new AccountMovementDTO(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}

	}
	
	private void copyDtoToEntity(AccountMovementDTO dto, AccountMovement entity, MovementType type) {
		entity.setId(dto.getId());
		
		double amount = dto.getAmount();
		if(type == MovementType.DEBIT) {
			amount*=-1;
		}
		
		entity.setOriginAccountId(dto.getOriginAccountId());	
		entity.setDestinationAccountId(dto.getDestinationAccountId());
		
		entity.setAmount(amount);
		entity.setType(type);
		entity.setMoment(Instant.now());
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