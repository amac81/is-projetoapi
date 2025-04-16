package pt.arnaldocanelas.projetoapi.services;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.arnaldocanelas.projetoapi.controllers.exceptions.ResourceNotFoundException;
import pt.arnaldocanelas.projetoapi.dto.DepositDTO;
import pt.arnaldocanelas.projetoapi.entities.Account;
import pt.arnaldocanelas.projetoapi.entities.Deposit;
import pt.arnaldocanelas.projetoapi.entities.enums.MovementType;
import pt.arnaldocanelas.projetoapi.repositories.AccountRepository;
import pt.arnaldocanelas.projetoapi.repositories.DepositRepository;
	
@Service
public class DepositService<T> {

	@Autowired
	private DepositRepository depositRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountService<?> accountService;
	
	@Transactional(readOnly = true)
	public DepositDTO findById(Long id) {
		Optional<Deposit> result = depositRepository.findById(id);
		Deposit entity = result.orElseThrow(
				()-> new ResourceNotFoundException("Resource " + id + " not found."));

		return new DepositDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public Page<DepositDTO> findAll(Pageable pageable) {
		
		Page<Deposit> result = depositRepository.findAll(pageable);
		
		//with lambda expression
		return result.map(x -> new DepositDTO(x));
	}
	
	@Transactional
	public DepositDTO insert(DepositDTO dto) {
		
		Long destinationAccountID = dto.getDestinationAccount().getId();
		
		if(!accountRepository.existsById(destinationAccountID)) 
		{
			throw new ResourceNotFoundException("Resource " + destinationAccountID + " not found.");
		}
		else 
		{
			Deposit entity = new Deposit(); 
			copyDtoToEntity(dto, entity);
			
			//update account balance
			accountService.bankingMovement(destinationAccountID, dto.getAmount(), MovementType.CREDIT);
			
			entity = depositRepository.save(entity);
			return new DepositDTO(entity);
		}
	}

	private void copyDtoToEntity(DepositDTO dto, Deposit entity) {
		entity.setId(dto.getId());
		entity.setAmount(dto.getAmount());
		entity.setMoment(Instant.now());
		entity.setDescription(dto.getDescription());
		entity.setOriginBank(dto.getOriginBank());
		entity.setOriginAccountHolderName(dto.getOriginAccountHolderName());
		entity.setOriginAccountHolderNif(dto.getOriginAccountHolderNif());
		entity.setOriginAccountNumber(dto.getOriginAccountNumber());
		Account account = accountRepository.getReferenceById(dto.getDestinationAccount().getId());
		entity.setDestinationAccount(account);
	}
		
}