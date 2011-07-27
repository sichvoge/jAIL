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

	/* (non-Javadoc)
	 * @see de.mathlib.geometry.distancefunctions.IDistanceFunction#calculate(de.mathlib.geometry.schemas.Point, de.mathlib.geometry.schemas.Point)
	 */
	@Override
	public double calculate(Point arg1, Point arg2) {
		if(arg1 == null || arg2 == null) {
			throw new NullPointerException("arguments cannot be null");
		}
		
		final double[] vector1 = arg1.getVector();
		final double[] vector2 = arg2.getVector();
		
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
