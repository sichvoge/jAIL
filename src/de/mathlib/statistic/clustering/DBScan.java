package de.mathlib.statistic.clustering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.mathlib.geometry.distancefunctions.PointBasedDistanceFunction;
import de.mathlib.geometry.distancefunctions.impl.EuclideanDistance;
import de.mathlib.geometry.schemas.Point;

/**
 * @author Christian Vogel
 */
public class DBScan implements Clusterer {

	private List<Point> pointsMappedToCluster;
	
	private List<Point> visitedPoints;

	private List<Point> noisePoints;
	
	private Point ptrArray[];
	
	private double distanceMatrix[][];
	
	private final double eps;
	
	private final int minPtr;
	
	private final PointBasedDistanceFunction distFunction;
	
	/**
	 * Constructor with initial values for the area and minimum points for neighbor points. The distance function
	 * used is the default Euclidean distance.
	 * 
	 * @param eps minimum area in which a neighbor point should be, related to an other point
	 * @param minPtr number of neighbor points, otherwise points are noise 
	 */
	public DBScan(final double eps, final int minPtr) {
		this(eps,minPtr,new EuclideanDistance());
	}
	
	/**
	 * Constructor with initial values for the area, minimum points for neighbor points and a specific 
	 * distance function used to compute the distance matrix.
	 *  
	 * @param eps minimum area in which a neighbor point should be, related to an other point
	 * @param minPtr number of neighbor points, otherwise points are noise
	 * @param distFunction distance function which is used to compute the distance between to points
	 */
	public DBScan(final double eps, final int minPtr, final PointBasedDistanceFunction distFunction) {
		this.eps = eps;
		this.minPtr = minPtr;
		this.distFunction = distFunction;
	}

	/* (non-Javadoc)
	 * @see de.mathlib.statistic.clustering.Clusterer#cluster(java.util.Collection, de.mathlib.statistic.clustering.ClusteringUtils.Properties)
	 */
	@Override
	public List<Cluster> cluster(Collection<Point> points) {
		if(points == null) {
			throw new IllegalArgumentException("argument must not be null!");
		}
		
		ptrArray = new Point[points.size()];
		ptrArray = points.toArray(ptrArray);
		
		distanceMatrix = computeDistanceMatrix();
		
		return computeDbscan();
	}

	/**
	 * Computes the DBScan algorithm.
	 * 
	 * @return list of computed cluster
	 */
	private List<Cluster> computeDbscan() {
		int numberOfPoints = ptrArray.length;
		
		pointsMappedToCluster = new ArrayList<Point>(numberOfPoints);
		visitedPoints = new ArrayList<Point>(numberOfPoints);
		noisePoints = new ArrayList<Point>();
		
		List<Cluster> resultCluster = new ArrayList<Cluster>();
		
		for(int index = 0; index < numberOfPoints; index++) {
			Point current = ptrArray[index];
			
			if(!visitedPoints.contains(current)) {
				visitedPoints.add(current);
				
				List<Integer> neighbors = findNeighbors(index);
				
				if(neighbors.size() < minPtr) {
					noisePoints.add(current);
				} else {
					Cluster c = expandCluster(current, neighbors);
					
					resultCluster.add(c);
				}
			}
		}
		
		return resultCluster;
	}
	
	/**
	 * Finds all neighbors from a given point and which are in a specific area.
	 * 
	 * @param point_id point position in the computed distance matrix
	 * @param eps defines the area in which a neighbor point should be
	 * 
	 * @return list of neighbor point ids, which are related to a given point
	 */
	private List<Integer> findNeighbors(final int point_id) {
		List<Integer> result = new ArrayList<Integer>();
		
		for(int ptrIndex = 0; ptrIndex < distanceMatrix.length; ptrIndex++) {
			if((point_id != ptrIndex) && (distanceMatrix[point_id][ptrIndex] < eps)) {
				result.add(ptrIndex);
			}
		}
		
		return result;
	}
	
	/**
	 * Creates the cluster with all points which is the current visited point and all his neighbors and neighbors of the
	 * neighbors. Is one of the point already in any cluster, this point will be ignored.
	 * 
	 * @param p current visited point
	 * @param n neighbors of {@code p}
	 *
	 * @return a cluster containing all points which are "near" to each other
	 */
	private Cluster expandCluster(final Point p, final List<Integer> n) {
		Cluster c = new Cluster();
		
		c.addPoint(p);
		pointsMappedToCluster.add(p);
		
		for(int index = 0; index < n.size(); index++) {
			int np_id = n.get(index);
			Point np = ptrArray[np_id];
			
			if(!visitedPoints.contains(np)) {
				visitedPoints.add(np);
				
				List<Integer> neighbors = findNeighbors(np_id);
				
				if(neighbors.size() >= minPtr) {
					merge(n, neighbors);
				}
			}
			
			if(!pointsMappedToCluster.contains(np)) {
				c.addPoint(np);
			}
		}
		
		return c;
	}
	
	/**
	 * Merges to list by taking all points of {@code newNeighbors} and adds only the points to {@code currentNeighbors}
	 * which are not already in this list.
	 * 
	 * @param currentNeighbors resulting list which contains all points
	 * @param newNeighbors list of points which has to be added to {@code currentNeighbors}
	 */
	private void merge(List<Integer> currentNeighbors, final List<Integer> newNeighbors) {
		for(Integer p : newNeighbors) {			
			if(!currentNeighbors.contains(p)) {
				currentNeighbors.add(p);
			}
		}
	}
	
	/**
	 * Computes the distance matrix for a collection of points. Because it is a symmetric matrix, we only need to compute
	 * the upper half of the matrix and copies the value to the symmetric position.

	 * @return computed distance matrix which contains all distances between points
	 */
	private double[][] computeDistanceMatrix() {
		int numberOfPoints = ptrArray.length;
		double[][] distMatrix = new double[numberOfPoints][numberOfPoints];
		
		for(int row = 0; row < numberOfPoints; row++) {
			for(int col = row; col < numberOfPoints; col++) {
				if(row == col) {
					distMatrix[row][col] = 0;
				} else {
					distMatrix[row][col] = distMatrix[col][row] = distFunction.calculate(ptrArray[row], ptrArray[col]);
				}
			}
		}
		
		return distMatrix;
	}

}
