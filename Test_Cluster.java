package KMeans;

import org.junit.Test;

import junit.framework.TestCase;

public class Test_Cluster extends TestCase {

	@Test
	public void testConstructor() {
		int id = 1;
		Cluster cluster1 = new Cluster(id);
		
	}
	
	public void testUpdateCentroid() {
		
		// The following tests whether the cluster class can successfully update the centroids
		int id = 2;
		Cluster cluster2 = new Cluster(id);
		double[] a1 = {0.0,1.0};
		double[] a2 = {0.0,0.0};
		double[] a3 = {1.0,1.0};
		double[] a4 = {1.0,0.0};
		Point p1 = new Point(a1);
		Point p2 = new Point(a2);
		Point p3 = new Point(a3);
		Point p4 = new Point(a4);
		double[] ac1 = {0.5,0.5};
		Centroid c1 = new Centroid(ac1);
		double[] ac2 = {0.8,0.8};
		Centroid c2 = new Centroid(ac2);
		cluster2.addPoint(p1);
		cluster2.addPoint(p2);
		cluster2.addPoint(p3);
		cluster2.addPoint(p4);
		cluster2.setCentroid(c2);
		int length = cluster2.getCentroid().length();
		System.out.printf("centriod length %d\n", length);

		Centroid c3 = cluster2.updateCentrod();
		System.out.println(c3.toString());
		cluster2.returnCluster();
		assertTrue(c3.equalsTo(c1));
	}

}
