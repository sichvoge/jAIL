package de.mathlib.geometry.distancefunctions;

/**
 * Interface introducing a default distance calculating function for all implementations.
 * 
 * @author Christian Vogel
 */
public interface IDistanceFunction {
	/**
	 * Calculates the distance between two arguments.
	 * 
	 * @param arg1 first argument
	 * @param arg2 second argument  	 
	 *  
	 * @return the calculated distance between first and second argument
	 */
	double calculate(Object arg1, Object arg2);
}
