/**
 * 
 */
package de.mathlib.geometry.schemas.matrices;

import de.mathlib.exceptions.MatrixException;

/**
 * This class represents a square matrix. A square matrix is a special matrix which has 
 * the same number of rows and columns. An n-by-n matrix is known as a square 
 * matrix of order n. Any two square matrices of the same order can be added 
 * and multiplied.
 * <p>
 * Data inside the matrix will be double.
 * </p>
 * 
 * @author Christian Vogel
 */
public class SquareMatrix extends Matrix {

	/**
	 * Initialize an empty nxn matrix with order n.
	 * 
	 * @param order the size of the matrix
	 */
	public SquareMatrix(final int order) {
		super(order, order);
	}

	/**
	 * Sets a new matrix with default initializing fields. The data should
	 * contain only data where the size of each dimension are the same and 
	 * when it is given, the size should be equals to the order as well.
	 * <p>
	 * example where order 2:
	 * <code>double[2][2]</code>
	 * </p>
	 * @param data the field data in a matrix
	 * @throws MatrixException thrown if dimensions does not have the same order or
	 * they have not the same order as the initialized matrix
	 */
	@Override
	public void setData(double[][] data) throws MatrixException {
		if(data.length != data[0].length) {
			throw new MatrixException("Data is not a square matrix, because the order of both dimensions are not equal!");
		}
		
		super.setData(data);
	}
	
	/**
	 * Returns the order of a square matrix. The column and the row order will
	 * be the same.
	 * 
	 * @return size of the matrix
	 */
	public int getOrder() {
		return super.getElements().length;
	}

	/**
	 * Calculates the determinant of the matrix with individual algorithms for
	 * different types of matrices.
	 * 
	 * @return determinant of a matrix
	 * 
	 * @author Christian Vogel
	 */
	public double determinant() {
		throw new UnsupportedOperationException();
	}
}
