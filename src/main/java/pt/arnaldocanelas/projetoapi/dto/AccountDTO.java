package pt.arnaldocanelas.projetoapi.dto;

import java.time.LocalDate;

import pt.arnaldocanelas.projetoapi.entities.Account;
import pt.arnaldocanelas.projetoapi.entities.User;

public class AccountDTO {
    
	private Long id;
	private User holder;
	private Double balance;
	private LocalDate creationDate;
	
	public AccountDTO() {}

    public AccountDTO(Long id, User holder, Double balance, LocalDate creationDate) {
    	this.id = id;
    	this.holder = holder;
		this.balance = balance;
		this.creationDate = creationDate;
	}

	public AccountDTO(Account entity) {
		this.id = entity.getId();
		this.holder = entity.getHolder();
		this.balance = entity.getBalance();
		this.creationDate = entity.getCreationDate();
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

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "AccountDTO [id=" + id + ", holder=" + holder + ", balance=" + balance
				+ ", creationDate=" + creationDate + "]";
	}
	
	

}