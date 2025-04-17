package pt.arnaldocanelas.projetoapi.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDTO extends CustomErrorDTO{
	
	private List<FieldMessageDTO> errors = new ArrayList<>();

	public ValidationErrorDTO(Instant timestamp, Integer status, String error, String path) {
		super(status, error, path);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessageDTO> getFieldMessages() {
		return errors;
	}
	
	public void addError (String fieldName, String message) {
		errors.add(new FieldMessageDTO(fieldName, message));
	}

}