package de.jail.geometry.schemas;

import de.jail.utils.HashCodeUtil;

/**
 * This class represents a normal point which can be in a multidimensional space. 
 * 
 * @author Christian Vogel
 */
public class Point {
	
	private double[] vector;
	
	public Point(double[] vector) {
		this.vector = vector;
	}
	
	public Point(int dim) {
		vector = new double[dim];
	}
	
	public int getDimension() {
		return vector.length;
	}
	
	/**
	 * Addition of this point with a defined one.
	 * 
	 * @param other 
	 * @return the result of point addition
	 */
	public Point add(final Point other) {
		if(other == null) {
			throw new IllegalArgumentException("argument must not be null");
		}
		
		if(this.getDimension() != other.getDimension()) {
			throw new IllegalArgumentException("points not equal, means calculation not possible");
		}
		
		double[] otherVector = other.getVector();
		
		double[] sumVector = new double[vector.length];
		
		for(int i = 0; i < vector.length; i++) {
			sumVector[i] = vector[i] + otherVector[i];
		}
		
		return new Point(sumVector);
	}
	
	/**
	 * Multiplies a scalar value to this point.
	 * 
	 * @param scalar value for multiplying
	 * @return new calculated point
	 */
	public Point multiply(final double scalar) {
		double[] result = new double[vector.length];
		
		for(int i = 0; i < vector.length; i++) {
			result[i] = scalar * vector[i];
		}
		
		return new Point(result);
	}
	
	public double[] getVector() {
		return vector;
	}
	
	public void setVector(double[] vector) {
		if(this.vector != null && this.vector.length != vector.length) {
			throw new IllegalArgumentException("Both vectors should have the same size.");
		}
		
		this.vector = vector;
	}
	
	/**
	 * Returns a string representation of a {@code Point}, where all data will be shown as a vector.  
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("[");
		
		if(vector != null) {
			for(int i = 0; i < vector.length; i++) {
				builder.append(vector[i]);
				
				if(i != (vector.length - 1)) {
					builder.append(",");
				}
			}
		}
		
		builder.append("]");
		
		return builder.toString();
	}
	
	/** 
	 * Determines whether or not two points are equal. Two instances of 
	 * {@code Point} are equal if the containing values of their {@code vector} 
	 * member fields are the same. 
	 * 
	 * @param that an object to be compared with this {@code Point} 
	 * 
	 * @return {@code true} if the object to be compared is an instance of 
	 *         {@code Point} and has the same values; {@code false} otherwise. 
	 */
	@Override
	public boolean equals(Object obj) {
		
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof Point)) {
			return false;
		}
		
		Point other = (Point)obj;
		
		double[] otherVector = other.getVector();
		double[] actualVector = getVector();
		
		if(otherVector.length != actualVector.length) {
			return false;
		}
				
		for(int i = 0; i < actualVector.length; i++) {
			if(actualVector[i] != otherVector[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	/** 
	 * Returns a hash code value for this {@code Point} object. 
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
	    result = HashCodeUtil.hash(result, vector);
	 
	    return result; 
	}

}
