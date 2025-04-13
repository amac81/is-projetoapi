package pt.arnaldocanelas.projetoapi.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import pt.arnaldocanelas.projetoapi.entities.enums.BankingMovementType;

@Entity
@Table(name="tb_bankingmovement")
public class BankingMovement implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double amount;
	private String originBankName;
	private String destinationBankName;
	private Account originAccount;
	private Account destinationAccount;
	private String originAccountHolderName;
	private String originAccountHolderNif;
	private BankingMovementType bankingMovementType;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")//UTC
	private Instant moment;

	public BankingMovement() {}
	
	public BankingMovement(Double amount, String originBankName, String destinationBankName, Account originAccount,
			Account destinationAccount, String originAccountHolderName, String originAccountHolderNif,
			BankingMovementType bankingMovementType, Instant moment) {
		this.amount = amount;
		this.originBankName = originBankName;
		this.destinationBankName = destinationBankName;
		this.originAccount = originAccount;
		this.destinationAccount = destinationAccount;
		this.originAccountHolderName = originAccountHolderName;
		this.originAccountHolderNif = originAccountHolderNif;
		this.bankingMovementType = bankingMovementType;
		this.moment = moment;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getOriginBankName() {
		return originBankName;
	}

	public void setOriginBankName(String originBankName) {
		this.originBankName = originBankName;
	}

	public String getDestinationBankName() {
		return destinationBankName;
	}

	public void setDestinationBankName(String destinationBankName) {
		this.destinationBankName = destinationBankName;
	}

	public Account getOriginAccount() {
		return originAccount;
	}

	public void setOriginAccount(Account originAccount) {
		this.originAccount = originAccount;
	}

	public Account getDestinationAccount() {
		return destinationAccount;
	}

	public void setDestinationAccount(Account destinationAccount) {
		this.destinationAccount = destinationAccount;
	}

	public String getOriginAccountHolderName() {
		return originAccountHolderName;
	}

	public void setOriginAccountHolderName(String originAccountHolderName) {
		this.originAccountHolderName = originAccountHolderName;
	}

	public String getOriginAccountHolderNif() {
		return originAccountHolderNif;
	}

	public void setOriginAccountHolderNif(String originAccountHolderNif) {
		this.originAccountHolderNif = originAccountHolderNif;
	}

	public BankingMovementType getBankingMovementType() {
		return bankingMovementType;
	}

	public void setBankingMovementType(BankingMovementType bankingMovementType) {
		this.bankingMovementType = bankingMovementType;
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
		BankingMovement other = (BankingMovement) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "BankingMovement [amount=" + amount + ", originBankName=" + originBankName + ", destinationBankName="
				+ destinationBankName + ", originAccount=" + originAccount + ", destinationAccount="
				+ destinationAccount + ", originAccountHolderName=" + originAccountHolderName
				+ ", originAccountHolderNif=" + originAccountHolderNif + ", bankingMovementType=" + bankingMovementType
				+ ", moment=" + moment + "]";
	} 

}
