package de.jail.geometry.schemas;


/**
 * This class represents an "average" (arithmetic mean) point of all points in a collection X. 
 * 
 * @author Christian Vogel
 */
public class Centroid extends Point {

	public Centroid(double[] vector) {
		super(vector);
	}
	
	public Centroid(Point p) {
		super(p.getVector());
	}

}
