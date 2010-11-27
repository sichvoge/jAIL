/**
 * 
 */
package de.mathlib.schemas;

/**
 * This class represents a 2x2 matrix which is a special square matrix
 * where the order is two. 
 * 
 * @author Christian Vogel
 */
public class Matrix2d extends AbstractSquareMatrix {
	
	/**
	 * Initialize a 2x2 matrix. Only arrays with a size of 2 are allowed!
	 */
	public Matrix2d() {
		super(2);
	}
}
