package pt.arnaldocanelas.projetoapi.services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	
		
}