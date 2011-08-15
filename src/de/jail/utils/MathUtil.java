/**
 * 
 */
package de.jail.utils;

import java.util.Collection;

import de.jail.geometry.schemas.Centroid;
import de.jail.geometry.schemas.Point;

/**
 * The class {@code MatUtil} contains methods for performing basic numeric operations such 
 * as the elementary exponential, logarithm, square root, and trigonometric 
 * functions. Furthermore, it contains two different PI and E numbers where
 * the precision will be float and double.
 * 
 * @author Christian Vogel
 */
public class MathUtil {
	
	/** 
	 * <b>PI</b>
	 * 
	 * <p>
	 * 	<code>public final static float F_PI</code>
	 * </p>
	 * PI with a precision of seven number of digits after the decimal point 
	 */
	public final static float F_PI = 3.1415926f;
	
	/** 
	 * <b>PI</b>
	 * 
	 * <p>
	 * 	<code>public final static double D_PI</code>
	 * </p>
	 * PI with a precision of sixteen number of digits after the decimal point 
	 */
	public final static double D_PI = 3.1415926535897932;
	
	/** 
	 * <b>E - Euler's number</b>
	 * 
	 * <p>
	 * 	<code>public final static float F_E</code>
	 * </p>
	 * E with a precision of seven number of digits after the decimal point 
	 */
	public final static float F_E = 2.7182818f;
	
	/** 
	 * <b>E - Euler's number</b>
	 * 
	 * <p>
	 * 	<code>public final static double D_E</code>
	 * </p>
	 * E with a precision of sixteen number of digits after the decimal point 
	 */
	public final static double D_E = 2.7182818284590452;
	
	/**
	 * Returns a double value where the argument will be raised to the power of 2.
	 * 
	 * @param arg the value which will be squared
	 * @return the value raised to the power of 2
	 */
	public static double square(double arg) {
		return arg * arg;
	}
	
	/**
	 * Returns an int value where the argument will be raised to the power of 2.
	 * 
	 * @param arg the value which will be squared
	 * @return the value raised to the power of 2
	 */
	public static int square(int arg) {
		return arg * arg;
	}
	
	/**
	 * Returns a float value where the argument will be raised to the power of 2.
	 * 
	 * @param arg the value which will be squared
	 * @return the value raised to the power of 2
	 */
	public static float square(float arg) {
		return arg * arg;
	}
	
	/**
	 * Calculates the mean from a collection of points.
	 * 
	 * @param points collection of points
	 * @return calculated mean point
	 * 
	 * @see Centroid
	 */
	public static Centroid mean(Collection<Point> points) {
		if(points == null) {
			throw new IllegalArgumentException("argument must not be null");
		}
		
		if(points.size() == 0) {
			return null;
		}
		
		Point mean = null;
		
		for(Point p : points) {
			if(mean == null) {
				mean = p;
			} else {
				mean = mean.add(p);
			}
		}
		
		Point center = mean.multiply(1.0/(double)points.size());
		
		return new Centroid(center);
	}
}
