package pt.arnaldocanelas.projetoapi.entities;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transfer")
public class Transfer extends AccountMovement {
	private static final long serialVersionUID = 1L;

	private String destinationBank;
	private String destinationAccountHolderName;	
	private String destinationAccountHolderNif;
	private Long destinationAccountNumber;
	
	@ManyToOne
	@JoinColumn(name = "origin_account_number")
	private Account originAccount;
	
	public Transfer() {
	    super();
	}
	
	public Transfer(Long id, Instant moment, double amount, String description, String destinationBank, String destinationAccountHolderName, String destinationAccountHolderNif,
			Long destinationAccountNumber, Account originAccount) {
		super(id, moment, amount, description);
		this.destinationBank = destinationBank;
		this.destinationAccountHolderName = destinationAccountHolderName;
		this.destinationAccountHolderNif = destinationAccountHolderNif;
		this.destinationAccountNumber = destinationAccountNumber;
		this.originAccount = originAccount;
	}
	
	public String getDestinationBank() {
		return destinationBank;
	}

	public void setDestinationBank(String destinationBank) {
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

	public Long getDestinationAccountNumber() {
		return destinationAccountNumber;
	}

	public void setDestinationAccountNumber(Long destinationAccountNumber) {
		this.destinationAccountNumber = destinationAccountNumber;
	}

	public Account getOriginAccount() {
		return originAccount;
	}

	public void setOriginAccount(Account originAccount) {
		this.originAccount = originAccount;
	}

	@Override
	public void execute() {
		// TODO decrease, if possible amount at account
		
	}
	
	
}