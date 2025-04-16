package pt.arnaldocanelas.projetoapi.dto;

import java.time.Instant;

import pt.arnaldocanelas.projetoapi.entities.Account;
import pt.arnaldocanelas.projetoapi.entities.Transfer;

public class TransferDTO {
	private Long id;
	private Instant moment;
	private Double amount;
	private String description;
	private String destinationBank;
	private String destinationAccountHolderName;	
	private String destinationAccountHolderNif;
	private AccountIdDTO originAccount;
	private Long destinationAccountNumber;
		
	public TransferDTO() {
	    super();
	}
	
	public TransferDTO(Long id, Instant moment, double amount, String description, String originBank, String destinationAccountHolderName, String destinationAccountHolderNif,
			Account originAccount, Long destinationAccountNumber) {
		super();
		
		this.id = id;
		this.amount = amount;
		this.moment = moment;
		this.description = description;		
		this.destinationBank = originBank;
		this.destinationAccountHolderName = destinationAccountHolderName;
		this.destinationAccountHolderNif = destinationAccountHolderNif;
		this.originAccount = new AccountIdDTO(originAccount);
		this.destinationAccountNumber = destinationAccountNumber;
	}
	
	public TransferDTO(Transfer entity) {
		this.id = entity.getId();
		this.moment = entity.getMoment();
		this.amount = entity.getAmount();
		this.description = entity.getDescription();		
		this.destinationBank = entity.getDestinationBank();
		this.destinationAccountHolderName = entity.getDestinationAccountHolderName();
		this.destinationAccountHolderNif = entity.getDestinationAccountHolderNif();
		this.originAccount = new AccountIdDTO(entity.getOriginAccount());
		this.destinationAccountNumber = entity.getDestinationAccountNumber();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public AccountIdDTO getOriginAccount() {
		return originAccount;
	}

	public void setOriginAccount(AccountIdDTO originAccount) {
		this.originAccount = originAccount;
	}

	public Long getDestinationAccountNumber() {
		return destinationAccountNumber;
	}

	public void setDestinationAccountNumber(Long destinationAccountNumber) {
		this.destinationAccountNumber = destinationAccountNumber;
	}

}