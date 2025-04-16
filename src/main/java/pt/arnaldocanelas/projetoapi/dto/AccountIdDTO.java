package pt.arnaldocanelas.projetoapi.dto;

import pt.arnaldocanelas.projetoapi.entities.Account;

public class AccountIdDTO {

    private Long id;

    public AccountIdDTO() {}

    public AccountIdDTO(Long id) {
        this.id = id;
    }

    public AccountIdDTO(Account entity) {
		this.id = entity.getId();
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}