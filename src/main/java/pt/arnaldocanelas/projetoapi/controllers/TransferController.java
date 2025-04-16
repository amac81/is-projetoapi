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
import pt.arnaldocanelas.projetoapi.dto.TransferDTO;
import pt.arnaldocanelas.projetoapi.entities.Transfer;
import pt.arnaldocanelas.projetoapi.services.TransferService;

@RestController
@RequestMapping(value = "/transfers")
public class TransferController {

	@Autowired	
	private TransferService<Transfer> service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TransferDTO> findById(@PathVariable Long id) {
		TransferDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<TransferDTO>> getAll(Pageable pageable) {
		Page<TransferDTO> page = service.findAll(pageable); 
		
		return ResponseEntity.ok(page);
	}
	
	@PostMapping
	public ResponseEntity<TransferDTO> insert(@Valid @RequestBody TransferDTO dto) {
		
		dto = service.insert(dto); 
		
		//to generate the correct HTTP response code 201 - Created
		//good programming practice Post 
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(dto.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}
}