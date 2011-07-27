package de.mathlib.geometry.distancefunctions;

import de.mathlib.geometry.schemas.Point;

/**
 * Interface introducing a default distance calculating function based on points for all implementations
 * 
 * @author Christian Vogel
 */
public interface PointBasedDistanceFunction {
	/**
	 * Calculates the distance between two points.
	 * 
	 * @param arg1 first argument
	 * @param arg2 second argument  	 
	 *  
	 * @return the calculated distance between first and second argument
	 */
	double calculate(Point arg1, Point arg2);

}
