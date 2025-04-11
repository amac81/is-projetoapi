package pt.arnaldocanelas.projetoapi.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.arnaldocanelas.projetoapi.dto.UserDTO;
import pt.arnaldocanelas.projetoapi.entities.User;
import pt.arnaldocanelas.projetoapi.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService<User> service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		UserDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	
	@GetMapping
	public ResponseEntity<Page<UserDTO>> getAll(Pageable pageable) {
		Page<UserDTO> page = service.findAll(pageable); 
		
		return ResponseEntity.ok(page);
	}
	
	
}