/**
 * 
 */
package de.jail.statistic.clustering.partitional.kmeans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import de.jail.geometry.distancefunctions.PointBasedDistanceFunction;
import de.jail.geometry.distancefunctions.impl.EuclideanDistance;
import de.jail.geometry.schemas.Centroid;
import de.jail.geometry.schemas.Point;
import de.jail.statistic.clustering.Cluster;
import de.jail.statistic.clustering.Clusterer;
import de.jail.utils.MathUtil;

/**
 * @author Christian Vogel
 */
public class KMeansClusterer implements Clusterer {
	
	private final PointBasedDistanceFunction function;
	private final int k;
	
	public KMeansClusterer(final int k) {
		this(k, new EuclideanDistance());
	}
	
	public KMeansClusterer(final int k, final PointBasedDistanceFunction function) {
		this.function = function;
		this.k = k;
	}

	/* (non-Javadoc)
	 * @see de.mathlib.statistic.clustering.Clusterer#cluster(java.util.Collection, de.mathlib.statistic.clustering.ClusteringUtils.Properties)
	 */
	@Override
	public List<Cluster> cluster(Collection<Point> points) {
		if(points == null) {
			throw new IllegalArgumentException("argument must not be null");
		}
		
		List<Point> list = new ArrayList<Point>(points);
		
		List<Cluster> cluster = new ArrayList<Cluster>(k);
		Random rand = new Random();
		
		// creates all needed cluster with random centroids from given list of points
		for(int i = 0; i < k; i++) {
			int index = rand.nextInt(list.size()) + 1;
			
			Point center = list.get(index - 1);
			KMeansCluster c = new KMeansCluster();
			c.setCenter(center);
			cluster.add(c);
		}
		
		boolean posChange;
		
		do {
			posChange = false;
			
			for (Point p : list) {
				Cluster buffer = null;

				double bufferDistance = Double.MAX_VALUE;

				for (Cluster c : cluster) {
					Centroid centroid = ((KMeansCluster) c).getCenter();

					double distanceToCenter = function.calculate(p, centroid);

					if (distanceToCenter < bufferDistance) {
						buffer = c;
						bufferDistance = distanceToCenter;
					}
				}

				buffer.addPoint(p);
			}

			for (Cluster c : cluster) {
				Centroid newMean = MathUtil.mean(c.getAllPoints());
				
				KMeansCluster kCluster = (KMeansCluster) c;
				Centroid oldMean = kCluster.getCenter();

				if (!oldMean.equals(newMean)) {
					posChange = true;
					kCluster.setCenter(newMean);
				}
			}
		} while (posChange);
		
		return cluster;
	}

}
