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
import pt.arnaldocanelas.projetoapi.dto.AccountMovementDTO;
import pt.arnaldocanelas.projetoapi.entities.AccountMovement;
import pt.arnaldocanelas.projetoapi.services.AccountMovementService;

@RestController
@RequestMapping(value = "/accountmovements")
public class AccountMovementController {

	@Autowired
	private AccountMovementService<AccountMovement> accountMovementService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<AccountMovementDTO> findById(@PathVariable Long id) {
		AccountMovementDTO dto = accountMovementService.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<AccountMovementDTO>> getAll(Pageable pageable) {
		Page<AccountMovementDTO> page = accountMovementService.findAll(pageable); 
		
		return ResponseEntity.ok(page);
	}
	
	@PostMapping(value = "/deposit")
	public ResponseEntity<AccountMovementDTO> deposit(@Valid @RequestBody AccountMovementDTO dto) {
		
		dto = accountMovementService.insert(dto); 
		
		//to generate the correct HTTP response code 201 - Created
		//good programming practice Post 
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(dto.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(dto);
			
	}
	
	@PostMapping(value = "/transfer")
	public ResponseEntity<AccountMovementDTO> transfer(@Valid @RequestBody AccountMovementDTO dto) {

		AccountMovementDTO responseDTO;
		
		//responseDTO = movementLogic(dto);
		
		responseDTO = accountMovementService.insert(dto); 
		
		//to generate the correct HTTP response code 201 - Created
		//good programming practice Post 
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(dto.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(responseDTO);
			
	}
}