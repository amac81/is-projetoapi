package pt.arnaldocanelas.projetoapi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.arnaldocanelas.projetoapi.entities.Transfer;
import pt.arnaldocanelas.projetoapi.repositories.TransferRepository;

@RestController
@RequestMapping(value = "/movements/deposits")
public class TransferController {

    private final TransferRepository repository;

    public TransferController(TransferRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Transfer> create(@RequestBody Transfer movement) {
    	Transfer saved = repository.save(movement);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Transfer>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }
}