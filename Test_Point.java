package KMeans;

import org.junit.Test;

import java.util.LinkedList;
import java.util.ArrayList;

import junit.framework.TestCase;

public class Test_Point extends TestCase {

	@Test
	public void testConstructor1() {
		
		double[] coor = {5.0,8.0};
		Point p = new Point(coor);
	}
	
	
	public void testEquals() {
		double[] coor = {1.2,3.5,6.8};
		double[] coor1 = {1.2,3.5,6.8};

		Point p1 = new Point(coor);
		Point p2 = new Point(coor1);
		
		assertTrue(p1.equalsTo(p1));
		assertTrue(p2.equalsTo(p1));
	}
	
	public void testDistance() {
		double[]  coor1 = {0.0,6.0};
		double[] coor2 = {8.0,0.0};

		Point p1 = new Point(coor1);
		
		Point p2 = new Point(coor2);
		assertTrue(Math.abs(p1.distanceTo(p2) - 10.0)<0.0001) ;
		
	}
	
	public void testPlus() {
		double[] coor = {1.2,3.5,6.8};
		double[] coor1 = {1.2,3.5,6.8};
		double[] coor2 = {2.4,7.0,13.6};

		Point p1 = new Point(coor);
		Point p2 = new Point(coor1);
		Point p3 = new Point(coor2);
		Point p4 = new Point(p1.plus(p2).elements());
		String s = p4.toString();
		System.out.println(s);
		assertTrue(p4.equalsTo(p3));
	}
	
	public void testMinMax() {
		double[] coor = {1.2,3.5,6.8};
		Point p1 = new Point(coor);
		assertTrue(Math.abs(p1.max() - 6.8) < 0.0001);
		assertTrue(Math.abs(p1.min() - 1.2) < 0.0001);
	}
	
	public void testScale() {
		double[] coor = {1.0,2.0};
		double[] coor1 = {2.0,4.0};
		Point p1 = new Point(coor);
		Point p2 = new Point(p1.scale(2.0));
		Point p3 = new Point(coor1);
		
		assertTrue(p3.equalsTo(p2));
		
	}
	
	public void testFindClosetCentroid() {
		double[] coor1 = {1.0,1.0};
		Centroid c1 = new Centroid(coor1);
		double[] coor2 = {2.0,3.0};
		Centroid c2 = new Centroid(coor2);
		double[] coor3 = {4.0,5.0};
		Centroid c3 = new Centroid(coor3);
		double[] coor4 = {0.5,0.5};
		Point p1 = new Point(coor4);
		ArrayList<Centroid> lc = new ArrayList<Centroid>();
		lc.add(c1);
		lc.add(c2);
		lc.add(c3);
		Centroid c4 = new Centroid(p1.findClosest(lc));
		assertTrue(c4.equalsTo(c1));
	}
	

}
