package pt.arnaldocanelas.projetoapi.dto;

import java.time.Instant;

import pt.arnaldocanelas.projetoapi.entities.Account;
import pt.arnaldocanelas.projetoapi.entities.Deposit;

public class DepositDTO {
	private Long id;
	private Instant moment;
	private Double amount;
	private String description;
	private String originBank;
	private String originAccountHolderName;	
	private String originAccountHolderNif;
	private Long originAccountNumber;
	private AccountIdDTO destinationAccount;
		
	public DepositDTO() {
	    super();
	}
	
	public DepositDTO(Long id, Instant moment, double amount, String description, String originBank, String originAccountHolderName, String originAccountHolderNif,
			Long originAccountNumber, Account destinationAccount) {
		super();
		
		this.id = id;
		this.amount = amount;
		this.moment = moment;
		this.description = description;		
		this.originBank = originBank;
		this.originAccountHolderName = originAccountHolderName;
		this.originAccountHolderNif = originAccountHolderNif;
		this.originAccountNumber = originAccountNumber;
		this.destinationAccount = new AccountIdDTO(destinationAccount);
	}
	
	public DepositDTO(Deposit entity) {
		this.id = entity.getId();
		this.moment = entity.getMoment();
		this.amount = entity.getAmount();
		this.description = entity.getDescription();		
		this.originBank = entity.getOriginBank();
		this.originAccountHolderName = entity.getOriginAccountHolderName();
		this.originAccountHolderNif = entity.getOriginAccountHolderNif();
		this.originAccountNumber = entity.getOriginAccountNumber();
		this.destinationAccount = new AccountIdDTO(entity.getDestinationAccount());
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

	public AccountIdDTO getDestinationAccount() {
		return destinationAccount;
	}

	public void setDestinationAccount(Account destinationAccount) {
		this.destinationAccount = new AccountIdDTO(destinationAccount) ;
	}
	
}