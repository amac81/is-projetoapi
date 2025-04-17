package pt.arnaldocanelas.projetoapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import pt.arnaldocanelas.projetoapi.entities.User;

public class UserMinDTO {
    
    private Long id;
    
    @NotBlank(message = "Campo necessário")
    private String name;
    @Positive(message = "Idade tem de ser um valor positivo")
    private Integer age;
    private String nif;
    private String username;
     
    public UserMinDTO() {}

    public UserMinDTO(Long id, String name, Integer age, String nif, String username) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.nif = nif;
        this.username = username;
    }

    public UserMinDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.age = entity.getAge();
        this.nif = entity.getNif();
        this.username = entity.getUsername();
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
 }