package pt.arnaldocanelas.projetoapi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private Integer age;
	private String nif;
	
	@JsonIgnore  // Ignora a coleção de accounts durante a serialização
	@OneToMany(mappedBy = "holder", fetch = FetchType.LAZY)
	private List<Account> accounts = new ArrayList<>();

	public User() {}
	
	public User(Long id, String name, Integer age, String nif) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.nif = nif;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
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

	public List<Account> getAccounts() {
		return accounts;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", nif=" + nif + ", accounts=" + accounts + "]";
	}
	
}