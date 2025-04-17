package pt.arnaldocanelas.projetoapi.controllers.exceptions;

public class AccountMovementException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AccountMovementException(String msg) {
		super(msg);
	}

}