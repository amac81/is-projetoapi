package pt.arnaldocanelas.projetoapi.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_account")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long number;
	private User holder;
	private Double balance;
	
	@Column(name="creationdate")
	private LocalDate creationDate;

	public Account() {
	}
	
	public Account(Long number, User holder, Double balance, LocalDate creationDate) {
		this.number = number;
		this.holder = holder;
		this.balance = balance;
		this.creationDate = creationDate;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public User getHolder() {
		return holder;
	}

	public void setHolder(User holder) {
		this.holder = holder;
	}

	public Double getBalance() {
		return balance;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(number, other.number);
	}
	
}

