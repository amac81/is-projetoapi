package pt.arnaldocanelas.projetoapi.dto;

import java.util.ArrayList;
import java.util.List;

import pt.arnaldocanelas.projetoapi.entities.Account;
import pt.arnaldocanelas.projetoapi.entities.User;

public class UserDTO {
    
    private Long id;
    private String name;
    private Integer age;
    private List<AccountDTO> accounts = new ArrayList<>();

    public UserDTO(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.age = entity.getAge();
        
        for(Account account : entity.getAccounts()) {
			accounts.add(new AccountDTO(account));
		}
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

     public Integer getAge() {
        return age;
    }
     
 	public List<AccountDTO> getAccounts() {
		return accounts;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
 	
 	
 	
 }