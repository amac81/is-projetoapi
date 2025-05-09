package pt.arnaldocanelas.projetoapi.controllers;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import pt.arnaldocanelas.projetoapi.dto.UserDTO;
import pt.arnaldocanelas.projetoapi.dto.UserMinDTO;
import pt.arnaldocanelas.projetoapi.entities.User;
import pt.arnaldocanelas.projetoapi.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService<User> service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserMinDTO> findById(@PathVariable Long id) {
		UserMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	
	@GetMapping
	public ResponseEntity<Page<UserMinDTO>> getAll(Pageable pageable) {
		Page<UserMinDTO> page = service.findAll(pageable); 
		
		return ResponseEntity.ok(page);
	}
	
	
	@PostMapping
	public ResponseEntity<UserMinDTO> insert(@Valid @RequestBody UserMinDTO dto) {
		
		dto = service.insert(dto); 
		
		//to generate the correct HTTP response code 201 - Created
		//good programming practice Post 
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(dto.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> updateById(@PathVariable Long id, @Valid @RequestBody UserDTO dto) {
		dto = service.update(id, dto); 
		
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
}