package de.jail.exceptions;

public class MissingPropertyException extends Exception {

	/** default serial for this class */
	private static final long serialVersionUID = 1L;

	/**
	 * Initialize a new missing property exception if something went wrong with
	 * property specific topics.
	 */
	public MissingPropertyException() {
		super();
	}

	/**
	 * Initialize a new missing property exception if something went wrong with
	 * property specific topics.
	 * 
	 * @param msg individual text which represents a detailed description of
	 * the exception
	 */
	public MissingPropertyException(String msg) {
		super(msg);
	}

}
