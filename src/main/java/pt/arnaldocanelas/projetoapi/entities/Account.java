package pt.arnaldocanelas.projetoapi.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_account")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique = true, name = "accountnumber")
	private Long id;
	
	@Column(name="bankname")
	private String bankName;
	
	@ManyToOne
	@JoinColumn(name = "holder_id")
	private User holder;
	
	private Double balance;
	
	@Column(name="creationdate")
	private LocalDate creationDate;

	public Account() {
	}
	
	public Account(Long id, String bankName, User holder, Double balance, LocalDate creationDate) {
		this.bankName = bankName;
		this.id = id;
		this.holder = holder;
		this.balance = balance;
		this.creationDate = creationDate;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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
	
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
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
		Account other = (Account) obj;
		return Objects.equals(id, other.id);
	}
	
}

