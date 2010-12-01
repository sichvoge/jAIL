/**
 * 
 */
package de.mathlib.schemas;

import de.mathlib.exceptions.MatrixException;


/**
 * This class represents a mxn matrix. A Matrix is a rectangular array 
 * of numbers. The horizontal and vertical lines in a matrix are called rows 
 * and columns, respectively. The numbers in the matrix are called its 
 * entries or its elements. To specify the size of a matrix, a matrix with m 
 * rows and n columns is called an m-by-n matrix or m × n matrix, while m and n 
 * are called its dimensions
 * <p>
 * Data inside the matrix will be double.
 * </p>
 * 
 * @author Christian Vogel
 */
public class Matrix implements Cloneable {
	
	private double[][] data;
	
	private int row_order = 0;
	private int column_order = 0;
	
	/**
	 * Initialize a default mxn matrix.
	 */
	public Matrix() {}
	
	/**
	 * Initialize a default mxn matrix.
	 * 
	 * @param row_order defines how much rows are possible in the matrix
	 * @param column_order defines how much columns are possible in the matrix
	 */
	public Matrix(final int row_order, final int column_order) {
		try {
			this.row_order = row_order;
			this.column_order = column_order;
			
			setData(new double[row_order][column_order]);
		} catch (MatrixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sets a new matrix with default initializing fields.
	 * 
	 * @param data the field data in a matrix
	 * @throws MatrixException thrown if the setting process fails
	 */
	public void setData(double[][] data) throws MatrixException {
		if(data == null) {
			throw new NullPointerException("data cannot be null");
		}
		
		if(row_order != 0 && column_order != 0) {		
			if(data.length != row_order || data[0].length != column_order) {
				throw new MatrixException("The dimension of data should be the same as the order for the matrix!");
			}
		} else {
			row_order = data.length;
			column_order = data[0].length;
		}
		
		this.data = data;
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
	 * Gets a specific entry out of the matrix.
	 * 
	 * @param row specify the row where the value will be found
	 * @param column specify the column where the value will be found
	 * @return value of row and column in the matrix
	 * 
	 * @author Christian Vogel
	 */
	public double getElement(int row, int column) {
		return data[row][column];
	}
	
	/**
	 * Transposes this matrix in place.
	 * 
	 * @author Christian Vogel
	 */
	public final void transpose() throws MatrixException {
		double[][] normalData = get();
		
		if(normalData == null) {
			throw new MatrixException("data cannot be null");
		}
		
		double[][] transposeData = new double[data.length][data[0].length];
			
		for(int i = 0; i < normalData.length; i++) {			
			for(int j = 0; j < normalData[i].length; j++) {
				transposeData[i][j] = normalData[j][i];
			}
		}
			
		setData(transposeData);
	}
	
	/**
	 * The scalar multiplication cA of a matrix A and a number c (also called 
	 * a scalar) is given by multiplying every entry of A by c:
	 * 
	 * @param scalar represents the multiplier
	 * @return new matrix with multiplied entries
	 * 
	 * @throws MatrixException thrown if multiplying fails
	 */
	public Matrix multiply(int scalar) throws MatrixException {
		Matrix newMatrix = new Matrix(row_order, column_order);
		
		double[][] normalData = get();
		
		if(normalData == null) {
			throw new MatrixException("data cannot be null");
		}
		
		double[][] newData = new double[row_order][column_order];
		
		for(int row = 0; row < normalData.length; row++) {			
			for(int column = 0; column < normalData[row].length; column++) {
				newData[row][column] = normalData[row][column] * scalar;
			}
		}
		
		newMatrix.setData(newData);
		
		return newMatrix;
	}
	
	/**
	 * Multiplication of two matrices is defined only if the number of columns 
	 * of the left matrix is the same as the number of rows of the right matrix. 
	 * If A is an m-by-n matrix and B is an n-by-p matrix, then their matrix 
	 * product AB is the m-by-p matrix whose entries are given by dot-product 
	 * of the corresponding row of A and the corresponding column of B.
	 * 
	 * @param arg
	 * @return
	 * @throws MatrixException
	 */
	public Matrix multiply(Matrix arg) throws MatrixException {
		Matrix newMatrix = new Matrix();
		
		double[][] normalData = get();
		
		if(normalData == null) {
			throw new MatrixException("data cannot be null");
		}
		
		double[][] argMatrix = arg.get();
		
		if(normalData[0].length != argMatrix.length) {
			throw new MatrixException("number of columns in matrix A should be the same as the number of rows in matrix B");
		}
		
		double[][] newData = new double[normalData.length][argMatrix[0].length];
		
		for(int i = 0; i < normalData.length; i++) {
			for(int j = 0; j < argMatrix[0].length; j++) {
				double value = 0;
				
				for(int r = 0; r < normalData[0].length; r++) {
					value += normalData[i][r] * argMatrix[r][j];				
				}
				
				newData[i][j] = value;
			}
		}
		
		newMatrix.setData(newData);
		
		return newMatrix;
	}
	
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
		
		if(!(obj instanceof Matrix)) {
			return false;
		}
		
		Matrix other = (Matrix)obj;
		
		double[][] otherData = other.get();
		double[][] actualData = get();
		
		if(actualData.length != otherData.length
				&& actualData[0].length != otherData[0].length) {
			return false;
		}
				
		for(int row = 0; row < actualData.length; row++) {
			for(int column = 0; column < actualData[row].length; column++) {
				if(actualData[row][column] != otherData[row][column]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	@Override
	public Matrix clone() {
		double[][] normalData = get();
		
		if(normalData == null) {
			return null;
		}
		
		Matrix newMatrix = new Matrix();
		
		double[][] newData = new double[row_order][column_order];
		
		for(int row = 0; row < normalData.length; row++) {
			for(int column = 0; column < normalData[row].length; column++) {
				newData[row][column] = normalData[row][column];
			}
		}
		
		try {
			newMatrix.setData(newData);
			
			return newMatrix;
		} catch (MatrixException e) {
			return null;
		}
	}
}
