package pt.arnaldocanelas.projetoapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.arnaldocanelas.projetoapi.entities.Deposit;

@RestController
@RequestMapping(value = "/movements/deposits")
public class DepositController {

	@Autowired
	private DepositService<Deposit> service;

    
}