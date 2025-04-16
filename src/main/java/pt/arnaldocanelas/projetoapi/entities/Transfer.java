package pt.arnaldocanelas.projetoapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "transfer")
public class Transfer extends AccountMovement {
	private static final long serialVersionUID = 1L;

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}