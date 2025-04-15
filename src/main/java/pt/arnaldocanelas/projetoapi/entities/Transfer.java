package pt.arnaldocanelas.projetoapi.entities;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "transfer")
public class Transfer extends AccountMovement {
	private static final long serialVersionUID = 1L;
	
	private String originBank;
	private String originAccountHolderName;	
	private String originAccountHolderNif;
	
	public Transfer(Long id, Instant moment, double amount, String description, Long originAccountNumber,
			Long destinationAccountNumber, String originBank, String originAccountHolderName,
			String originAccountHolderNif) {
		super(id, moment, amount, description, originAccountNumber, destinationAccountNumber);
		this.originBank = originBank;
		this.originAccountHolderName = originAccountHolderName;
		this.originAccountHolderNif = originAccountHolderNif;
	}

	public String getOriginBank() {
		return originBank;
	}

	public void setOriginBank(String originBank) {
		this.originBank = originBank;
	}

	public String getOriginAccountHolderName() {
		return originAccountHolderName;
	}

	public void setOriginAccountHolderName(String originAccountHolderName) {
		this.originAccountHolderName = originAccountHolderName;
	}

	public String getOriginAccountHolderNif() {
		return originAccountHolderNif;
	}

	public void setOriginAccountHolderNif(String originAccountHolderNif) {
		this.originAccountHolderNif = originAccountHolderNif;
	}

	@Override
    public void execute() {
        System.out.println("Depósito de " + amount + " executado.");
        // Aqui podes adicionar lógica para atualizar saldo de uma conta, por exemplo
    }
	
	
	
}