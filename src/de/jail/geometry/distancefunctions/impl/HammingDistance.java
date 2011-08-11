package de.jail.geometry.distancefunctions.impl;

import de.jail.geometry.distancefunctions.StringBasedDistanceFunction;

/**
 * This class calculates the distance between two strings. The hamming distance
 * can only be calculated iff both strings have the same length. The distance
 * predicates how much characters are different.
 * <p>
 * Example: d("toned","roses") = 3 or d("10110","11010") = 2
 * </p> 
 * 
 * @author Christian Vogel
 */
public class HammingDistance implements StringBasedDistanceFunction {
	
	/**
	 * Default constructor doing nothing special.
	 */
	public HammingDistance() {}

	/**
	 * Calculates the distance between two strings.
	 */
	/* (non-Javadoc)
	 * @see de.mathlib.geometry.distancefunctions.IDistanceFunction#calculate(String, String)
	 */
	@Override
	public double calculate(String arg1, String arg2) {
		if(arg1 == null || arg2 == null) {
			throw new IllegalArgumentException("Arguments should not be null!");
		}
		
		if(arg1.length() != arg2.length()) {
			throw new IllegalArgumentException("Both string should have the same number of characters!");
		}
		
		double dist = 0;
		
		for(int i = 0; i < arg1.length(); i++) {
			if(arg1.charAt(i) != arg2.charAt(i)) {
				dist++;
			}
		}
		
		return dist;
	}

}
