package de.mathlib.geometry.distancefunctions;

/**
 * Interface introducing a default distance calculating function for all implementations. The generic {@code <T>}
 * defines the type of the input for the function. There are some distance functions who will have different 
 * inputs like other functions.
 * 
 * @author Christian Vogel
 */
public interface IDistanceFunction<T> {
	/**
	 * Calculates the distance between two arguments.
	 * 
	 * @param arg1 first argument
	 * @param arg2 second argument  	 
	 *  
	 * @return the calculated distance between first and second argument
	 */
	double calculate(T arg1, T arg2);
}
