package de.mathlib.schemas;

/**
 * This class represents a 3x3 matrix which is a special square matrix
 * where the order is two. 
 * 
 * @author Christian Vogel
 */
public class Matrix3d extends AbstractSquareMatrix {

	/**
	 * Initialize a 3x3 matrix. Only arrays with a size of 3 are allowed!
	 */
	public Matrix3d() {
		super(3);
	}
}
