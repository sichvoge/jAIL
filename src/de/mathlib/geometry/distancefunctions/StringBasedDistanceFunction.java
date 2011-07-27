package de.mathlib.geometry.distancefunctions;

/**
 * Interface introducing a default distance calculating function based on strings for all implementations.
 * 
 * @author Christian Vogel
 */
public interface StringBasedDistanceFunction {
	/**
	 * Calculates the distance between two strings.
	 * 
	 * @param arg1 first argument
	 * @param arg2 second argument  	 
	 *  
	 * @return the calculated distance between first and second argument
	 */
	double calculate(String arg1, String arg2);
}
