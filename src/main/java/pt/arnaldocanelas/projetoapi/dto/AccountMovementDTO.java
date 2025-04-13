package pt.arnaldocanelas.projetoapi.dto;

import java.time.Instant;

import pt.arnaldocanelas.projetoapi.entities.AccountMovement;
import pt.arnaldocanelas.projetoapi.entities.enums.MovementType;


public class AccountMovementDTO {

	private Long id;
	private Double amount;
	private Long originAccountId;
	private Long destinationAccountId;
	private MovementType type;
	
	private Instant moment;

	public AccountMovementDTO() {}
	
	public AccountMovementDTO(AccountMovement entity) {
		this.id = entity.getId();
		this.amount = entity.getAmount();
		
		this.originAccountId = entity.getOriginAccountId();
		this.destinationAccountId = entity.getDestinationAccountId();
		
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
	
	public Long getOriginAccountId() {
		return originAccountId;
	}

	public void setOriginAccountId(Long originAccountId) {
		this.originAccountId = originAccountId;
	}

	public Long getDestinationAccountId() {
		return destinationAccountId;
	}

	public void setDestinationAccountId(Long destinationAccountId) {
		this.destinationAccountId = destinationAccountId;
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

	@Override
	public String toString() {
		return "AccountMovementDTO [id=" + id + ", amount=" + amount + ", originAccountId=" + originAccountId
				+ ", destinationAccountId=" + destinationAccountId + ", type=" + type + ", moment=" + moment + "]";
	}

	
}
