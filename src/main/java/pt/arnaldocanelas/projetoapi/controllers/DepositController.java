package pt.arnaldocanelas.projetoapi.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import pt.arnaldocanelas.projetoapi.dto.DepositDTO;
import pt.arnaldocanelas.projetoapi.entities.Deposit;
import pt.arnaldocanelas.projetoapi.services.DepositService;

@RestController
@RequestMapping(value = "/deposits")
public class DepositController {

	@Autowired
	private DepositService<Deposit> service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DepositDTO> findById(@PathVariable Long id) {
		DepositDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<DepositDTO>> getAll(Pageable pageable) {
		Page<DepositDTO> page = service.findAll(pageable); 
		
		return ResponseEntity.ok(page);
	}
	
	@PostMapping
	public ResponseEntity<DepositDTO> insert(@Valid @RequestBody DepositDTO dto) {
		
		dto = service.insert(dto); 
		
		//to generate the correct HTTP response code 201 - Created
		//good programming practice Post 
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(dto.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}
}