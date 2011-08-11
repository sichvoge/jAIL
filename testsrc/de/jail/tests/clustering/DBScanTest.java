package de.jail.tests.clustering;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import de.jail.geometry.schemas.Point;
import de.jail.statistic.clustering.Cluster;
import de.jail.statistic.clustering.Clusterer;
import de.jail.statistic.clustering.density.DBScan;

/**
 * @author Christian Vogel
 *
 */
public class DBScanTest {
	
	private static Collection<Point> points;
	private static Clusterer clusterer;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		points = new ArrayList<Point>();
		points.add(new Point(new double[] {3.80286,2.15835}));
		points.add(new Point(new double[] {5.18384,2.21139}));
		points.add(new Point(new double[] {3.15722,2.30568}));
		points.add(new Point(new double[] {5.56977,2.37613}));
		points.add(new Point(new double[] {4.11252,2.95963}));
		points.add(new Point(new double[] {5.22759,2.47502}));
		points.add(new Point(new double[] {4.30611,2.10944}));
		points.add(new Point(new double[] {5.30211,2.33592}));
		points.add(new Point(new double[] {5.80744,2.17864}));
		points.add(new Point(new double[] {5.47962,2.59444}));
		
		points.add(new Point(new double[] {10.45085,9.48734}));
		points.add(new Point(new double[] {9.31804,9.37289}));
		points.add(new Point(new double[] {9.93597,9.00237}));
		points.add(new Point(new double[] {10.18147,9.43247}));
		points.add(new Point(new double[] {10.01732,9.31879}));
		points.add(new Point(new double[] {10.18090,8.74487}));
		points.add(new Point(new double[] {10.23984,9.19842}));
		points.add(new Point(new double[] {10.04680,9.23837}));
		points.add(new Point(new double[] {9.33601,8.81810}));
		points.add(new Point(new double[] {10.14558,8.25980}));
		
		clusterer = new DBScan(0.3,4);
	}

	/**
	 * Test method for {@link de.jail.statistic.clustering.density.DBScan#cluster(java.util.Collection)}.
	 */
	@Test
	public void testCluster() {
		List<Cluster> cluster = clusterer.cluster(points);
		
		assertNotNull("list should not be null", cluster);
	}

}
