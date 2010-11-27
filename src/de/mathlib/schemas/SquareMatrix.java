package de.mathlib.schemas;

/**
 * This class only implements the abstract class for square matrices, so
 * that they can be initialized.
 * 
 * @author Christian Vogel
 */
public class SquareMatrix extends AbstractSquareMatrix {

	/**
	 * Initialize a nxn matrix where the size is n.
	 * 
	 * @param order the size of the matrix
	 */
	public SquareMatrix(int order) {
		super(order);
	}

	/**
	 * Initialize a nxn matrix with default data.
	 * 
	 * @param data in the matrix
	 */
	public SquareMatrix(double[][] data) {
		super(data);
	}

}
