package pt.arnaldocanelas.projetoapi.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import pt.arnaldocanelas.projetoapi.entities.enums.MovementType;

@Entity
@Table(name="tb_accountmovement")
public class AccountMovement implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double amount;
	private Account originAccount;
	private Account destinationAccount;
	private MovementType type;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")//UTC
	private Instant moment;

	public AccountMovement() {}
	
	public AccountMovement(Long id, Double amount, Account originAccount, Account destinationAccount,
			MovementType type, Instant moment) {
		this.id = id;
		this.amount = amount;
		this.originAccount = originAccount;
		this.destinationAccount = destinationAccount;
		this.type = type;
		this.moment = moment;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountMovement other = (AccountMovement) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "BankingMovement [id=" + id + ", amount=" + amount + ", originAccount=" + originAccount
				+ ", destinationAccount=" + destinationAccount + ", type=" + type
				+ ", moment=" + moment + "]";
	}

}
