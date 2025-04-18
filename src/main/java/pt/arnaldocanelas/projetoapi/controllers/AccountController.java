package pt.arnaldocanelas.projetoapi.controllers;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pt.arnaldocanelas.projetoapi.dto.AccountMinDTO;
import pt.arnaldocanelas.projetoapi.entities.Account;
import pt.arnaldocanelas.projetoapi.services.AccountService;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

	@Autowired
	private AccountService<Account> service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AccountMinDTO> findById(@PathVariable Long id) {
		AccountMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<AccountMinDTO>> getAll(Pageable pageable) {
		Page<AccountMinDTO> page = service.findAll(pageable); 
		
		return ResponseEntity.ok(page);
	}
	
	
	@PostMapping
	public ResponseEntity<AccountMinDTO> autoAccount() {
		
		AccountMinDTO dto;
		
		dto = service.auto(); 
		
		//to generate the correct HTTP response code 201 - Created
		//good programming practice Post 
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/").buildAndExpand()
				.toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}
	
	
}