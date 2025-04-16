package pt.arnaldocanelas.projetoapi.services;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.arnaldocanelas.projetoapi.controllers.exceptions.ResourceNotFoundException;
import pt.arnaldocanelas.projetoapi.dto.TransferDTO;
import pt.arnaldocanelas.projetoapi.entities.Account;
import pt.arnaldocanelas.projetoapi.entities.Transfer;
import pt.arnaldocanelas.projetoapi.repositories.AccountRepository;
import pt.arnaldocanelas.projetoapi.repositories.TransferRepository;
	
@Service
public class TransferService<T> {

	@Autowired
	private TransferRepository transferRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Transactional(readOnly = true)
	public TransferDTO findById(Long id) {
		Optional<Transfer> result = transferRepository.findById(id);
		Transfer entity = result.orElseThrow(
				()-> new ResourceNotFoundException("Resource " + id + " not found."));
		
		
		return new TransferDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public Page<TransferDTO> findAll(Pageable pageable) {
		
		Page<Transfer> result = transferRepository.findAll(pageable);
		
		//with lambda expression
		return result.map(x -> new TransferDTO(x));
	}
	
	@Transactional
	public TransferDTO insert(TransferDTO dto) {
		
		Long destinationAccountID = dto.getDestinationAccount().getId();
		
		if(!accountRepository.existsById(destinationAccountID)) 
		{
			throw new ResourceNotFoundException("Resource " + destinationAccountID + " not found.");
		}
		else 
		{
			Transfer entity = new Transfer(); 
			copyDtoToEntity(dto, entity);
			entity = transferRepository.save(entity);
			return new TransferDTO(entity);
		}
	}

	private void copyDtoToEntity(TransferDTO dto, Transfer entity) {
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