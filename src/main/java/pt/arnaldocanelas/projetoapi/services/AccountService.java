package pt.arnaldocanelas.projetoapi.services;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
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
import pt.arnaldocanelas.projetoapi.dto.AccountDTO;
import pt.arnaldocanelas.projetoapi.dto.AccountMinDTO;
import pt.arnaldocanelas.projetoapi.entities.Account;
import pt.arnaldocanelas.projetoapi.entities.enums.MovementType;
import pt.arnaldocanelas.projetoapi.repositories.AccountRepository;

@Service
public class AccountService<T> {

	@Autowired
	private AccountRepository repository;
	
	private List<Account> accounts;

	public AccountService(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<Account> getAccounts() {
		return accounts;
	}
	
	@Transactional(readOnly = true)
	public AccountMinDTO findById(Long id) {
		Optional<Account> result = repository.findById(id);
		Account entity = result.orElseThrow(
				()-> new ResourceNotFoundException("Resource " + id + " not found."));
		
		return new AccountMinDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public Page<AccountMinDTO> findAll(Pageable pageable) {
		
		Page<Account> result = repository.findAll(pageable);
		
		//with lambda expression
		return result.map(x -> new AccountMinDTO(x));
	}
	
	@Transactional(readOnly = true)
	public Page<AccountDTO> findAllComplete(Pageable pageable) {
		
		Page<Account> result = repository.findAll(pageable);
		
		//with lambda expression
		return result.map(x -> new AccountDTO(x));
	}
	
	@Transactional
	public AccountDTO insert(AccountDTO dto) {
		
		Account entity = new Account(); 
		
		copyDtoToEntity(dto, entity);
		
		ZoneId fusoHorario = ZoneId.systemDefault(); // ou por exemplo: ZoneId.of("Europe/Lisbon")
        LocalDate dataAtual = Instant.now().atZone(fusoHorario).toLocalDate();
		
		entity.setCreationDate(dataAtual);
		
		if (repository.existsById(entity.getId())) {
			throw new DatabaseException("Resource already exists.");
		}
		
		entity = repository.save(entity);
			
		return new AccountDTO(entity);
	}
	
	@Transactional
	public AccountDTO update(Long id, AccountDTO dto) {
		try 
		{
			//does not go to the database; object monitored by JPA
			Account entity = repository.getReferenceById(id); 
			
			copyDtoToEntity(dto, entity);
					
			entity = repository.save(entity);
			
			return new AccountDTO(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Resource " + id + " not found.");
		}
	
	}
	
	private void copyDtoToEntity(AccountDTO dto, Account entity) {
		entity.setHolder(dto.getHolder());
		entity.setId(dto.getId());
		entity.setCreationDate(dto.getCreationDate());
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
	
	/**
	 * Search for an account by accountNumber (Id).
	 * 
	 * @param accountNumber (Id) of the account to be searched
	 * @return the searched account or null if not found
	 */
	public Account searchAccount(Long accountNumber) {

		for (Account account : accounts) {
			if (account.getId() == accountNumber)
				return account;
		}
		return null;
	}
	
	
	/**
	 * Transfers a certain amount from an Origin account to a Destination account.
	 * If there is not enough balance, the amount will not be transferred.
	 *
	 * @param value of the movement
	 * @param accountID account that will have the balance changed
	 * @return true, if the movement was successful.
	 * @throws BussinessException
	 */
	public boolean bankingMovement(Long accountID, double value, MovementType type) throws BussinessException {

		boolean success = false;

		Account account = searchAccount(accountID);

		if(type == MovementType.DEBIT) 
		{
			if (account.getBalance() >= value) {
				account.setBalance(account.getBalance() - value);
				success = true;
			} else {
				throw new BussinessException("Insufficient funds in the origin account!");
			}
		}else {
			if (value > 0) {
				account.setBalance(account.getBalance() + value);
				success = true;
			} else {
				throw new BussinessException("Invalid deposit amount!");
			}		
		}

		return success;
	}
	
		
}