package pt.arnaldocanelas.projetoapi.dto;

import java.time.Instant;

import pt.arnaldocanelas.projetoapi.entities.AccountMovement;
import pt.arnaldocanelas.projetoapi.entities.enums.MovementType;


public class AccountMovementReportDTO {

	private Double amount;
	private Long originAccountId;
	private String originBankName;
	private String originHolderName;
	private String originHolderNif;
	private Long destinationAccountId;
	private String destinationBankName;
	private String destinationHolderName;
	private String destinationHolderNif;
	private Instant originMovementMoment;
	private Instant destinationMovementMoment;
	private MovementType type; 
	
	public AccountMovementReportDTO() {}
	
	public AccountMovementReportDTO(AccountMovement originAccountMovement, AccountMovement destinationAccountMovement) {
		this.amount = originAccountMovement.getAmount();
		this.originAccountId = originAccountMovement.getId();
		this.destinationAccountId = destinationAccountMovement.getId();
		this.type = originAccountMovement.getType();
		
		this.originMovementMoment = originAccountMovement.getMoment();
		this.destinationMovementMoment = destinationAccountMovement.getMoment();
		
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

	public String getOriginBankName() {
		return originBankName;
	}

	public void setOriginBankName(String originBankName) {
		this.originBankName = originBankName;
	}

	public String getDestinationBankName() {
		return destinationBankName;
	}

	public void setDestinationBankName(String destinationBankName) {
		this.destinationBankName = destinationBankName;
	}

	public String getOriginHolderName() {
		return originHolderName;
	}

	public void setOriginHolderName(String originHolderName) {
		this.originHolderName = originHolderName;
	}

	public String getDestinationHolderName() {
		return destinationHolderName;
	}

	public void setDestinationHolderName(String destinationHolderName) {
		this.destinationHolderName = destinationHolderName;
	}

	public String getOriginHolderNif() {
		return originHolderNif;
	}

	public void setOriginHolderNif(String originHolderNif) {
		this.originHolderNif = originHolderNif;
	}

	public String getDestinationHolderNif() {
		return destinationHolderNif;
	}

	public void setDestinationHolderNif(String destinationHolderNif) {
		this.destinationHolderNif = destinationHolderNif;
	}

	public MovementType getType() {
		return type;
	}

	public void setType(MovementType type) {
		this.type = type;
	}

	public Instant getOriginMovementMoment() {
		return originMovementMoment;
	}

	public void setOriginMovementMoment(Instant originMovementMoment) {
		this.originMovementMoment = originMovementMoment;
	}

	public Instant getDestinationMovementMoment() {
		return destinationMovementMoment;
	}

	public void setDestinationMovementMoment(Instant destinationMovementMoment) {
		this.destinationMovementMoment = destinationMovementMoment;
	}

}
