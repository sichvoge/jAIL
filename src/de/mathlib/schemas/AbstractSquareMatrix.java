/**
 * 
 */
package de.mathlib.schemas;

import de.mathlib.exceptions.MatrixException;

/**
 * This class represents a square matrix. A square matrix is a matrix which has 
 * the same number of rows and columns. An n-by-n matrix is known as a square 
 * matrix of order n. Any two square matrices of the same order can be added 
 * and multiplied.
 * <p>
 * Data inside the matrix will be double.
 * </p>
 * 
 * @author Christian Vogel
 */
public abstract class AbstractSquareMatrix {
	
	private double[][] data;
	
	/**
	 * ToDo: change data type to short?
	 */
	private int order;

	/**
	 * Initialize a nxn matrix where the size is n.
	 * 
	 * @param order the size of the matrix
	 */
	public AbstractSquareMatrix(final int order) {
		this.order = order;
		try {
			setData(new double[this.order][this.order]);
		} catch (MatrixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialize a nxn matrix with default data.
	 * 
	 * @param data in the matrix
	 */
	public AbstractSquareMatrix(final double[][] data) {		
		try {
			if(data == null) {
				throw new NullPointerException("null values are not allowed");
			}
			
			setData(data);
		} catch (MatrixException e) {
			// handling exception
			e.printStackTrace();
		}
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
	public void setData(double[][] data) throws MatrixException {
		// comment by bytefish (Philipp Wagner) :) 
		if(data.length != data[0].length) {
			throw new MatrixException("Data is not a square matrix, because the order of both dimensions are not equal!");
		}
		
		if(data.length != order || data[0].length != order) {
			throw new MatrixException("The dimension if data should be the same as the order for the matrix!");
		}
		
		this.data = data;
	}
	
	/**
	 * Gets a specific value contained in the matrix.
	 * 
	 * @param row specify the row where the value will be found
	 * @param column specify the column where the value will be found
	 * @return value of row and column in the matrix
	 * 
	 * @author Christian Vogel
	 */
	public double getValueOf(int row, int column) {
		return data[row][column];
	}

	/**
	 * Gets all values stored in the matrix.
	 * 
	 * @return the data in a matrix
	 */
	public double[][] get() {
		return data;
	}

	
	/**
	 * Transposes this matrix in place.
	 * 
	 * @author Christian Vogel
	 */
	public final void transpose() {		
		try {
			double[][] transposeData = new double[getOrder()][getOrder()];
			double[][] normalData = get();
			
			for(int i = 0; i < normalData.length; i++) {			
				for(int j = 0; j < normalData[i].length; j++) {
					transposeData[i][j] = normalData[j][i];
				}
			}
			
			setData(transposeData);
		} catch (MatrixException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the order of a square matrix. The column and the row order will
	 * be the same.
	 * 
	 * @return size of the matrix
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * Calculates the determinant of the matrix with individual algorithms for
	 * different types of matrices.
	 * 
	 * @return determinant of a matrix
	 * 
	 * @author Christian Vogel
	 */
	public abstract double determinant();
	
	@Override
	public String toString() {
		StringBuilder matrixBuilder = new StringBuilder();
		
		for(int row = 0; row < data.length; row++) {
			String sRow = "";
			
			for(int column = 0; column < data[row].length; column++) {
				sRow += data[row][column] + " ";
			}
			
			matrixBuilder.append(sRow + "\n");
		}
		
		return matrixBuilder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		// comment by bytefish (Philipp Wagner) :)
		if(obj == null) {
			return false;
		}
		
		if(!(obj instanceof AbstractSquareMatrix)) {
			return false;
		}
		
		AbstractSquareMatrix other = (AbstractSquareMatrix)obj;
		
		if(this.getOrder() != other.getOrder()) {
			return false;
		}
		
		double[][] mData = other.get();
				
		for(int row = 0; row < data.length; row++) {
			for(int column = 0; column < data[row].length; column++) {
				if(this.data[row][column] != mData[row][column]) {
					return false;
				}
			}
		}
		
		return true;
	}
}
