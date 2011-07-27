/**
 * 
 */
package de.mathlib.statistic.clustering.kmeans;

import de.mathlib.geometry.schemas.Point;
import de.mathlib.statistic.clustering.Cluster;

/**
 * Class represents a special cluster which are related to the kmeans clustering. Difference to a general cluster is, we have
 * a center now.
 * 
 * @author Christian Vogel
 */
public class KMeansCluster extends Cluster {
	
	private Point center;
	
	/**
	 * Default constructor doing nothing special.
	 */
	public KMeansCluster() {
		super();
	}

	/**
	 * Returns the current center point of this cluster.
	 * 
	 * @return the center point
	 */
	public Point getCenter() {
		return center;
	}
	
	/**
	 * Set a point to be the center of this cluster. 
	 * 
	 * @param center normal point
	 */
	public void setCenter(Point center) {
		this.center = center;
	}
}
