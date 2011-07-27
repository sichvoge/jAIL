package de.mathlib.geometry.distancefunctions.impl;

import de.mathlib.geometry.distancefunctions.IDistanceFunction;
import de.mathlib.geometry.schemas.Point;

/**
 * This class calculates the distance between two point through the manhattan distance function. 
 * The given formula for a n-dimensional space and between two points p and q is: 
 * <p>
 * 	{@code d(p,q)=|p_1 - q_1| + |p_2 - q_2| + ... + |p_n - q_n|}
 * </p>
 * 
 * @author Christian Vogel
 */
public class ManhattanDistance implements IDistanceFunction {

	/**
	 * Calculates the distance between two points. Both arguments should be
	 * of type {@link Point}. If one of these are not of this type, a {code IllegalArgumentException}
	 * will be thrown.
	 */
	/* (non-Javadoc)
	 * @see de.mathlib.geometry.distancefunctions.IDistanceFunction#calculate(de.mathlib.geometry.schemas.Point, de.mathlib.geometry.schemas.Point)
	 */
	@Override
	public double calculate(Object arg1, Object arg2) {
		if(arg1 == null || arg2 == null) {
			throw new IllegalArgumentException("arguments cannot be null");
		}
		
		if(!(arg1 instanceof Point) || !(arg2 instanceof Point)) {
			throw new IllegalArgumentException("arguments should be of class Point");
		}
		
		final double[] vector1 = ((Point)arg1).getVector();
		final double[] vector2 = ((Point)arg2).getVector();
		
		if(vector1.length != vector2.length) {
			throw new IllegalArgumentException("Both points should be in the same dimensional space.");
		}
		
		double sum = 0;
		
		for(int i = 0; i < vector1.length; i++) {
			sum = Math.abs(vector1[i] - vector2[i]);
		}
		
		return sum;
	}

}
