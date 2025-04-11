package pt.arnaldocanelas.projetoapi.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.arnaldocanelas.projetoapi.dto.AccountDTO;
import pt.arnaldocanelas.projetoapi.entities.Account;
import pt.arnaldocanelas.projetoapi.services.AccountService;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

	@Autowired
	private AccountService<Account> service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AccountDTO> findById(@PathVariable Long id) {
		AccountDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<AccountDTO>> getAll(Pageable pageable) {
		Page<AccountDTO> page = service.findAll(pageable); 
		
		return ResponseEntity.ok(page);
	}
	
	
}