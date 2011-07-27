package de.mathlib.geometry.distancefunctions;

import de.mathlib.geometry.schemas.Point;

/**
 * Interface introducing a default distance calculating function for all implementations.
 * 
 * @author Christian Vogel
 */
public interface IDistanceFunction {
	/**
	 * Calculates the distance between two points.
	 * 
	 * @param arg1 first point
	 * @param arg2 second point  	 
	 *  
	 * @return the calculated distance between first and second point
	 */
	double calculate(Point arg1, Point arg2);
}
