package de.jail.statistic.clustering;

import java.util.Collection;
import java.util.List;

import de.jail.geometry.schemas.Point;

/**
 * Interface introducing a cluster method for all implementation of clustering algorithms.
 * 
 * @author Christian Vogel
 */
public interface Clusterer {
	
	/**
	 * Running clustering algorithm for given points. 
	 * 
	 * @param points collection of points which will be set into specific cluster
	 * 
	 * @return list of cluster containing their belonging points
	 */
	List<Cluster> cluster(Collection<Point> points);

}
