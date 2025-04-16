package pt.arnaldocanelas.projetoapi.dto;

import java.time.LocalDate;

import pt.arnaldocanelas.projetoapi.entities.Account;

public class AccountMinDTO {
    
	private Long accountNumber;
	private String holderName;
	private String holderNif;
	private Double balance;
	private LocalDate creationDate;
	
	public AccountMinDTO() {}

    public AccountMinDTO(String holderName, String holderNif, Double balance, LocalDate creationDate) {
     	this.holderName = holderName;
    	this.holderNif = holderNif;
		this.balance = balance;
		this.creationDate = creationDate;
	}

	public AccountMinDTO(Account entity) {
		this.accountNumber = entity.getId();
		this.holderName = entity.getHolder().getName();
		this.holderNif = entity.getHolder().getNif();
		this.balance = entity.getBalance();
		this.creationDate = entity.getCreationDate();
    }

	
	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getHolderName() {
		return holderName;
	}
	
	public String getHolderNif() {
		return holderNif;
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
	
 }