/**
 * 
 */
package de.mathlib.statistic.clustering;

import java.util.ArrayList;
import java.util.List;

import de.mathlib.geometry.schemas.Point;

/**
 * This class represents a general cluster which contains a number of points which are "neighbors".
 * 
 * @author Christian Vogel
 */
public class Cluster {
	
	private List<Point> points;
	
	/**
	 * Default constructor doing nothing special.
	 */
	public Cluster() {
		points = new ArrayList<Point>();
	}
	
	/**
	 * Adds a point to this cluster.
	 * 
	 * @param point related to this cluster
	 */
	public final void addPoint(final Point point) {
		if(point == null) {
			throw new IllegalArgumentException("point must not be null!");
		}
		
		points.add(point);
	}
	
	/**
	 * Returns a point from a specific index. All points are stored in a list.
	 * 
	 * @param index position in the list
	 * @return point at a specific position
	 */
	public final Point getPoint(final int index) {
		return points.get(index);
	}
	
	/**
	 * Returns all points which are related to this cluster.
	 * 
	 * @return a list of points
	 */
	public final List<Point> getAllPoints() {
		return points;
	}

}
