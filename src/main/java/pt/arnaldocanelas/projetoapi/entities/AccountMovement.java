package pt.arnaldocanelas.projetoapi.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@ManyToOne
	@JoinColumn(name = "accountnumber")
	private Account account;
	
	@Enumerated(EnumType.STRING)
	private MovementType type;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")//UTC
	private Instant moment;

	public AccountMovement() {}
	
	public AccountMovement(Long id, Double amount, Account account, MovementType type, Instant moment) {
		this.id = id;
		this.amount = amount;
		this.account = account;
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
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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
		return "AccountMovement [id=" + id + ", amount=" + amount + ", account=" + account + ", type=" + type
				+ ", moment=" + moment + "]";
	}

}
