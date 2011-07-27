package de.mathlib.geometry.distancefunctions.impl;

import de.mathlib.geometry.distancefunctions.IDistanceFunction;

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
public class HammingDistance implements IDistanceFunction {

	/**
	 * Calculates the distance between two strings. Both arguments should be
	 * of type {@link String}. If one of these are not of this type, a {code IllegalArgumentException}
	 * will be thrown.
	 */
	/* (non-Javadoc)
	 * @see de.mathlib.geometry.distancefunctions.IDistanceFunction#calculate(String, String)
	 */
	@Override
	public double calculate(Object arg1, Object arg2) {
		if(arg1 == null || arg2 == null) {
			throw new IllegalArgumentException("Arguments should not be null!");
		}
		
		if(!(arg1 instanceof String) || !(arg2 instanceof String)) {
			throw new IllegalArgumentException("arguments should be of class String");
		}
		
		String str1 = (String)arg1;
		String str2 = (String)arg2;
		
		if(str1.length() != str2.length()) {
			throw new IllegalArgumentException("Both string should have the same number of characters!");
		}
		
		double dist = 0;
		
		for(int i = 0; i < str1.length(); i++) {
			if(str1.charAt(i) != str2.charAt(i)) {
				dist++;
			}
		}
		
		return dist;
	}

}
