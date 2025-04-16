package pt.arnaldocanelas.projetoapi.entities;

import java.io.Serializable;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AccountMovement implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  Long id;
	
	protected Instant moment;
    protected double amount;
    protected String description;
    protected Long originAccountNumber;
    protected Long destinationAccountNumber;

	public AccountMovement() {
	}

	public AccountMovement(Long id, Instant moment, double amount, String description, Long originAccountNumber,
			Long destinationAccountNumber) {
		super();
		this.id = id;
		this.moment = moment;
		this.amount = amount;
		this.description = description;
		this.originAccountNumber = originAccountNumber;
		this.destinationAccountNumber = destinationAccountNumber;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getOriginAccountNumber() {
		return originAccountNumber;
	}

	public void setOriginAccountNumber(Long originAccountNumber) {
		this.originAccountNumber = originAccountNumber;
	}

	public Long getDestinationAccountNumber() {
		return destinationAccountNumber;
	}

	public void setDestinationAccountNumber(Long destinationAccountNumber) {
		this.destinationAccountNumber = destinationAccountNumber;
	}

	// Método abstrato que será implementado por cada tipo de movimento
    public abstract void execute();
}
