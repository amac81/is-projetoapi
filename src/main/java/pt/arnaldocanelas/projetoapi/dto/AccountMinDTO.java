package pt.arnaldocanelas.projetoapi.dto;

import java.time.LocalDate;

import pt.arnaldocanelas.projetoapi.entities.Account;

public class AccountMinDTO {
    
	private Long id;
	private String bankName;
	private Double balance;
	private LocalDate creationDate;
	
	public AccountMinDTO() {}

    public AccountMinDTO(Long id, String bankName, Double balance, LocalDate creationDate) {
    	this.id = id;
    	this.bankName = bankName;
		this.balance = balance;
		this.creationDate = creationDate;
	}

	public AccountMinDTO(Account entity) {
		this.id = entity.getId();
		this.bankName = entity.getBankName();
		this.balance = entity.getBalance();
		this.creationDate = entity.getCreationDate();
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
	
 }