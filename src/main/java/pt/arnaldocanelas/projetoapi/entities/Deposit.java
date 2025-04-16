package pt.arnaldocanelas.projetoapi.entities;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "deposit")
public class Deposit extends AccountMovement {
	private static final long serialVersionUID = 1L;
	private String originBank;
	private String originAccountHolderName;	
	private String originAccountHolderNif;
	private Long originAccountNumber;
	
	@ManyToOne
	@JoinColumn(name = "destination_account_number")
	private Account destinationAccount;
		
	public Deposit() {
	    super();
	}
	
	public Deposit(Long id, Instant moment, double amount, String description, String originBank, String originAccountHolderName, String originAccountHolderNif,
			Long originAccountNumber, Account destinationAccount) {
		super(id, moment, amount, description);
		this.originBank = originBank;
		this.originAccountHolderName = originAccountHolderName;
		this.originAccountHolderNif = originAccountHolderNif;
		this.originAccountNumber = originAccountNumber;
		this.destinationAccount = destinationAccount;
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

	public Long getOriginAccountNumber() {
		return originAccountNumber;
	}

	public void setOriginAccountNumber(Long originAccountNumber) {
		this.originAccountNumber = originAccountNumber;
	}

	public Account getDestinationAccount() {
		return destinationAccount;
	}

	public void setDestinationAccount(Account destinationAccount) {
		this.destinationAccount = destinationAccount;
	}

	@Override
    public void execute() {
        System.out.println("Depósito de " + amount + " executado.");
        // Aqui podes adicionar lógica para atualizar saldo de uma conta, por exemplo
    }
	
	
	
}