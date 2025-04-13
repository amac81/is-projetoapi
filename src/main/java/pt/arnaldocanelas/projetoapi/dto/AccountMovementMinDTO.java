package pt.arnaldocanelas.projetoapi.dto;

import java.time.Instant;

import pt.arnaldocanelas.projetoapi.entities.AccountMovement;
import pt.arnaldocanelas.projetoapi.entities.enums.MovementType;


public class AccountMovementMinDTO {

	private Long id;
	private Double amount;
	private MovementType type;
	private String bankName;
	private Long accountNumber;
	private String holderName;
	private String holderNif;	
	private Instant moment;

	public AccountMovementMinDTO() {}
	
	public AccountMovementMinDTO(AccountMovement entity) {
		this.id = entity.getId();
		this.amount = entity.getAmount();
		this.bankName = entity.getAccount().getBankName();
		this.accountNumber = entity.getAccount().getId();
		this.holderName = entity.getAccount().getHolder().getName();
		this.holderNif = entity.getAccount().getHolder().getNif();
		this.type = entity.getType();
		this.moment = entity.getMoment();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public String getBankName() {
		return bankName;
	}
	
	public Long getAccountNumber() {
		return accountNumber;
	}
	
	public String getHolderName() {
		return holderName;
	}
	
	public String getHolderNif() {
		return holderNif;
	}

	public MovementType getType() {
		return type;
	}

	public void setType(MovementType type) {
		this.type = type;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}


}
