/**
 * 
 */
package de.mathlib.geometry.schemas.matrices;

import de.mathlib.exceptions.MatrixException;
import de.mathlib.utils.HashCodeUtil;


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
	 * Initialize an empty mxn matrix.
	 */
	public Matrix() {}
	
	/**
	 * Initialize an empty mxn matrix.
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
			e.printStackTrace();
		}
	}

	/**
	 * Sets a new matrix with default initializing fields.
	 * 
	 * @param data the field data in a matrix
	 * @throws MatrixException thrown if the setting process fails
	 */
	public void setData(final double[][] data) throws MatrixException {
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
	 * Returns the number of rows.
	 * 
	 * @return number of rows in the matrix
	 */
	public final int numberOfRows() {
		return row_order;
	}
	
	/**
	 *  Returns the number of columns.
	 * 
	 * @return number of columns in the matrix
	 */
	public final int numberOfColumns() {
		return column_order;
	}
	
	/**
	 * Gets all entries stored in the matrix.
	 * 
	 * @return the data in a matrix
	 */
	public final double[][] getElements() {
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
	public final double getElementAt(int row, int column) {
		if(row > numberOfRows()) {
			throw new IndexOutOfBoundsException("row should be not greater than the number of rows in the matrix");
		}
		
		if(column > numberOfColumns()) {
			throw new IndexOutOfBoundsException("column should be not greater than the number of columns in the matrix");
		}
		
		return data[row][column];
	}
	
	/**
	 * Transposes this matrix in place.
	 * <p>
	 * The transpose of a m-by-n matrix A is the n-by-m matrix 
	 * formed by turning rows into columns and vice versa.
	 * </p>
	 * 
	 * @author Christian Vogel
	 */
	public final void transpose() throws MatrixException {
		double[][] normalData = getElements();
		
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
	 * 
	 * @author Christian Vogel
	 */
	public final Matrix multiply(double scalar) throws MatrixException {
		Matrix newMatrix = new Matrix(row_order, column_order);
		
		double[][] normalData = getElements();
		
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
	 * @param arg represents matrix B
	 * @return a new instance of the multiplied matrix
	 * @throws MatrixException thrown if multiplying fails
	 * 
	 * @author Christian Vogel
	 */
	public final Matrix multiply(Matrix arg) throws MatrixException {
		Matrix newMatrix = new Matrix();
		
		double[][] normalData = getElements();
		
		if(normalData == null) {
			throw new MatrixException("data cannot be null");
		}
		
		if(arg == null) {
			throw new MatrixException("argument cannot be null");
		}
		
		double[][] argMatrix = arg.getElements();
		
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
	
	/**
	 * Two matrices can be aggregated, if matrix A is from the same type
	 * like matrix B. Type means that the number of rows and columns are
	 * the same in both matrices. The sum of A+B will be calculated
	 * by aggregating each entry of both matrices.
	 * 
	 * 
	 * @param arg represents matrix B
	 * @return a new instance of the aggregated matrix
	 * @throws MatrixException thrown if aggregating fails
	 */
	public final Matrix aggregate(Matrix arg) throws MatrixException {
		Matrix newMatrix = new Matrix();
		
		double[][] normalData = getElements();
		
		if(normalData == null) {
			throw new MatrixException("data cannot be null");
		}
		
		if(arg == null) {
			throw new MatrixException("argument cannot be null");
		}

		double[][] argMatrix = arg.getElements();
		
		if(normalData.length != argMatrix.length
				&& normalData[0].length != argMatrix[0].length) {
			throw new MatrixException("number of columns in matrix A should be the same as the number of rows in matrix B");
		}
		
		double[][] newData = new double[normalData.length][normalData[0].length];
		
		for(int i = 0; i < normalData.length; i++) {
			for(int j = 0; j < normalData[0].length; j++) {
				newData[i][j] = normalData[i][j] + argMatrix[i][j];
			}
		}
		
		newMatrix.setData(newData);
		
		return newMatrix;
	}
	
	/**
	 * Returns a string representation of a {@code Matrix}, where all data will be shown as usual for a matrix in 
	 * rows and columns.  
	 */
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
	
	/** 
	 * Determines whether or not two matrices are equal. Two instances of 
	 * {@code Matrix} are equal if the containing values of their {@code data} 
	 * member fields are the same. 
	 * 
	 * @param that an object to be compared with this {@code Matrix} 
	 * 
	 * @return {@code true} if the object to be compared is an instance of 
	 *         {@code Matrix} and has the same values; {@code false} otherwise. 
	 */
	@Override
	public boolean equals(Object obj) {
		
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof Matrix)) {
			return false;
		}
		
		Matrix other = (Matrix)obj;
		
		double[][] otherData = other.getElements();
		double[][] actualData = getElements();
		
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
	
	/** 
	 * Returns a hash code value for this {@code Matrix} object. 
	 * 
	 * @return A hash code value for this object. 
	 * 
	 * @see java.lang.Object#equals(java.lang.Object) 
	 * @see java.util.HashMap 
	 */ 
	@Override 
	public int hashCode() 
	{ 
	    int result = HashCodeUtil.SEED;
	    result = HashCodeUtil.hash(result, data);
	 
	    return result; 
	}
	
	/**
	 * Produces a copy of this {@code Matrix}. Copy means that the new {@code Matrix contains} the same
	 * values.
	 * 
	 * @return a new {@code Matrix} object with the same values
	 */
	@Override
	public Matrix clone() {
		double[][] normalData = getElements();
		
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
