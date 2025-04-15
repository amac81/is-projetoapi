package pt.arnaldocanelas.projetoapi.entities;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "deposit_movement")
public class DepositMovement extends AccountMovement {
	private static final long serialVersionUID = 1L;
	
	private String destinationBank;
	private String destinationAccountHolderName;	
	private String destinationAccountHolderNif;
	
	public DepositMovement(Long id, Instant moment, double amount, String description, Long originAccountNumber,
			Long destinationAccountNumber, String destinationBank, String destinationAccountHolderName,
			String destinationAccountHolderNif) {
		super(id, moment, amount, description, originAccountNumber, destinationAccountNumber);
		this.destinationBank = destinationBank;
		this.destinationAccountHolderName = destinationAccountHolderName;
		this.destinationAccountHolderNif = destinationAccountHolderNif;
	}

	public String getDestinationBank() {
		return destinationBank;
	}

	public void setOriginBank(String destinationBank) {
		this.destinationBank = destinationBank;
	}

	public String getDestinationAccountHolderName() {
		return destinationAccountHolderName;
	}

	public void setDestinationAccountHolderName(String destinationAccountHolderName) {
		this.destinationAccountHolderName = destinationAccountHolderName;
	}

	public String getDestinationAccountHolderNif() {
		return destinationAccountHolderNif;
	}

	public void setDestinationAccountHolderNif(String destinationAccountHolderNif) {
		this.destinationAccountHolderNif = destinationAccountHolderNif;
	}

	@Override
    public void execute() {
        System.out.println("Depósito de " + amount + " executado.");
        // Aqui podes adicionar lógica para atualizar saldo de uma conta, por exemplo
    }
	
	
	
}