package pt.arnaldocanelas.projetoapi.dto;

import pt.arnaldocanelas.projetoapi.entities.Deposit;

import java.time.Instant;

public class DepositDTO {

    private Long id;
    private Instant moment;
    private double amount;
    private String description;
    private Long originAccountNumber;
    private Long destinationAccountNumber;
    private String destinationBank;
    private String destinationAccountHolderName;
    private String destinationAccountHolderNif;

    public DepositDTO() {}

    public DepositDTO(Long id, Instant moment, double amount, String description,
                      Long originAccountNumber, Long destinationAccountNumber,
                      String destinationBank, String destinationAccountHolderName,
                      String destinationAccountHolderNif) {
        this.id = id;
        this.moment = moment;
        this.amount = amount;
        this.description = description;
        this.originAccountNumber = originAccountNumber;
        this.destinationAccountNumber = destinationAccountNumber;
        this.destinationBank = destinationBank;
        this.destinationAccountHolderName = destinationAccountHolderName;
        this.destinationAccountHolderNif = destinationAccountHolderNif;
    }

    public DepositDTO(Deposit entity) {
        this.id = entity.getId();
        this.moment = entity.getMoment();
        this.amount = entity.getAmount();
        this.description = entity.getDescription();
        this.originAccountNumber = entity.getOriginAccountNumber();
        this.destinationAccountNumber = entity.getDestinationAccountNumber();
        this.destinationBank = entity.getDestinationBank();
        this.destinationAccountHolderName = entity.getDestinationAccountHolderName();
        this.destinationAccountHolderNif = entity.getDestinationAccountHolderNif();
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOriginAccountNumber() {
        return originAccountNumber;
    }

    public void setOriginAccountNumber(Long originAccountNumber) {
        this.originAccountNumber = originAccountNumber;
    }

    public Long getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(Long destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public String getDestinationBank() {
        return destinationBank;
    }

    public void setDestinationBank(String destinationBank) {
        this.destinationBank = destinationBank;
    }

    public String getDestinationAccountHolderName() {
        return destinationAccountHolderName;
    }

    public void setDestinationAccountHolderName(String destinationAccountHolderName) {
        this.destinationAccountHolderName = destinationAccountHolderName;
    }

    public String getDestinationAccountHolderNif() {
        return destinationAccountHolderNif;
    }

    public void setDestinationAccountHolderNif(String destinationAccountHolderNif) {
    }
    
}