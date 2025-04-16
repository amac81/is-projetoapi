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
	private AccountIdDTO originAccountNumber;
	private Long destinationAccount;
		
	public TransferDTO() {
	    super();
	}
	
	public TransferDTO(Long id, Instant moment, double amount, String description, String originBank, String destinationAccountHolderName, String destinationAccountHolderNif,
			Account originAccountNumber, Long destinationAccount) {
		super();
		
		this.id = id;
		this.amount = amount;
		this.moment = moment;
		this.description = description;		
		this.destinationBank = originBank;
		this.destinationAccountHolderName = destinationAccountHolderName;
		this.destinationAccountHolderNif = destinationAccountHolderNif;
		this.originAccountNumber = new AccountIdDTO(originAccountNumber);
		this.destinationAccount = destinationAccount;
	}
	
	public TransferDTO(Transfer entity) {
		this.id = entity.getId();
		this.moment = entity.getMoment();
		this.amount = entity.getAmount();
		this.description = entity.getDescription();		
		this.destinationBank = entity.getdes();
		this.destinationAccountHolderName = entity.getOriginAccountHolderName();
		this.destinationAccountHolderNif = entity.getOriginAccountHolderNif();
		this.originAccountNumber = new AccountIdDTO(entity.getOriginAccountNumber());
		this.destinationAccount = entity.getDestinationAccount().getId();
	}


}