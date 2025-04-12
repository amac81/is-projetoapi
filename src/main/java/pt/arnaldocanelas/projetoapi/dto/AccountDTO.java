package pt.arnaldocanelas.projetoapi.dto;

import java.time.LocalDate;

import pt.arnaldocanelas.projetoapi.entities.Account;
import pt.arnaldocanelas.projetoapi.entities.User;

public class AccountDTO {
    
	private Long id;
	private Long number;
	private User holder;
	private Double balance;
	private LocalDate creationDate;

    public AccountDTO(Long id, Long number, User holder, Double balance, LocalDate creationDate) {
		this.id = id;
    	this.number = number;
		this.holder = holder;
		this.balance = balance;
		this.creationDate = creationDate;
	}

	public AccountDTO(Account entity) {
		this.id = entity.getId();
		this.number = entity.getNumber();
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

	public Long getNumber() {
		return number;
	}

	public User getHolder() {
		return holder;
	}

	public Double getBalance() {
		return balance;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public void setHolder(User holder) {
		this.holder = holder;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	
  
 }