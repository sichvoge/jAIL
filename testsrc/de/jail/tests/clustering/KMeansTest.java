package de.jail.tests.clustering;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import de.jail.geometry.schemas.Point;
import de.jail.statistic.clustering.Cluster;
import de.jail.statistic.clustering.Clusterer;
import de.jail.statistic.clustering.partitional.kmeans.KMeansClusterer;
import de.jail.utils.FileUtil;

/**
 * @author Christian Vogel
 *
 */
public class KMeansTest {
	
	private static Collection<Point> points;
	private static Clusterer clusterer;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		points = FileUtil.importPointsFromCsv("testsrc/de/jail/tests/resources/data2d_1545.csv", ",", false);
		
		clusterer = new KMeansClusterer(2);
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
