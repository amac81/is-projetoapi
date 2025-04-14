package pt.arnaldocanelas.projetoapi.dto;

import java.time.Instant;

import pt.arnaldocanelas.projetoapi.entities.Account;
import pt.arnaldocanelas.projetoapi.entities.AccountMovement;


public class AccountMovementDTO {

	private Long id;
	private Double amount;
	
	
	private Account account;
	private Instant moment;

	public AccountMovementDTO() {}
	
	public AccountMovementDTO(AccountMovement entity) {
		this.id = entity.getId();
		this.amount = entity.getAmount();
		this.account = entity.getAccount();
		this.moment = entity.getMoment();
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

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	@Override
	public String toString() {
		return "AccountMovementDTO [id=" + id + ", amount=" + amount + ", account=" + account + ", moment=" + moment
				+ "]";
	}
}
