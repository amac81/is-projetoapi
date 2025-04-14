package pt.arnaldocanelas.projetoapi.controllers.exceptions;

public class BussinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BussinessException(String msg) {
		super(msg);
	}

}