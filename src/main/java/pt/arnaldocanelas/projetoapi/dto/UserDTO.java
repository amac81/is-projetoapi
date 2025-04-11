package pt.arnaldocanelas.projetoapi.dto;

import pt.arnaldocanelas.projetoapi.entities.User;

public class UserDTO {
    
    private Long id;
    private String name;
    private Integer age;

    public UserDTO(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.age = entity.getAge();
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
 }