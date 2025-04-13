package pt.arnaldocanelas.projetoapi.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import pt.arnaldocanelas.projetoapi.entities.User;

public class UserDTO {
    
    private Long id;
    
    @NotBlank(message = "Campo necessário")
    private String name;
    @Positive(message = "Idade tem de ser um valor positivo")
    private Integer age;
    private String nif;
    
    
    private List<AccountDTO> accounts = new ArrayList<>();

    public UserDTO() {}

    public UserDTO(Long id, String name, Integer age, String nif) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.nif = nif;
    }

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.age = entity.getAge();
        this.nif = entity.getNif();
        
        accounts = entity.getAccounts().stream().map(x->new AccountDTO(x)).collect(Collectors.toList());
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

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

 }