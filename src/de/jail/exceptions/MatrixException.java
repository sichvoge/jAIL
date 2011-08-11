package de.jail.exceptions;

public class MatrixException extends Exception {

	/** default serial for this class */
	private static final long serialVersionUID = 1L;

	/**
	 * Initialize a new matrix exception if something went wrong with
	 * matrix specific topics.
	 */
	public MatrixException() {
		super();
	}

	/**
	 * Initialize a new matrix exception if something went wrong with
	 * matrix specific topics.
	 * 
	 * @param msg individual text which represents a detailed description of
	 * the exception
	 */
	public MatrixException(String msg) {
		super(msg);
	}

}
