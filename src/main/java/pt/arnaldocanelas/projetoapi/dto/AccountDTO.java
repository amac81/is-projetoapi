package pt.arnaldocanelas.projetoapi.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pt.arnaldocanelas.projetoapi.entities.Account;
import pt.arnaldocanelas.projetoapi.entities.User;

public class AccountDTO {
    
	private Long id;
	private User holder;
	private Double balance;
	private LocalDate creationDate;
	
	private List<DepositMinDTO> deposits = new ArrayList<>();
	
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
		
		deposits = entity.getDeposits().stream().map(x->new DepositMinDTO(x)).collect(Collectors.toList());

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
	
	public List<DepositMinDTO> getDeposits() {
		return deposits;
	}

	@Override
	public String toString() {
		return "AccountDTO [id=" + id + ", holder=" + holder + ", balance=" + balance
				+ ", creationDate=" + creationDate + "]";
	}
	
	

}