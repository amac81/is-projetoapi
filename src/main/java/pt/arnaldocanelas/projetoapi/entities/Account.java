package pt.arnaldocanelas.projetoapi.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_account")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique = true, name = "accountnumber")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "holder_id")
	private User holder;
	
	private Double balance;
	
	@Column(name="creationdate")
	private LocalDate creationDate;
	
	@JsonIgnore  // Ignora a coleção de accounts durante a serialização
	@OneToMany(mappedBy = "destinationAccount", fetch = FetchType.LAZY)
	private List<Deposit> deposits = new ArrayList<>();
	
	@JsonIgnore  // Ignora a coleção de accounts durante a serialização
	@OneToMany(mappedBy = "originAccount", fetch = FetchType.LAZY)
	private List<Transfer> transfers = new ArrayList<>();
	
	public Account() {
		balance = 0.0;
	}
	
	public Account(Long id, User holder, Double balance, LocalDate creationDate) {
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
	
	public User getHolder() {
		return holder;
	}

	public void setHolder(User holder) {
		this.holder = holder;
	}

	public Double getBalance() {
		return balance;
	}
	
	public void setBalance(Double value) {
		balance = value;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	
	public List<Deposit> getDeposits() {
		return deposits;
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

	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + "]";
	}
		
}

