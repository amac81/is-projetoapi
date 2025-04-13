package pt.arnaldocanelas.projetoapi.dto;

import java.time.Instant;

import pt.arnaldocanelas.projetoapi.entities.Account;
import pt.arnaldocanelas.projetoapi.entities.AccountMovement;
import pt.arnaldocanelas.projetoapi.entities.enums.MovementType;


public class AccountMovementDTO {

	private Long id;
	private Double amount;
	private Account originAccount;
	private Account destinationAccount;
	private MovementType type;
	
	private Instant moment;

	public AccountMovementDTO() {}
	
	public AccountMovementDTO(AccountMovement entity) {
		this.id = entity.getId();
		this.amount = entity.getAmount();
		this.originAccount = entity.getOriginAccount();
		this.destinationAccount = entity.getDestinationAccount();
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

	public Account getOriginAccount() {
		return originAccount;
	}

	public void setOriginAccount(Account originAccount) {
		this.originAccount = originAccount;
	}

	public Account getDestinationAccount() {
		return destinationAccount;
	}

	public void setDestinationAccount(Account destinationAccount) {
		this.destinationAccount = destinationAccount;
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
