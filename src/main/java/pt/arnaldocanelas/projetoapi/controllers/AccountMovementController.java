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
import pt.arnaldocanelas.projetoapi.dto.AccountMinDTO;
import pt.arnaldocanelas.projetoapi.dto.AccountMovementDTO;
import pt.arnaldocanelas.projetoapi.dto.AccountMovementReportDTO;
import pt.arnaldocanelas.projetoapi.entities.AccountMovement;
import pt.arnaldocanelas.projetoapi.services.AccountMovementService;
import pt.arnaldocanelas.projetoapi.services.AccountService;

@RestController
@RequestMapping(value = "/accountmovements")
public class AccountMovementController {

	@Autowired
	private AccountMovementService<AccountMovement> accountMovementService;
	
	@Autowired
	private AccountService<AccountMovement> accountService;
	
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
	
	@PostMapping
	public ResponseEntity<AccountMovementReportDTO> insert(@Valid @RequestBody AccountMovementDTO dto) {

		AccountMovementReportDTO responseDTO;
		
		responseDTO = movementLogic(dto);
		
		accountMovementService.insert(dto); 
		
		//to generate the correct HTTP response code 201 - Created
		//good programming practice Post 
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(dto.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(responseDTO);
			
	}
	
	private AccountMovementReportDTO movementLogic(AccountMovementDTO dto) {
		AccountMovementReportDTO responseDTO = new AccountMovementReportDTO();
		
		AccountMinDTO originAccount = accountService.findById(dto.getOriginAccountId());
		AccountMinDTO destinationAccount = accountService.findById(dto.getDestinationAccountId());
		
		responseDTO.setAmount(dto.getAmount());
		responseDTO.setType(dto.getType());
		responseDTO.setOriginAccountId(dto.getOriginAccountId());
		responseDTO.setOriginBankName(originAccount.getBankName());
		responseDTO.setOriginHolderName(originAccount.getHolderName());
		responseDTO.setOriginHolderNif(originAccount.getHolderNif());
		
		responseDTO.setDestinationAccountId(dto.getDestinationAccountId());
		responseDTO.setDestinationBankName(destinationAccount.getBankName());
		responseDTO.setDestinationHolderName(destinationAccount.getHolderName());
		
		responseDTO.setDestinationHolderNif(destinationAccount.getHolderNif());
		
		//TODO INSTANT apos operacao
		responseDTO.setOriginMovementMoment(dto.getMoment());
		
		//TODO INSTANT apos operacao
		responseDTO.setDestinationMovementMoment(dto.getMoment());
		
		return responseDTO;
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<AccountMovementDTO> updateById(@PathVariable Long id, @Valid @RequestBody AccountMovementDTO dto) {
		dto = accountMovementService.update(id, dto); 
		
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		accountMovementService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
}